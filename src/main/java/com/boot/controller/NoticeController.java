package com.boot.controller;

import com.boot.dto.AccountDTO;
import com.boot.dto.Criteria;
import com.boot.dto.NoticeDTO;
import com.boot.dto.PagingDTO;
import com.boot.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/notice") // 모든 요청 경로 앞에 /notice가 붙음
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 1. 공지사항 목록 페이지
     */
    @RequestMapping("/notice_list")
    public String noticeList(@RequestParam HashMap<String, String> param, Criteria cri,
                             Model model, HttpSession session) {
        log.info("noticeList()");

        String loginId = (String) session.getAttribute("accountId");
        if (loginId != null) {
            AccountDTO userInfo = noticeService.getUserInfo(loginId);
            model.addAttribute("userInfo", userInfo);
        }

        List<NoticeDTO> noticeList = noticeService.noticeList(param, cri);
        int total = noticeService.getTotalCount();

        model.addAttribute("list", noticeList);
        model.addAttribute("pageMaker", new PagingDTO(total, cri));

        return "notice/notice_list";
    }

    /**
     * 2. 공지사항 상세 보기
     */
    @RequestMapping("/notice_view")
    public String noticeView(@RequestParam HashMap<String, String> param, Criteria cri,
                             Model model, HttpSession session) {
        log.info("noticeView()");

        String loginId = (String) session.getAttribute("accountId");
        if (loginId != null) {
            AccountDTO userInfo = noticeService.getUserInfo(loginId);
            model.addAttribute("userInfo", userInfo);
        }

        noticeService.increaseViews(param); // 조회수 증가
        NoticeDTO noticeView = noticeService.noticeView(param);
        int total = noticeService.getTotalCount();

        model.addAttribute("view", noticeView);
        model.addAttribute("pageMaker", new PagingDTO(total, cri));

        return "notice/notice_view";
    }

    /**
     * 3. 공지사항 작성 페이지 이동 (로그인 필요)
     */
    @RequestMapping("/notice_write")
    public String noticeWrite(HttpSession session) {
        log.info("notice_write()");
        String loginId = (String) session.getAttribute("accountId");

        if (loginId == null) return "redirect:/login";

        return "notice/notice_write";
    }

    /**
     * 4. 공지사항 작성 처리
     */
    @RequestMapping("/writeProcess")
    public String writeProcess(@RequestParam HashMap<String, String> param, HttpSession session) {
        log.info("writeProcess()");
        String loginId = (String) session.getAttribute("accountId");

        if (loginId == null) return "redirect:/login";

        param.put("notice_writer", loginId);
        noticeService.writeProcess(param);

        return "redirect:/notice/notice_list";
    }

    /**
     * 5. 공지사항 수정 페이지 이동
     */
    @RequestMapping("/notice_modify")
    public String noticeModify(@RequestParam HashMap<String, String> param, Criteria cri,
                               Model model, HttpSession session) {
        log.info("noticeModify()");
        String loginId = (String) session.getAttribute("accountId");

        if (loginId == null) return "redirect:/login";

        NoticeDTO noticeModify = noticeService.noticeView(param);
        int total = noticeService.getTotalCount();

        model.addAttribute("modify", noticeModify);
        model.addAttribute("pageMaker", new PagingDTO(total, cri));

        return "notice/notice_modify";
    }

    /**
     * 6. 공지사항 수정 처리
     */
    @RequestMapping("/modifyProcess")
    public String modify(@RequestParam HashMap<String, String> param,
                         RedirectAttributes attr, HttpSession session) {
        log.info("modifyProcess()");
        String loginId = (String) session.getAttribute("accountId");

        if (loginId == null) return "redirect:/login";

        noticeService.modifyProcess(param);

        attr.addAttribute("pageNum", param.get("pageNum"));
        attr.addAttribute("amount", param.get("amount"));

        return "redirect:/notice/notice_list";
    }

    /**
     * 7. 공지사항 삭제 처리
     */
    @RequestMapping("/deleteProcess")
    public String deleteProcess(@RequestParam HashMap<String, String> param,
                                RedirectAttributes attr, HttpSession session) {
        log.info("deleteProcess()");
        String loginId = (String) session.getAttribute("accountId");

        if (loginId == null) return "redirect:/login";

        noticeService.deleteProcess(param);

        attr.addAttribute("pageNum", param.get("pageNum"));
        attr.addAttribute("amount", param.get("amount"));

        return "redirect:/notice/notice_list";
    }
}