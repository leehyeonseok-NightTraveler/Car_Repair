package com.boot.dto;

import lombok.Data;

@Data
public class PagingDTO {

    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int total;
    private Criteria criteria;

    public PagingDTO(int total, Criteria criteria) {
        this.total = total;
        this.criteria = criteria;

        int pageNum = criteria.getPageNum();
        int amount = criteria.getAmount();

        // 현재 페이지 기준으로 끝 페이지 계산
        this.endPage = (int) Math.ceil(pageNum / 10.0) * 10;
        this.startPage = this.endPage - 9;

        // 실제 마지막 페이지 계산
        int realEnd = (int) Math.ceil((double) total / amount);

        // 실제 마지막 페이지가 계산된 끝 페이지보다 작으면 보정
        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }

        // 이전 페이지 존재 여부
        this.prev = this.startPage > 1;

        // 다음 페이지 존재 여부
        this.next = this.endPage < realEnd;
    }
}