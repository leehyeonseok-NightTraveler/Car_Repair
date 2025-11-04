package com.boot.dao;

import java.util.ArrayList;
import com.boot.dto.RecommendDTO;

// @Mapper 제거 필수!
public interface RecommendDAO {
    int insertCoordinate(RecommendDTO dto);
    int selectListCount(RecommendDTO dto);
    int updateCoordinate(RecommendDTO dto);
    ArrayList<RecommendDTO> selectMapList();
}