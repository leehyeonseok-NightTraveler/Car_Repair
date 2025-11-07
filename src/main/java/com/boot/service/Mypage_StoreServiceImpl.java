package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dao.Mypage_StoreDAO;
import com.boot.dto.StoreDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Mypage_StoreServiceImpl implements Mypage_StoreService {

    @Autowired
    private Mypage_StoreDAO dao;

    @Override
    public StoreDTO getStoreInfo(String storeId) {
        return dao.getStoreInfo(storeId);
    }

    @Override
    public int updateStoreInfo(StoreDTO store) {
        return dao.updateStoreInfo(store);
    }
}
