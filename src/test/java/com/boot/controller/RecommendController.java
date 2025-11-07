package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.dto.RecommendDTO;
import com.boot.service.RecommendService;

@Controller
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    //  지도 페이지
    @GetMapping("/recommend")
    public String showRecommendPage() {
        return "recommend";
    }

    //  JSON API (지역 필터링 포함)
    @GetMapping("/api/recommend")
    @ResponseBody
    public List<RecommendDTO> getRecommendData(@RequestParam(value = "region", required = false) String region) {
        List<RecommendDTO> list = recommendService.selectMapList();

        //  지역 필터링 적용
        if (region != null && !region.isEmpty()) {
            list.removeIf(dto -> dto.getAddress() == null || !dto.getAddress().contains(region));
            System.out.println("[INFO] 지역 필터 적용됨: " + region + " / 결과 수: " + list.size());
        }
        
        // 전체 로딩 시 데이터 제한
        if (region == null || region.isEmpty()) {
            if (list.size() > 500) {
                list = list.subList(0, 500);
                System.out.println("[WARN] 전체 요청 → 500개만 로드");
            }
        }


        System.out.println("[INFO] API 호출됨: 총 데이터 개수 = " + list.size());
        return list;
    }
}
