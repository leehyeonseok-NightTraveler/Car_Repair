package com.boot.service;

import java.util.ArrayList;

import com.boot.dto.FaQDTO;
import com.boot.dto.Criteria;

public interface PageService {
	public ArrayList<FaQDTO> listWithPaging(Criteria cri);
	public int getTotalCount(Criteria cri);
	public FaQDTO getFaqDetail(int faq_no);
}
