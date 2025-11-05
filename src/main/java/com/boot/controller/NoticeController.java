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

import java.util.HashMap;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService service;

    // 공지사항 목록 조회
    @RequestMapping("/notice_list")
    public String notice_list(Criteria cri, Model model) {
        log.info("notice_list()");

        List<NoticeDTO> noticeList = service.noticeList(cri);
        int total = service.getTotalCount();

        model.addAttribute("list", noticeList);
        model.addAttribute("pageMaker", new PagingDTO(total, cri));

        return "notice/notice_list";
    }

    // 공지사항 상세보기
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

    // 공지사항 작성 페이지 이동
    @RequestMapping("/notice_write")
    public String notice_write() {
        log.info("notice_write()");
        return "notice/notice_write";
    }

    // 공지사항 작성 처리
    @RequestMapping("/writeProcess")
    public String writeProcess(@RequestParam HashMap<String, String> param) {
        log.info("writeProcess()");
        service.writeProcess(param);
        return "redirect:notice_list";
    }

    // 공지사항 수정 페이지 이동
    @RequestMapping("/notice_modify")
    public String notice_modify(@RequestParam HashMap<String, String> param,
                                Model model, Criteria cri) {
        log.info("notice_modify()");

        NoticeDTO noticeModify = service.noticeView(param);
        int total = service.getTotalCount();

        model.addAttribute("modify", noticeModify);
        model.addAttribute("pageMaker", new PagingDTO(total, cri));

        return "notice/notice_modify";
    }

    // 공지사항 수정 처리
    @RequestMapping("/modifyProcess")
    public String modify(@RequestParam HashMap<String, String> param,
                         RedirectAttributes attr) {
        log.info("modifyProcess()");

        service.modifyProcess(param);

        attr.addAttribute("pageNum", param.get("pageNum"));
        attr.addAttribute("amount", param.get("amount"));

        return "redirect:notice_list";
    }

    // 공지사항 삭제 처리
    @RequestMapping("/deleteProcess")
    public String deleteProcess(@RequestParam HashMap<String, String> param,
                                RedirectAttributes attr) {
        log.info("deleteProcess()");

        service.deleteProcess(param);

        attr.addAttribute("pageNum", param.get("pageNum"));
        attr.addAttribute("amount", param.get("amount"));

        return "redirect:notice_list";
    }
}