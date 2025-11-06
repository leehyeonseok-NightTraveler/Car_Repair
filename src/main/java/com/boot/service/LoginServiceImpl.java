package com.boot.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder; // 1. PasswordEncoder import 삭제
import org.springframework.stereotype.Service;

import com.boot.dao.LoginDAO;
import com.boot.dto.LoginDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDAO loginDAO;
    
    @Autowired
    private SqlSession sqlSession;

    // 2. PasswordEncoder 주입(@Autowired) 부분 삭제
    // @Autowired
    // private PasswordEncoder passwordEncoder; 

    @Override
    public void register(LoginDTO accountDTO) {
        log.info("암호화 없이 원본 DTO를 DB로 전달합니다.");
        
        // 3. 암호화 로직 전체 삭제
        /*
        String rawPassword = accountDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        accountDTO.setPassword(encodedPassword);
        */
        
        // 4. 폼에서 받은 DTO를 DAO로 그대로 전달하여 DB에 저장
        // (비밀번호가 "1234"라면 "1234"가 그대로 DB에 저장됨)
        loginDAO.register(accountDTO);
    }

	@Override
	public ArrayList<LoginDTO> loginYn(HashMap<String, String> param) {
		
		LoginDAO dao = sqlSession.getMapper(LoginDAO.class);
		ArrayList<LoginDTO> list = dao.loginYn(param);
		return list;
	}

	
}