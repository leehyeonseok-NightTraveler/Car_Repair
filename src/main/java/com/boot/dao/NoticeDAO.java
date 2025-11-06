package com.boot.dao;

import com.boot.dto.AccountDTO;
import com.boot.dto.Criteria;
import com.boot.dto.NoticeDTO;

import java.util.HashMap;
import java.util.List;

public interface NoticeDAO {

    // 공지사항 목록 조회 (페이징 포함)
    List<NoticeDTO> noticeList(HashMap<String, String> param, Criteria cri);

    // 공지사항 상세보기
    NoticeDTO noticeView(HashMap<String, String> param);

    // 공지사항 작성
    void writeProcess(HashMap<String, String> param);

    // 공지사항 수정
    void modifyProcess(HashMap<String, String> param);

    // 공지사항 삭제
    void deleteProcess(HashMap<String, String> param);

    // 전체 게시글 수 조회 (페이징용)
    int getTotalCount();

    // 조회수 증가
    void increaseViews(HashMap<String, String> param);

    AccountDTO getUserInfo(String accountId);
}