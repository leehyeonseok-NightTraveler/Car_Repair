package com.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerHelpServiceImpl implements CustomerHelpService {

    @Autowired
    private SqlSession sqlSession;

}
