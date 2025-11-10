package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.AccountDTO;
import com.boot.dto.StoreDTO;
import com.boot.service.Mypage_AdminService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class Mypage_AdminController {

    @Autowired
    private Mypage_AdminService adminService;

    /**
     * 관리자 마이페이지 (회원 관리 + 업체 승인 요청)
     * JSP 경로: /WEB-INF/views/mypage/mypage_admin.jsp
     */
    @GetMapping("/mypage_admin")
    public String adminDashboard(Model model) {
        log.info("@# GET /mypage_admin");

        // 전체 회원 목록
        List<AccountDTO> userList = adminService.getAllUsers();

        // 승인 대기중인 업체 목록
        List<StoreDTO> pendingStores = adminService.getPendingStores();

        model.addAttribute("userList", userList);
        model.addAttribute("pendingStores", pendingStores);

        return "mypage/mypage_admin";
    }

    /**
     * 회원 상태 변경 (정지 / 삭제 등)
     */
    @PostMapping("/mypage/user/updateStatus")
    public String updateUserStatus(@RequestParam("accountId") String accountId,
                                   @RequestParam("status") String status) {
        log.info("@# POST /mypage/user/updateStatus - accountId={}, status={}", accountId, status);
        adminService.updateAccountStatus(accountId, status);
        return "redirect:/mypage_admin";
    }

    /**
     * 업체 승인 / 거절 처리
     */
    @PostMapping("/mypage/store/updateStatus")
    public String updateStoreStatus(@RequestParam("storeId") String storeId,
                                    @RequestParam("status") String status) {
        log.info("@# POST /mypage/store/updateStatus - storeId={}, status={}", storeId, status);
        adminService.updateStoreStatus(storeId, status);
        return "redirect:/mypage_admin";
    }
}
