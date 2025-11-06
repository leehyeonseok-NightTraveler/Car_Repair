package com.boot.dao;

import com.boot.dto.InquiryDTO;

import java.util.HashMap;
import java.util.List;

public interface InquiryDAO {
    void writeProcess(HashMap<String, String> param);
    List<InquiryDTO> inquiryList();
    InquiryDTO inquiryView(HashMap<String, String> param);
}
