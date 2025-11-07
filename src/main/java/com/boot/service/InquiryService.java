package com.boot.service;

import com.boot.dto.AccountDTO;
import com.boot.dto.InquiryDTO;

import java.util.HashMap;
import java.util.List;

public interface InquiryService {
    void writeProcess(HashMap<String, String> param);
    List<InquiryDTO> inquiryList(HashMap<String, String> param);
    InquiryDTO inquiryView(HashMap<String, String> param);
    AccountDTO getUserInfo(String accountId);
    void replyProcess(HashMap<String, String> param);
    List<InquiryDTO> selectByAccountId(String accountId);
}
