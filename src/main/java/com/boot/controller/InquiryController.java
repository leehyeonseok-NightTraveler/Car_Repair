package com.boot.controller;

import com.boot.dto.AccountDTO;
import com.boot.dto.Criteria;
import com.boot.dto.InquiryDTO;
import com.boot.dto.PagingDTO;
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
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/inquiry")
public class InquiryController {

    @Autowired
    private InquiryService service;

    // 1. 문의 작성 페이지 진입
    @RequestMapping("/inquiry_write")
    public String inquiry_write(HttpSession session, Model model) {
        log.info("inquiryWrite()");
        String loginId = (String) session.getAttribute("accountId");
        String Role =  (String) session.getAttribute("ROLE");

        if (loginId == null) return "redirect:/login";

        AccountDTO userInfo = service.getUserInfo(loginId);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("role", Role);

        return "inquiry/inquiry_write";
    }

    // 2. 문의 작성 처리
    @RequestMapping("/writeProcess")
    public String writeProcess(@RequestParam HashMap<String, String> param, HttpSession session) {
        log.info("writeProcess()");
        String loginId = (String) session.getAttribute("accountId");

        if (loginId == null) return "redirect:/login";

        param.put("customer_id", loginId);
        service.writeProcess(param);

        return "redirect:/inquiry/inquiry_history";
    }

    // 3. 사용자 문의 내역 조회
    @RequestMapping("/inquiry_history")
    public String inquiry_history(@RequestParam HashMap<String, String> param, Model model,
                                  HttpSession session, Criteria cri) {
        log.info("inquiryHistory()");
        String loginId = (String) session.getAttribute("accountId");
        String Role = (String) session.getAttribute("ROLE");

        if (loginId == null) return "redirect:/login";
        if (Objects.equals(Role, "ADMIN")) return "redirect:/inquiry/inquiry_manage";

        AccountDTO userInfo = service.getUserInfo(loginId);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("role", Role);
        param.put("customer_id", loginId);
        List<InquiryDTO> inquiryList = service.inquiryList(param, cri);
        model.addAttribute("inquiryList", inquiryList);

        int total = service.TotalInquiryUser(loginId);
        model.addAttribute("pageMaker", new PagingDTO(total, cri));

        return "inquiry/inquiry_history";
    }

    // 4. 문의 상세 조회
    @RequestMapping("/inquiry_view")
    public String inquiry_view(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
        log.info("inquiryView()");
        String loginId = (String) session.getAttribute("accountId");
        String Role = (String) session.getAttribute("ROLE");

        if (loginId == null) return "redirect:/login";

        AccountDTO userInfo = service.getUserInfo(loginId);
        model.addAttribute("userInfo", userInfo);

        param.put("customer_id", loginId);
        InquiryDTO inquiryView = service.inquiryView(param);
        model.addAttribute("inquiryView", inquiryView);
        model.addAttribute("role", Role);
        return "inquiry/inquiry_view";
    }

    // 5. 관리자 답변 작성 페이지 진입
    @RequestMapping("/reply_write")
    public String reply_write(@RequestParam HashMap<String, String> param, HttpSession session, Model model) {
        log.info("replyWrite()");
        String loginId = (String) session.getAttribute("accountId");

        if (loginId == null) return "redirect:/login";

        AccountDTO userInfo = service.getUserInfo(loginId);
        model.addAttribute("userInfo", userInfo);

        InquiryDTO inquiryView = service.inquiryView(param);
        model.addAttribute("reply", inquiryView);

        return "inquiry/reply_write";
    }

    // 6. 관리자 답변 등록 처리
    @RequestMapping("/replyProcess")
    public String replyProcess(@RequestParam HashMap<String, String> param, HttpSession session, RedirectAttributes attr) {
        log.info("replyProcess()");
        String loginId = (String) session.getAttribute("accountId");

        if (loginId == null) return "redirect:/login";

        service.replyProcess(param);
        attr.addAttribute("inquiry_no", param.get("inquiry_no"));

        return "redirect:/inquiry/inquiry_view";
    }

    // 7. 관리자 문의 관리 페이지
    @RequestMapping("/inquiry_manage")
    public String inquiry_manage(@RequestParam HashMap<String, String> param, Model model,
                                 HttpSession session, Criteria cri) {
        log.info("inquiryManage()");
        String loginId = (String) session.getAttribute("accountId");

        if (loginId == null) return "redirect:/login";

        AccountDTO userInfo = service.getUserInfo(loginId);
        model.addAttribute("userInfo", userInfo);

        List<InquiryDTO> inquiryManageList = service.inquiryManageList(param, cri);
        model.addAttribute("ManageList", inquiryManageList);

        int total = service.TotalInquiry();
        model.addAttribute("pageMaker", new PagingDTO(total, cri));

        return "inquiry/inquiry_manage";
    }
}