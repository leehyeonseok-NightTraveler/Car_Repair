package com.boot.controller;

import com.boot.dto.InquiryDTO;
import com.boot.service.InquiryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/inquiry")
public class InquiryController {

    @Autowired
    private InquiryService service;

    // 1대1문의 작성 페이지 (로그인 필요)
    @RequestMapping("/inquiry_write")
    public String inquiry_write(HttpSession session) {
        log.info("inquiry_write()");
        String loginId = (String) session.getAttribute("login_id");

//        if (loginId == null) {
//            return "redirect:/login"; // ✅ 로그인 안 되어 있으면 로그인 페이지로
//        }

        return "inquiry/inquiry_write";
    }

    // 1대1문의 작성 처리 (로그인 필요)
    @RequestMapping("/writeProcess")
    public String writeProcess(@RequestParam HashMap<String, String> param, HttpSession session) {
        log.info("writeProcess()");
        String loginId = (String) session.getAttribute("login_id");

//        if (loginId == null) {
//            return "redirect:/login"; // ✅ 로그인 안 되어 있으면 로그인 페이지로
//        }

        param.put("inquiry_writer", loginId); // 작성자 정보 세션에서 주입
        service.writeProcess(param);

        return "redirect:/inquiry/inquiry_history"; // ✅ 경로도 절대 경로로 수정
    }

    @RequestMapping("/inquiry_history")
    public String inquiry_history(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
        log.info("inquiry_history()");

        List<InquiryDTO> inquiryList = service.inquiryList();
        model.addAttribute("inquiryList", inquiryList);

        return "inquiry/inquiry_history";
    }

    @RequestMapping("/inquiry_view")
    public String inquiry_view(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
        log.info("inquiry_view()");

        InquiryDTO inquiryView = service.inquiryView(param);
        model.addAttribute("inquiryView", inquiryView);

        return "inquiry/inquiry_view";
    }
}