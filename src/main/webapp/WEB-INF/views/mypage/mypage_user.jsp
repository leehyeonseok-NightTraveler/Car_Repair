<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 사용자</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
</head>
<body>

<div class="mypage-body">
  <div class="mypage-title">
    <h2>마이페이지</h2>
    <p>회원님의 예약 현황과 차량 정보를 확인하세요.</p>
  </div>

  <!-- 내 정보 -->
  <section class="mypage-section">
    <h3>내 정보</h3>
    <table class="info-table">
      <tr><th>이름</th><td>이현석</td></tr>
      <tr><th>이메일</th><td>user@email.com</td></tr>
      <tr><th>전화번호</th><td>010-1234-5678</td></tr>
    </table>
    <button class="btn-normal">정보 수정</button>
  </section>

  <!-- 예약 내역 -->
  <section class="mypage-section">
    <h3>예약 내역</h3>
    <table class="data-table">
      <tr><th>예약일자</th><th>업체명</th><th>서비스</th><th>상태</th></tr>
      <tr><td>2025-11-04</td><td>서울모터스</td><td>엔진오일 교체</td><td>완료</td></tr>
      <tr><td>2025-11-12</td><td>강남카센터</td><td>타이어 교체</td><td>예약중</td></tr>
    </table>
  </section>

  <!-- 차량 관리 -->
  <section class="mypage-section">
    <h3>내 차량</h3>
    <ul class="car-list">
      <li>소나타 (12가 3456) <button class="btn-sub">삭제</button></li>
      <li>스포티지 (25마 6789) <button class="btn-sub">삭제</button></li>
    </ul>
    <button class="btn-normal">차량 추가</button>
  </section>

  <!-- 리뷰 -->
  <section class="mypage-section">
    <h3>내가 쓴 리뷰</h3>
    <div class="review-box">
      <p><strong>서울모터스</strong> — “서비스가 정말 좋았습니다!” ★★★★★</p>
    </div>
  </section>
</div>

</body>
</html>
