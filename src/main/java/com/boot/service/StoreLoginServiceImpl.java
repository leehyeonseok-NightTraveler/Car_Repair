package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.StoreLoginDAO;
import com.boot.dto.StoreLoginDTO;

@Service
public class StoreLoginServiceImpl implements StoreLoginService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<StoreLoginDTO> storeLoginYn(HashMap<String, String> param) {
		
		StoreLoginDAO dao = sqlSession.getMapper(StoreLoginDAO.class);
		ArrayList<StoreLoginDTO> list = dao.storeLoginYn(param);
		
		return list;
	}

}
