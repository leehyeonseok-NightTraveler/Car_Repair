<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 정비업소</title>

<!-- CSS 연결 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage_common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage_store.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/header.jsp" />

<div class="mypage-body">

  <div class="mypage-title">
    <h2>정비업소 마이페이지</h2>
    <p>예약 관리와 매출 현황을 확인하세요.</p>
  </div>

  <!-- 업체 정보 -->
  <section class="mypage-section">
    <h3>업체 정보</h3>
    <table class="info-table">
      <tr><th>업체명</th><td>${store.store_name}</td></tr>
      <tr><th>주소</th><td>${store.address}</td></tr>
      <tr><th>영업시간</th><td>${store.opening_hours}</td></tr>
      <tr><th>이메일</th><td>${store.email}</td></tr>
      <tr><th>전화번호</th><td>${store.phone_number}</td></tr>
    </table>
    <button class="btn-normal" onclick="location.href='editStore'">정보 수정</button>
  </section>

  <!-- 예약 관리 -->
  <section class="mypage-section">
    <h3>예약 현황</h3>
    <table class="data-table">
      <thead>
        <tr><th>고객명</th><th>차량</th><th>서비스</th><th>시간</th><th>상태</th><th>처리</th></tr>
      </thead>
      <tbody>
        <c:forEach var="r" items="${reservationList}">
          <tr>
            <td>${r.customer_name}</td>
            <td>${r.car_name}</td>
            <td>${r.service_name}</td>
            <td>${r.reserve_time}</td>
            <td class="status ${r.status eq '대기중' ? 'pending' : 'complete'}">${r.status}</td>
            <td>
              <button class="btn-sub approve">승인</button>
              <button class="btn-sub reject">거절</button>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </section>

  <!-- 매출 요약 -->
  <section class="mypage-section">
    <h3>매출 요약</h3>
    <div style="display:flex; gap:20px; flex-wrap:wrap;">
      <div style="flex:1; min-width:240px; background:#f5f5f5; padding:20px; border-radius:6px; text-align:center;">
        <h4>이번 달 매출</h4>
        <p style="font-size:22px; font-weight:bold; color:#2d8f6f;">₩${salesData.monthlyTotal}</p>
      </div>
      <div style="flex:1; min-width:240px; background:#f5f5f5; padding:20px; border-radius:6px; text-align:center;">
        <h4>예약 건수</h4>
        <p style="font-size:22px; font-weight:bold; color:#007bff;">${salesData.reservationCount}건</p>
      </div>
    </div>
  </section>

  <!-- 고객 후기 -->
  <section class="mypage-section">
    <h3>고객 후기</h3>
    <c:choose>
      <c:when test="${not empty reviewList}">
        <c:forEach var="r" items="${reviewList}">
          <div class="review-box">
            <p><strong>${r.user_name}</strong> — “${r.content}” ★${r.rating}</p>
          </div>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <p>등록된 후기가 없습니다.</p>
      </c:otherwise>
    </c:choose>
  </section>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />

</body>
</html>
