package com.boot.service;

import java.util.List;
import com.boot.dto.AccountDTO;
import com.boot.dto.StoreDTO;

public interface Mypage_AdminService {

    // 회원 전체 조회
    List<AccountDTO> getAllUsers();

    // 승인 대기 업체 조회
    List<StoreDTO> getPendingStores();

    // 회원 상태 변경 (ACTIVE / SUSPENDED / DELETED)
    void updateAccountStatus(String accountId, String status);

    // 업체 승인/거절 (APPROVED / REJECTED)
    void updateStoreStatus(String storeId, String status);
}
