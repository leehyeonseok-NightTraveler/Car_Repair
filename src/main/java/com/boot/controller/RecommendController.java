package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; // <-- 이 줄이 추가되었습니다.

import com.boot.dto.RecommendDTO;
import com.boot.service.RecommendService;

@Controller
public class RecommendController {
	@Autowired
	private RecommendService recommendService;

	@GetMapping("/recommend")
	public String showRecommendPage(Model model) {
	    List<RecommendDTO> list = recommendService.selectMapList();
	    System.out.println("조회된 수리점 수: " + list.size());  // ← 이거 추가!
	    for (RecommendDTO dto : list) {
	        System.out.println(dto.getStoreId() + " | " + dto.getLatitude() + ", " + dto.getLongitude());
	    }
	    model.addAttribute("locations", list);
	    return "recommend";
	}
}
