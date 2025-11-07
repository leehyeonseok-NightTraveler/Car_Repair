package com.boot.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Admin_Controller {

    // 관리자 모드 진입 페이지
    @GetMapping("/admin/enter")
    public String adminEnterPage() {
        return "admin_enter"; // /WEB-INF/views/admin_enter.jsp
    }

    // 비밀번호 확인 → 세션 설정
    @PostMapping("/admin/verify")
    public String verifyAdmin(@RequestParam("adminKey") String adminKey,
                              HttpSession session,
                              RedirectAttributes rttr) {

        String correctPw = "1234";

        if ("1234".equals(adminKey)) {
            session.setAttribute("ROLE", "ADMIN");
            rttr.addFlashAttribute("msg", "관리자 모드가 활성화되었습니다.");
            return "redirect:/main";
        } else {
            rttr.addFlashAttribute("msg", "비밀번호가 올바르지 않습니다.");
            return "redirect:/admin/enter";
        }
    }



    // 관리자 모드 해제
    @GetMapping("/admin/exit")
    public String exitAdminMode(HttpSession session, RedirectAttributes rttr) {
        session.removeAttribute("ROLE");
        rttr.addFlashAttribute("msg", "관리자 모드가 해제되었습니다.");
        return "redirect:/main";
    }
}
