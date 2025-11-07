package com.boot.service;

import com.boot.dto.StoreDTO;

public interface Mypage_StoreService {
    StoreDTO getStoreInfo(String storeId);
    int updateStoreInfo(StoreDTO store);
}
