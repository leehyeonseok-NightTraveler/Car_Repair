package com.boot.service;

import java.util.List;
import com.boot.dto.AccountDTO;
import com.boot.dto.StoreDTO;

public interface Mypage_AdminService {

    List<AccountDTO> getAllUsers();
    List<StoreDTO> getPendingStores();

    void updateAccountStatus(String accountId, String status);
    void updateStoreStatus(String storeId, String status);

    // 추가
    void downgradeAdmin(String accountId);
}
