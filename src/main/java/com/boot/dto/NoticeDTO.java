package com.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {

    private int notice_no;
    private String notice_title;
    private String notice_writer;
    private Timestamp notice_created;
    private String notice_content;
    private int notice_views;

}
