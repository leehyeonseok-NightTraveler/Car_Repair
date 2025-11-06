package com.boot.service;

import com.boot.dao.InquiryDAO;
import com.boot.dao.NoticeDAO;
import com.boot.dto.AccountDTO;
import com.boot.dto.InquiryDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void writeProcess(HashMap<String, String> param) {
        InquiryDAO dao = sqlSession.getMapper(InquiryDAO.class);
        dao.writeProcess(param);
    }

    @Override
    public List<InquiryDTO> inquiryList(HashMap<String, String> param) {
        InquiryDAO dao = sqlSession.getMapper(InquiryDAO.class);
        return dao.inquiryList(param);
    }

    @Override
    public InquiryDTO inquiryView(HashMap<String, String> param) {
        InquiryDAO dao = sqlSession.getMapper(InquiryDAO.class);
        return dao.inquiryView(param);
    }

    @Override
    public AccountDTO getUserInfo(String accountId) {
        NoticeDAO dao = sqlSession.getMapper(NoticeDAO.class);
        return dao.getUserInfo(accountId);
    }

    @Override
    public void replyProcess(HashMap<String, String> param) {
        InquiryDAO dao = sqlSession.getMapper(InquiryDAO.class);
        dao.replyProcess(param);
    }
}
