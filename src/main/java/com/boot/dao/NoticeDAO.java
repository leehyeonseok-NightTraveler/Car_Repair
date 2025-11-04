package com.boot.dao;

import com.boot.dto.Criteria;
import com.boot.dto.NoticeDTO;

import java.util.ArrayList;
import java.util.HashMap;

public interface NoticeDAO {

    // 공지 목록 조회 (페이징)
    ArrayList<NoticeDTO> notice_list(Criteria criteria);

    // 공지 상세 조회
    NoticeDTO notice_view(HashMap<String, String> param);

    // 공지 작성
    void notice_write(HashMap<String, String> param);

    // 공지 수정
    void notice_modify(HashMap<String, String> param);

    // 공지 삭제
    void notice_delete(HashMap<String, String> param);

    // 전체 공지 수
    int notice_total(Criteria criteria);

    // 조회수 증가
    void increase_views(int notice_no);
}