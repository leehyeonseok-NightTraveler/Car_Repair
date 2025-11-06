package com.boot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.service.AdminService; // AdminService 주입

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService; // AdminService 주입

    /**
     * 관리자 승급 페이지(JSP)를 엽니다.
     */
    @GetMapping("/promote_admin")
    public String promoteAdminForm(HttpSession session) {
        // 로그인한 사용자만 접근 가능
        if (session.getAttribute("loginId") == null) {
            return "redirect:/login"; // 로그인 안 했으면 로그인 페이지로
        }
        
        log.info("@# GET /promote_admin (관리자 승급 페이지 열기)");
        return "promote_admin"; // /WEB-INF/views/promote_admin.jsp
    }
    
    /**
     * 관리자 키를 받아 승급을 처리합니다.
     */
    @PostMapping("/promoteAdminProc")
    public String promoteAdminProc(@RequestParam("adminKey") String adminKey, 
                                 HttpSession session, 
                                 RedirectAttributes rttr) {
        
        log.info("@# POST /promoteAdminProc (관리자 승급 처리)");
        
        String loginId = (String) session.getAttribute("loginId");
        if (loginId == null) {
            return "redirect:/login"; // 세션 만료
        }

        // AdminService를 호출하여 로직 처리
        boolean isSuccess = adminService.upgradeToAdmin(loginId, adminKey);

        if (isSuccess) {
            // 3. 세션 정보도 'ADMIN'으로 갱신 (★중요★)
            session.setAttribute("role", "ADMIN"); 
            
            rttr.addFlashAttribute("success_msg", "관리자 권한으로 승급되었습니다!");
            return "redirect:/"; // 성공 시 메인 페이지로
        } else {
            rttr.addFlashAttribute("error_msg", "관리자 키가 일치하지 않습니다.");
            return "redirect:/promote_admin";
        }
    }
}