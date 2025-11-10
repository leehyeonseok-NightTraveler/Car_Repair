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
        return sqlSession.getMapper(Mypage_AdminDAO.class).getAllUsers();
    }

    @Override
    public List<StoreDTO> getPendingStores() {
        log.info("@# getPendingStores()");
        return sqlSession.getMapper(Mypage_AdminDAO.class).getPendingStores();
    }

    @Override
    public void updateAccountStatus(String accountId, String status) {
        log.info("@# updateAccountStatus({}, {})", accountId, status);
        sqlSession.getMapper(Mypage_AdminDAO.class).updateAccountStatus(accountId, status);
    }

    @Override
    public void updateStoreStatus(String storeId, String status) {
        log.info("@# updateStoreStatus({}, {})", storeId, status);
        sqlSession.getMapper(Mypage_AdminDAO.class).updateStoreStatus(storeId, status);
    }

    /** 🔽 관리자 권한 해제 (role을 USER로 변경) */
    @Override
    public void downgradeAdmin(String accountId) {
        log.info("@# downgradeAdmin() - {}", accountId);
        sqlSession.getMapper(Mypage_AdminDAO.class).updateAccountRole(accountId, "USER");
    }
}
