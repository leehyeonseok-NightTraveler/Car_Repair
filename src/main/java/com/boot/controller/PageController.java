package com.boot.controller;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.FaQDTO;
import com.boot.dto.Criteria;
import com.boot.dto.PageDTO;
import com.boot.service.PageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PageController {
	
	@Autowired
	private PageService service;
	
	@RequestMapping("/faq")
	public String faq(Criteria cri, Model model) {
		log.info("@# faq()");
		log.info("@# cri=>"+cri);
		
		ArrayList<FaQDTO> list = service.listWithPaging(cri);
		int total = service.getTotalCount(cri);
		log.info("@# total=>"+total);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", new PageDTO(total, cri));
		
		return "faq";
	}
	@RequestMapping("/faq_view")
	public String faqView(@RequestParam("faq_no") int faq_no, Model model) {
	    log.info("@# faqView() with no: " + faq_no);
	    FaQDTO faqDto = service.getFaqDetail(faq_no);
	    model.addAttribute("faq", faqDto);
	    return "faq_view"; // "faq_answer.jsp"로 연결
	}
	
}









