package com.boot.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.Mypage_AdminDAO;
import com.boot.dto.AccountDTO;
import com.boot.dto.StoreDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Mypage_AdminServiceImpl implements Mypage_AdminService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<AccountDTO> getAllUsers() {
        log.info("@# getAllUsers()");
        Mypage_AdminDAO dao = sqlSession.getMapper(Mypage_AdminDAO.class);
        return dao.getAllUsers();
    }

    @Override
    public List<StoreDTO> getPendingStores() {
        log.info("@# getPendingStores()");
        Mypage_AdminDAO dao = sqlSession.getMapper(Mypage_AdminDAO.class);
        return dao.getPendingStores();
    }

    @Override
    public void updateAccountStatus(String accountId, String status) {
        log.info("@# updateAccountStatus({}, {})", accountId, status);
        Mypage_AdminDAO dao = sqlSession.getMapper(Mypage_AdminDAO.class);
        dao.updateAccountStatus(accountId, status);
    }

    @Override
    public void updateStoreStatus(String storeId, String status) {
        log.info("@# updateStoreStatus({}, {})", storeId, status);
        Mypage_AdminDAO dao = sqlSession.getMapper(Mypage_AdminDAO.class);
        dao.updateStoreStatus(storeId, status);
    }
}
