package com.boot.service;

import com.boot.dao.RecommendDAO;
import com.boot.dto.RecommendDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private RecommendDAO recommendDAO;

    @Override
    public ArrayList<RecommendDTO> selectMapList() {
        List<RecommendDTO> list = new ArrayList<>();
        String jsonPath = "static/data/전국자동차정비업체표준데이터.json";

        try {
            ClassPathResource resource = new ClassPathResource(jsonPath);
            InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS);
            mapper.enable(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER);

            JsonNode root = mapper.readTree(reader);
            JsonNode records = root.path("records");

            if (!records.isArray()) {
                for (Iterator<String> it = root.fieldNames(); it.hasNext();) {
                    String key = it.next();
                    JsonNode candidate = root.path(key);
                    if (candidate.isArray()) {
                        records = candidate;
                        break;
                    }
                }
            }

            if (records.isArray()) {
                for (JsonNode node : records) {
                    // 🔹 영업중인 업체만
                    String status = node.path("영업상태").asText("");
                    if (!"1".equals(status)) continue;

                    String name = node.path("자동차정비업체명").asText("").trim();
                    String addr = node.path("소재지도로명주소").asText("").trim();
                    if (addr.isEmpty()) addr = node.path("소재지지번주소").asText("").trim();

                    String phone = node.path("전화번호").asText("").trim();
                    String latStr = node.path("위도").asText("").trim();
                    String lngStr = node.path("경도").asText("").trim();

                    if (name.isEmpty() || latStr.isEmpty() || lngStr.isEmpty()) continue;

                    double lat, lng;
                    try {
                        lat = Double.parseDouble(latStr);
                        lng = Double.parseDouble(lngStr);
                    } catch (NumberFormatException e) {
                        continue;
                    }

                    String open = node.path("운영시작시각").asText("");
                    String close = node.path("운영종료시각").asText("");
                    String hours = (open.isEmpty() && close.isEmpty()) ? "" : open + " ~ " + close;

                    RecommendDTO dto = new RecommendDTO();
                    dto.setStoreId(name);
                    dto.setAddress(addr);
                    dto.setPhoneNumber(phone);
                    dto.setLatitude(lat);
                    dto.setLongitude(lng);
                    dto.setOpeningHours(hours);
                    list.add(dto);
                    
                    
                }
            }

            System.out.println("[INFO] JSON 파싱 완료. 결과 개수: " + list.size());

        } catch (Exception e) {
            System.err.println("[ERROR] JSON 읽기 실패: " + e.getMessage());
            e.printStackTrace();
        }

        return new ArrayList<>(list);
    }

    @Override public int insertCoordinate(RecommendDTO dto) { return recommendDAO.insertCoordinate(dto); }
    @Override public int selectListCount(RecommendDTO dto) { return recommendDAO.selectListCount(dto); }
    @Override public int updateCoordinate(RecommendDTO dto) { return recommendDAO.updateCoordinate(dto); }
    
    
}
