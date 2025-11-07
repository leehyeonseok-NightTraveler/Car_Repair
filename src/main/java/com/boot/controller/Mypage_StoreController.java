package com.boot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.StoreDTO;
import com.boot.service.Mypage_StoreService;

@Controller
@RequestMapping("/mypage_store")
public class Mypage_StoreController {

    @Autowired
    private Mypage_StoreService service;

    // 마이페이지 진입
    @GetMapping
    public String viewStoreMypage(HttpSession session, Model model) {
        String storeId = (String) session.getAttribute("storeId");
        if (storeId == null) {
            return "redirect:/storelogin"; // 로그인 안되어있으면 로그인 페이지로
        }

        StoreDTO store = service.getStoreInfo(storeId);
        model.addAttribute("store", store);
        return "mypage_store";
    }

    // 업체 정보 수정
    @PostMapping("/update")
    public String updateStoreInfo(StoreDTO dto, HttpSession session, RedirectAttributes rttr) {
        String storeId = (String) session.getAttribute("storeId");
        dto.setStoreId(storeId);

        int result = service.updateStoreInfo(dto);
        rttr.addFlashAttribute("msg", result > 0 ? "정보 수정 완료" : "수정 실패");
        return "redirect:/mypage_store";
    }
}
