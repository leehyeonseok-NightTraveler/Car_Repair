package com.boot.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.boot.dto.FaQDTO;

public interface FaQDAO {
	public ArrayList<FaQDTO> list();
	public void write(HashMap<String, String> param);
	public FaQDTO contentView(HashMap<String, String> param);
}






















