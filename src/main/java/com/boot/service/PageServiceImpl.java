package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.PageDAO;
import com.boot.dto.FaQDTO;
import com.boot.dto.Criteria;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PageServiceImpl implements PageService{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<FaQDTO> listWithPaging(Criteria cri) {
		log.info("@# listWithPaging()");
		log.info("@# cri=>"+cri);
		
		PageDAO dao = sqlSession.getMapper(PageDAO.class);
		ArrayList<FaQDTO> list = dao.listWithPaging(cri);
		
		return list;
	}

	@Override
//	public int getTotalCount() {
	public int getTotalCount(Criteria cri) {
		PageDAO dao = sqlSession.getMapper(PageDAO.class);
//		int total = dao.getTotalCount();
		int total = dao.getTotalCount(cri);
		
		return total;
	}

	@Override
	public FaQDTO getFaqDetail(int faq_no) {
		log.info("@# getFaqDetail");
		PageDAO dao = sqlSession.getMapper(PageDAO.class); 
	    return dao.getFaqDetail(faq_no);
	}

	
}

















