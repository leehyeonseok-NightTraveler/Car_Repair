package com.boot.controller;

import com.boot.dto.AccountDTO;
import com.boot.dto.InquiryDTO;
import com.boot.service.InquiryService;
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
@RequestMapping("/inquiry")
public class InquiryController {

    @Autowired
    private InquiryService service;

    // 1대1 문의 작성 페이지 (로그인 필요)
    @RequestMapping("/inquiry_write")
    public String inquiryWrite(HttpSession session) {
        log.info("inquiryWrite()");
        String loginId = (String) session.getAttribute("accountId");

        if (loginId == null) {
            return "redirect:/login";
        }

        return "inquiry/inquiry_write";
    }

    // 1대1 문의 작성 처리 (로그인 필요)
    @RequestMapping("/writeProcess")
    public String writeProcess(@RequestParam HashMap<String, String> param, HttpSession session) {
        log.info("writeProcess()");
        String loginId = (String) session.getAttribute("accountId");

        if (loginId == null) {
            return "redirect:/login";
        }

        param.put("customer_id", loginId);
        service.writeProcess(param);

        return "redirect:/inquiry/inquiry_history";
    }

    // 1대1 문의 내역 조회 (로그인 필요)
    @RequestMapping("/inquiry_history")
    public String inquiryHistory(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
        log.info("inquiryHistory()");

        // 로그인 사용자 정보 조회
        String loginId = (String) session.getAttribute("accountId");
        AccountDTO userInfo = service.getUserInfo(loginId);
        model.addAttribute("userInfo", userInfo);

        if (loginId == null) {
            return "redirect:/login";
        }

        param.put("customer_id", loginId);

        List<InquiryDTO> inquiryList = service.inquiryList(param);
        model.addAttribute("inquiryList", inquiryList);

        return "inquiry/inquiry_history";
    }

    // 1대1 문의 상세 조회 (로그인 필요)
    @RequestMapping("/inquiry_view")
    public String inquiryView(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
        log.info("inquiryView()");
        String accountId = (String) session.getAttribute("accountId");

        if (accountId == null) {
            return "redirect:/login";
        }

        // 로그인 사용자 정보 조회
        String loginId = (String) session.getAttribute("accountId");
        AccountDTO userInfo = service.getUserInfo(loginId);
        model.addAttribute("userInfo", userInfo);

        param.put("customer_id", loginId);
        InquiryDTO inquiryView = service.inquiryView(param);
        model.addAttribute("inquiryView", inquiryView);

        return "inquiry/inquiry_view";
    }

    @RequestMapping("/reply_write")
    public String reply_write(@RequestParam HashMap<String, String> param, HttpSession session, Model model) {
        log.info("replyWrite()");

        String loginId = (String) session.getAttribute("accountId");

        if (loginId == null) {
            return "redirect:/login";
        }

        InquiryDTO inquiryView = service.inquiryView(param);
        model.addAttribute("reply", inquiryView);

        return "inquiry/reply_write";
    }

    @RequestMapping("/replyProcess")
    public String replyProcess(@RequestParam HashMap<String, String> param, HttpSession session, RedirectAttributes attr) {
        log.info("replyProcess()");

        String loginId = (String) session.getAttribute("accountId");

        if (loginId == null) {
            return "redirect:/login";
        }

        service.replyProcess(param);

        attr.addAttribute("inquiry_no", param.get("inquiry_no"));

        return "redirect:/inquiry/inquiry_view";
    }
}