package com.boot.service;

import com.boot.dao.InquiryDAO;
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
    public List<InquiryDTO> inquiryList() {
        InquiryDAO dao = sqlSession.getMapper(InquiryDAO.class);
        return dao.inquiryList();
    }

    @Override
    public InquiryDTO inquiryView(HashMap<String, String> param) {
        InquiryDAO dao = sqlSession.getMapper(InquiryDAO.class);
        return dao.inquiryView(param);
    }
}
