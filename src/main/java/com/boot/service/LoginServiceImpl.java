package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.LoginDAO;
import com.boot.dto.LoginDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void register(LoginDTO accountDTO) {
        log.info("암호화 없이 원본 DTO를 DB로 전달합니다.");
        LoginDAO dao = sqlSession.getMapper(LoginDAO.class);
        dao.register(accountDTO);
    }

    @Override
    public ArrayList<LoginDTO> loginYn(HashMap<String, String> param) {
        LoginDAO dao = sqlSession.getMapper(LoginDAO.class);
        ArrayList<LoginDTO> list = dao.loginYn(param);

        if (list.isEmpty()) {
            log.warn("로그인 실패: 아이디 불일치");
            return null;
        }

        LoginDTO user = list.get(0); // 첫 번째 결과 사용

        // ✅ 상태값 체크 추가
        if ("PENDING".equalsIgnoreCase(user.getAccountStatus())) {
            log.warn("로그인 차단: 승인 대기 중인 계정");
            throw new IllegalStateException("관리자 승인 대기 중인 계정입니다.");
        }

        if ("SUSPENDED".equalsIgnoreCase(user.getAccountStatus())) {
            log.warn("로그인 차단: 정지된 계정");
            throw new IllegalStateException("정지된 계정입니다. 관리자에게 문의하세요.");
        }

        if ("DELETED".equalsIgnoreCase(user.getAccountStatus())) {
            log.warn("로그인 차단: 탈퇴된 계정");
            throw new IllegalStateException("이미 탈퇴된 계정입니다.");
        }

        log.info("로그인 성공: {}", user.getAccountId());
        return list;
    }
}
