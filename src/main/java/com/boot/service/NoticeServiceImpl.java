package com.boot.service;

import com.boot.dao.NoticeDAO;
import com.boot.dto.Criteria;
import com.boot.dto.NoticeDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service // 서비스 계층 등록
@Slf4j   // 로그 출력용
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private SqlSession sqlSession; // MyBatis SQL 세션

    // 공지 목록 조회
    @Override
    public ArrayList<NoticeDTO> notice_list(Criteria criteria) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        return dao.notice_list(criteria);
    }

    // 공지 상세 조회
    @Override
    public NoticeDTO notice_view(HashMap<String, String> param) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        return dao.notice_view(param);
    }

    // 공지 작성
    @Override
    public void notice_write(HashMap<String, String> param) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        dao.notice_write(param);
    }

    // 공지 수정
    @Override
    public void notice_modify(HashMap<String, String> param) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        dao.notice_modify(param);
    }

    // 공지 삭제
    @Override
    public void notice_delete(HashMap<String, String> param) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        dao.notice_delete(param);
    }

    // 전체 공지 수 조회
    @Override
    public int notice_total(Criteria criteria) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        return dao.notice_total(criteria);
    }

    // 조회수 증가
    @Override
    public void increase_views(int notice_no) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        dao.increase_views(notice_no);
    }
}