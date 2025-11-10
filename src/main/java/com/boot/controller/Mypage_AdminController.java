package com.boot.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.AccountDTO;
import com.boot.dto.StoreDTO;
import com.boot.service.Mypage_AdminService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class Mypage_AdminController {

    @Autowired
    private Mypage_AdminService adminService;

    /** 관리자 마이페이지 (회원/업체 관리) */
    @GetMapping("/mypage_admin")
    public String adminDashboard(Model model, HttpSession session) {
        log.info("@# GET /mypage_admin");

        // 세션 체크
        String role = (String) session.getAttribute("ROLE");
        if (role == null || !role.equals("ADMIN")) {
            return "redirect:/login";
        }

        // 회원 목록
        List<AccountDTO> userList = adminService.getAllUsers();
        // 승인 대기중 업체 목록
        List<StoreDTO> pendingStores = adminService.getPendingStores();

        model.addAttribute("userList", userList);
        model.addAttribute("pendingStores", pendingStores);
        return "mypage/mypage_admin";
    }

    /** 회원 상태 변경 */
    @PostMapping("/mypage/user/updateStatus")
    public String updateUserStatus(@RequestParam("accountId") String accountId,
                                   @RequestParam("status") String status,
                                   HttpSession session,
                                   RedirectAttributes rttr) {
        log.info("@# updateUserStatus({}, {})", accountId, status);
        adminService.updateAccountStatus(accountId, status);

        //  관리자 자신이 자기 계정을 삭제한 경우, 세션 만료 처리
        String currentUser = (String) session.getAttribute("accountId");
        if (currentUser != null && currentUser.equals(accountId) && "DELETED".equals(status)) {
            session.invalidate();
            rttr.addFlashAttribute("error_msg", "계정이 삭제되어 자동 로그아웃되었습니다.");
            return "redirect:/login";
        }

        return "redirect:/mypage_admin";
    }


    /** 업체 승인/거절 처리 */
    @PostMapping("/mypage/store/updateStatus")
    public String updateStoreStatus(@RequestParam("storeId") String storeId,
                                    @RequestParam("status") String status) {
        log.info("@# updateStoreStatus({}, {})", storeId, status);
        adminService.updateStoreStatus(storeId, status);
        return "redirect:/mypage_admin";
    }

    /** 🔽 관리자 본인 권한 해제 */
    @PostMapping("/mypage/admin/demote")
    public String demoteAdmin(HttpSession session, RedirectAttributes rttr) {
        String loginId = (String) session.getAttribute("accountId");
        if (loginId == null) {
            rttr.addFlashAttribute("error_msg", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        try {
            adminService.downgradeAdmin(loginId);
            session.setAttribute("ROLE", "USER");
            rttr.addFlashAttribute("success_msg", "관리자 권한이 해제되어 일반 회원으로 돌아갑니다.");
        } catch (Exception e) {
            log.error("관리자 권한 해제 오류: {}", e.getMessage());
            rttr.addFlashAttribute("error_msg", "권한 해제 중 오류가 발생했습니다.");
        }

        return "redirect:/main";
    }
}
