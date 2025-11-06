package com.boot.service;

import com.boot.dto.AccountDTO;
import com.boot.dto.Criteria;
import com.boot.dto.NoticeDTO;

import java.util.HashMap;
import java.util.List;

public interface NoticeService {
    public List<NoticeDTO> noticeList(HashMap<String, String> param, Criteria cri);
    NoticeDTO noticeView(HashMap<String, String> param);
    void writeProcess(HashMap<String, String> param);
    void modifyProcess(HashMap<String, String> param);
    void deleteProcess(HashMap<String, String> param);
    void increaseViews(HashMap<String, String> param);
    public int getTotalCount();
    AccountDTO getUserInfo(String accountId);
}
