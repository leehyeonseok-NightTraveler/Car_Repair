package com.boot.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaQDTO {
	private int FAQ_No;
	private String FAQ_Title;
	private String FAQ_Content;
}
