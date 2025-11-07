package com.boot.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.AccountDTO;
import com.boot.dto.InquiryDTO;
import com.boot.service.AccountService;
import com.boot.service.InquiryService;
import com.boot.service.Mypage_UserService;

@Controller
@RequestMapping("/mypage")
public class Mypage_UserController {

    @Autowired
    private Mypage_UserService service;

    @Autowired
    private InquiryService inquiryService;

    // 마이페이지 진입
    @GetMapping
    public String viewMypage(HttpSession session, Model model) {
        String accountId = (String) session.getAttribute("accountId");
        if (accountId == null) return "redirect:/login";

        // 1. 회원 정보 조회
        AccountDTO user = service.getUserInfo(accountId);
        model.addAttribute("user", user);

        // 2. 1:1 문의 내역 조회
        List<InquiryDTO> inquiryList = inquiryService.selectByAccountId(accountId);
        model.addAttribute("inquiryList", inquiryList);

        return "mypage_user"; // JSP 파일명
    }

    // 회원 정보 수정
    @PostMapping("/update")
    public String updateProfile(AccountDTO dto, HttpSession session, RedirectAttributes rttr) {
        String accountId = (String) session.getAttribute("accountId");
        dto.setAccountId(accountId);

        // 기본 정보 수정
        int result = service.updateUserInfo(dto);

        // 비밀번호 변경 로직 추가
        if (dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()) {
            boolean pwChanged = service.updatePassword(accountId, dto.getCurrentPassword(), dto.getNewPassword());
            if (!pwChanged) {
                rttr.addFlashAttribute("msg", "현재 비밀번호가 일치하지 않습니다.");
                return "redirect:/mypage";
            }
        }

        rttr.addFlashAttribute("msg", result > 0 ? "정보 수정 완료" : "수정 실패");
        return "redirect:/mypage";
    }

}
