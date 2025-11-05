package com.boot.service;

import com.boot.dao.NoticeDAO;
import com.boot.dto.Criteria;
import com.boot.dto.NoticeDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<NoticeDTO> noticeList(Criteria cri) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        return dao.noticeList(cri);
    }

    @Override
    public NoticeDTO noticeView(HashMap<String, String> param) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        return dao.noticeView(param);
    }

    @Override
    public void writeProcess(HashMap<String, String> param) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        dao.writeProcess(param);
    }

    @Override
    public void modifyProcess(HashMap<String, String> param) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        dao.modifyProcess(param);
    }

    @Override
    public void deleteProcess(HashMap<String, String> param) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        dao.deleteProcess(param);
    }

    @Override
    public int getTotalCount() {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        return dao.getTotalCount();
    }

    @Override
    public void increaseViews(HashMap<String, String> param) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        dao.increaseViews(param);
    }
}
