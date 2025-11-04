<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 정비업소</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
</head>
<body>

<div class="mypage-body">
  <div class="mypage-title">
    <h2>정비업소 마이페이지</h2>
    <p>예약 관리와 매출 현황을 확인하세요.</p>
  </div>

  <!-- 업체 정보 -->
  <section class="mypage-section">
    <h3>업체 정보</h3>
    <table class="info-table">
      <tr><th>업체명</th><td>서울모터스</td></tr>
      <tr><th>위치</th><td>서울 강남구 테헤란로 100</td></tr>
      <tr><th>영업시간</th><td>09:00 ~ 19:00</td></tr>
    </table>
    <button class="btn-normal">정보 수정</button>
  </section>

  <!-- 예약 관리 -->
  <section class="mypage-section">
    <h3>예약 현황</h3>
    <table class="data-table">
      <tr><th>고객명</th><th>차량</th><th>서비스</th><th>시간</th><th>상태</th><th>처리</th></tr>
      <tr>
        <td>김민수</td><td>아반떼</td><td>타이어 교체</td><td>10:30</td><td>대기중</td>
        <td><button class="btn-sub">승인</button> <button class="btn-sub">거절</button></td>
      </tr>
    </table>
  </section>

  <!-- 매출 통계 -->
  <section class="mypage-section">
    <h3>매출 요약</h3>
    <p>이번 달 매출: <strong>₩3,240,000</strong></p>
    <p>이번 달 예약 건수: <strong>24건</strong></p>
  </section>

  <!-- 후기 -->
  <section class="mypage-section">
    <h3>고객 후기</h3>
    <ul class="review-list">
      <li>“정비가 빨랐어요!” ★★★★☆ — user001</li>
      <li>“응대가 친절했습니다.” ★★★★★ — user002</li>
    </ul>
  </section>
</div>

</body>
</html>
