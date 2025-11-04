package com.boot.service;

import java.util.ArrayList; // (주의) ArrayList 임포트 필요

import com.boot.dao.RecommendDAO;
import com.boot.dto.RecommendDTO; // (주의) Location 임포트 필요
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // 오류 4: @Service 어노테이션 추가
public class RecommendServiceImpl implements RecommendService {
	
	// (주의) SqlSessionTemplate 대신 DAO를 주입받습니다.
		@Autowired
		private RecommendDAO recommendDAO;
		
		@Override
		public int insertCoordinate(RecommendDTO dto) {
	        // (주의) DAO의 메소드를 직접 호출합니다.
			return recommendDAO.insertCoordinate(dto);
		}

		@Override
		public int selectListCount(RecommendDTO dto) {
			return recommendDAO.selectListCount(dto);
		}

		@Override
		public int updateCoordinate(RecommendDTO dto) {
			return recommendDAO.updateCoordinate(dto);
		}

		@Override
		public ArrayList<RecommendDTO> selectMapList() {
			return recommendDAO.selectMapList();
		}
}