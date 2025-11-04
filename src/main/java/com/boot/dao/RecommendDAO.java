package com.boot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.boot.dto.RecommendDTO;

@Mapper
public interface RecommendDAO {
    int insertCoordinate(RecommendDTO dto);
    int selectListCount(RecommendDTO dto);
    int updateCoordinate(RecommendDTO dto);
    ArrayList<RecommendDTO> selectMapList();
}