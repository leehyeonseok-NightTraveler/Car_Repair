package com.boot.controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;

import com.boot.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.service.InquiryService;
import com.boot.service.LoginService;
import com.boot.service.Mypage_UserService;

@Controller
@RequestMapping("/mypage_user")
public class Mypage_UserController {

    @Autowired
    private Mypage_UserService service;

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String viewMypage(@RequestParam HashMap<String, String> param, Criteria cri, HttpSession session, Model model, RedirectAttributes rttr) {
        String accountId = (String) session.getAttribute("accountId");

        if (accountId == null) {
            rttr.addFlashAttribute("error_msg", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        LoginDTO loginUser = loginService.findByAccountId(accountId);
        if (loginUser == null || "DELETED".equalsIgnoreCase(loginUser.getAccountStatus())) {
            session.invalidate();
            rttr.addFlashAttribute("error_msg", "삭제된 계정으로 접근할 수 없습니다.");
            return "redirect:/login";
        }

        // 1. 회원 정보 조회
        AccountDTO userInfo = service.getUserInfo(accountId);
        model.addAttribute("user", userInfo);

        // 로그인 및 권한 확인
        String userId = (String) session.getAttribute("accountId");
        String storeId = (String) session.getAttribute("storeId");
        String Role = (String) session.getAttribute("ROLE");

        // 사용자 유형에 따라 customer_id 설정
        String loginId;
        if ("USER".equals(Role)) {
            param.put("customer_id", userId);
            loginId = userId;
        } else {
            param.put("customer_id", storeId);
            loginId = storeId;
        }

        // 문의 목록 조회
        List<InquiryDTO> inquiryList = inquiryService.inquiryList(param, cri);
        model.addAttribute("inquiryList", inquiryList);

        // 페이징 처리
        int total = inquiryService.TotalInquiryUser(loginId, cri);
        model.addAttribute("pageMaker", new PagingDTO(total, cri));

        return "mypage/mypage_user";
    }

    @GetMapping("/mypage_useredit")
    public String editProfile(HttpSession session, Model model) {
        String accountId = (String) session.getAttribute("accountId");
        if (accountId == null) return "redirect:/login";

        AccountDTO user = service.getUserInfo(accountId);
        model.addAttribute("user", user);

        return "mypage/mypage_useredit";  // JSP 경로 정확히 지정
    }


    // 회원 정보 수정
    @PostMapping("/update")
    public String updateProfile(AccountDTO dto, HttpSession session, RedirectAttributes rttr) {
        String accountId = (String) session.getAttribute("accountId");
        dto.setAccountId(accountId);

        // 기본 정보 수정
        int result = service.updateUserInfo(dto);

        // 비밀번호 변경 로직
        if (dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()) {
            boolean pwChanged = service.updatePassword(accountId, dto.getCurrentPassword(), dto.getNewPassword());
            if (!pwChanged) {
                rttr.addFlashAttribute("msg", "현재 비밀번호가 일치하지 않습니다.");
                return "redirect:/mypage_user";  // 수정됨
            }
        }

        rttr.addFlashAttribute("msg", result > 0 ? "정보 수정 완료" : "수정 실패");
        return "redirect:/mypage_user";  // 수정됨
    }
}
