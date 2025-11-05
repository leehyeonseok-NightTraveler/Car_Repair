package com.boot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.AccountDTO;

@Mapper
public interface Mypage_UserDAO {

    // 1. 아이디 기준 회원정보 조회
    public AccountDTO getUserInfo(String accountId);

    // 2. 회원정보 수정 (이름, 이메일, 전화번호)
    public int updateUserInfo(AccountDTO account);
}