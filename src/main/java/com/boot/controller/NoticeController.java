package com.boot.controller;

import com.boot.dto.Criteria;
import com.boot.dto.NoticeDTO;
import com.boot.dto.PagingDTO;
import com.boot.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/notice") // ✅ 공통 경로 설정
public class NoticeController {

    @Autowired
    private NoticeService service;

    // [1] 공지사항 목록 조회
    @RequestMapping("/notice_list")
    public String noticeList(Model model, Criteria criteria) {
        log.info("notice_list");

        List<NoticeDTO> notice_list = service.notice_list(criteria);
        int total = service.notice_total(criteria);

        model.addAttribute("list", notice_list);
        model.addAttribute("pageMaker", new PagingDTO(total, criteria));

        return "notice/notice_list"; // ✅ JSP 경로
    }

    // [2] 공지사항 상세 보기
    @RequestMapping("/notice_view")
    public String content_view(Model model, @RequestParam HashMap<String, String> param) {
        log.info("content_view()");

        int noticeNo = Integer.parseInt(param.get("notice_no"));
        service.increase_views(noticeNo);

        NoticeDTO view = service.notice_view(param);

        model.addAttribute("view", view);
        model.addAttribute("pageMaker", param);

        return "notice/notice_view";
    }

    // [3] 공지사항 작성 페이지 이동
    @RequestMapping("/notice_write")
    public String notice_write() {
        log.info("notice_write()");
        return "notice/notice_write";
    }

    // [4] 공지사항 작성 처리
    @PostMapping("/write_process")
    public String write_process(@RequestParam HashMap<String, String> param, Model model) {
        log.info("write_process()");
        service.notice_write(param);
        return "redirect:/notice/notice_list"; // ✅ redirect 경로
    }

    // [5] 공지사항 수정 페이지 이동
    @RequestMapping("/notice_modify")
    public String notice_modify(@RequestParam HashMap<String, String> param, Model model) {
        log.info("notice_modify()");
        NoticeDTO view = service.notice_view(param);
        model.addAttribute("modify", view);
        model.addAttribute("pageMaker", param);
        return "notice/notice_modify";
    }

    // [6] 공지사항 수정 처리
    @PostMapping("/modify_process")
    public String modify_process(@RequestParam HashMap<String, String> param) {
        log.info("modify_process()");
        service.notice_modify(param);

        return "redirect:/notice/notice_list";
    }

    // [7] 공지사항 삭제 처리
    @PostMapping("/delete_process")
    public String delete_process(@RequestParam HashMap<String, String> param) {
        log.info("delete_process()");
        service.notice_delete(param);
        return "redirect:/notice/notice_list";
    }
}