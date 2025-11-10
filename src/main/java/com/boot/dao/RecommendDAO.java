package com.boot.dao;

import java.util.ArrayList;
import com.boot.dto.RecommendDTO;

// DB 직접 접근은 거의 안하지만 남겨둠
public interface RecommendDAO {
    int insertCoordinate(RecommendDTO dto);
    int selectListCount(RecommendDTO dto);
    int updateCoordinate(RecommendDTO dto);
    ArrayList<RecommendDTO> selectMapList();
}
