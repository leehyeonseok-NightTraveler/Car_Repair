package com.boot.service;

import com.boot.dto.InquiryDTO;

import java.util.HashMap;
import java.util.List;

public interface InquiryService {
    void writeProcess(HashMap<String, String> param);
    List<InquiryDTO> inquiryList();
    InquiryDTO inquiryView(HashMap<String, String> param);
}
