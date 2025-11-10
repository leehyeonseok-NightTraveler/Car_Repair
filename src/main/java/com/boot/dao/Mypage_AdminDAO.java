package com.boot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.boot.dto.AccountDTO;
import com.boot.dto.StoreDTO;

@Mapper
public interface Mypage_AdminDAO {

    List<AccountDTO> getAllUsers();

    List<StoreDTO> getPendingStores();

    void updateAccountStatus(
        @Param("accountId") String accountId,
        @Param("status") String status
    );

    void updateStoreStatus(
        @Param("storeId") String storeId,
        @Param("status") String status
    );
}
