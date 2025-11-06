package com.boot.controller;

import com.boot.dto.Criteria;
import com.boot.dto.NoticeDTO;
import com.boot.dto.PagingDTO;
import com.boot.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService service;

    @RequestMapping("/notice_list")
    public String notice_list(Criteria cri, Model model) {
        log.info("notice_list()");
        List<NoticeDTO> noticeList = service.noticeList(cri);
        int total = service.getTotalCount();

        model.addAttribute("list", noticeList);
        model.addAttribute("pageMaker", new PagingDTO(total, cri));
        return "notice/notice_list";
    }

    @RequestMapping("/notice_view")
    public String notice_view(@RequestParam HashMap<String, String> param,
                              Model model, Criteria cri) {
        log.info("notice_view()");
        service.increaseViews(param);
        NoticeDTO noticeView = service.noticeView(param);
        int total = service.getTotalCount();

        model.addAttribute("view", noticeView);
        model.addAttribute("pageMaker", new PagingDTO(total, cri));
        return "notice/notice_view";
    }

    @RequestMapping("/notice_write")
    public String notice_write(HttpSession session) {
        log.info("notice_write()");
        String accountRole = (String) session.getAttribute("account_role");
        if (!"admin".equals(accountRole)) {
            return "redirect:/login";
        }
        return "notice/notice_write";
    }

    @RequestMapping("/writeProcess")
    public String writeProcess(@RequestParam HashMap<String, String> param, HttpSession session) {
        log.info("writeProcess()");
        String loginId = (String) session.getAttribute("login_id");
        String accountRole = (String) session.getAttribute("account_role");

        if (loginId == null || !"admin".equals(accountRole)) {
            return "redirect:/login";
        }

        param.put("notice_writer", loginId);
        service.writeProcess(param);
        return "redirect:/notice/notice_list"; // ✅ 절대 경로로 변경
    }

    @RequestMapping("/notice_modify")
    public String notice_modify(@RequestParam HashMap<String, String> param,
                                Model model, Criteria cri, HttpSession session) {
        log.info("notice_modify()");
        String accountRole = (String) session.getAttribute("account_role");
        if (!"admin".equals(accountRole)) {
            return "redirect:/notice/notice_list"; // ✅ 절대 경로 유지
        }

        NoticeDTO noticeModify = service.noticeView(param);
        int total = service.getTotalCount();

        model.addAttribute("modify", noticeModify);
        model.addAttribute("pageMaker", new PagingDTO(total, cri));
        return "notice/notice_modify";
    }

    @RequestMapping("/modifyProcess")
    public String modify(@RequestParam HashMap<String, String> param,
                         RedirectAttributes attr, HttpSession session) {
        log.info("modifyProcess()");
        String accountRole = (String) session.getAttribute("account_role");
        if (!"admin".equals(accountRole)) {
            return "redirect:/notice/notice_list"; // ✅ 절대 경로로 변경
        }

        service.modifyProcess(param);
        attr.addAttribute("pageNum", param.get("pageNum"));
        attr.addAttribute("amount", param.get("amount"));
        return "redirect:/notice/notice_list"; // ✅ 절대 경로로 변경
    }

    @RequestMapping("/deleteProcess")
    public String deleteProcess(@RequestParam HashMap<String, String> param,
                                RedirectAttributes attr, HttpSession session) {
        log.info("deleteProcess()");
        String accountRole = (String) session.getAttribute("account_role");
        if (!"admin".equals(accountRole)) {
            return "redirect:/notice/notice_list"; // ✅ 절대 경로로 변경
        }

        service.deleteProcess(param);
        attr.addAttribute("pageNum", param.get("pageNum"));
        attr.addAttribute("amount", param.get("amount"));
        return "redirect:/notice/notice_list"; // ✅ 절대 경로로 변경
    }
}