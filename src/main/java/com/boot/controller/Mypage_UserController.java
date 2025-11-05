package com.boot.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; // <-- 이 줄이 추가되었습니다.
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.AccountDTO;
import com.boot.dto.RecommendDTO;
import com.boot.service.Mypage_UserService;
import com.boot.service.RecommendService;

@Controller
@RequestMapping("/mypage")
public class Mypage_UserController {

    @Autowired
    private Mypage_UserService service;

    @GetMapping
    public String viewMypage(HttpSession session, Model model) {
        String accountId = (String) session.getAttribute("accountId");
        if (accountId == null) return "redirect:/login";

        AccountDTO user = service.getUserInfo(accountId);
        model.addAttribute("user", user);
        return "mypage_user"; // JSP 파일명에 맞게
    }

    @PostMapping("/update")
    public String updateProfile(AccountDTO dto, HttpSession session, RedirectAttributes rttr) {
        String accountId = (String) session.getAttribute("accountId");
        dto.setAccountId(accountId);

        int result = service.updateUserInfo(dto);
        rttr.addFlashAttribute("msg", result > 0 ? "정보 수정 완료" : "수정 실패");
        return "redirect:/mypage";
    }
}
