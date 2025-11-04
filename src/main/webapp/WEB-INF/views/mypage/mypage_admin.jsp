<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 마이페이지</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage_common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage_admin.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/header.jsp" />

<div class="mypage-body">

  <div class="mypage-title">
    <h2>관리자 대시보드</h2>
    <p>사이트 현황과 회원 및 정비업체를 관리하세요.</p>
  </div>

  <!-- 요약 통계 -->
  <section class="mypage-section">
    <h3>시스템 요약</h3>
    <div class="summary-cards">
      <div class="card">
        <h4>전체 회원 수</h4>
        <p>${summary.totalUsers}명</p>
      </div>
      <div class="card">
        <h4>등록된 정비업체</h4>
        <p>${summary.totalStores}개</p>
      </div>
      <div class="card">
        <h4>총 예약 건수</h4>
        <p>${summary.totalReservations}건</p>
      </div>
      <div class="card">
        <h4>이번 달 신규 회원</h4>
        <p>${summary.newUsersThisMonth}명</p>
      </div>
    </div>
  </section>

  <!-- 회원 관리 -->
  <section class="mypage-section">
    <h3>회원 관리</h3>
    <table class="data-table">
      <thead>
        <tr><th>회원명</th><th>이메일</th><th>전화번호</th><th>가입일</th><th>상태</th><th>관리</th></tr>
      </thead>
      <tbody>
        <c:forEach var="u" items="${userList}">
          <tr>
            <td>${u.user_name}</td>
            <td>${u.email}</td>
            <td>${u.phone_number}</td>
            <td>${u.reg_date}</td>
            <td class="${u.status eq '활성' ? 'active' : 'inactive'}">${u.status}</td>
            <td>
              <button class="btn-sub">정지</button>
              <button class="btn-sub">삭제</button>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </section>

  <!-- 정비업체 승인 -->
  <section class="mypage-section">
    <h3>정비업체 승인 요청</h3>
    <c:choose>
      <c:when test="${not empty pendingStores}">
        <table class="data-table">
          <thead>
            <tr><th>업체명</th><th>대표자</th><th>전화번호</th><th>신청일</th><th>승인</th></tr>
          </thead>
          <tbody>
            <c:forEach var="s" items="${pendingStores}">
              <tr>
                <td>${s.store_name}</td>
                <td>${s.owner_name}</td>
                <td>${s.phone_number}</td>
                <td>${s.reg_date}</td>
                <td>
                  <button class="btn-sub approve">승인</button>
                  <button class="btn-sub reject">거절</button>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:when>
      <c:otherwise>
        <p>대기 중인 승인 요청이 없습니다.</p>
      </c:otherwise>
    </c:choose>
  </section>

  <!-- 통계 요약 -->
  <section class="mypage-section">
    <h3>운영 통계 요약</h3>
    <div class="summary-graph">
      <img src="${pageContext.request.contextPath}/images/chart_sample.png" alt="통계 그래프" />
      <p>※ 추후 월별 이용 통계 차트로 교체 예정</p>
    </div>
  </section>

</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />

</body>
</html>
