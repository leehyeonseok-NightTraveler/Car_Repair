package com.boot.dao;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.StoreDTO;

@Mapper
public interface Mypage_StoreDAO {

    StoreDTO getStoreInfo(String storeId);
    int updateStoreInfo(StoreDTO store);
}
