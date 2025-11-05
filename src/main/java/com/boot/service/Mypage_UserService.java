package com.boot.service;

import com.boot.dto.AccountDTO;

public interface Mypage_UserService {
    AccountDTO getUserInfo(String accountId);
    int updateUserInfo(AccountDTO account);
}
