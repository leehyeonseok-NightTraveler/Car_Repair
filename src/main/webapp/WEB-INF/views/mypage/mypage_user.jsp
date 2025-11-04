<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 사용자</title>

<!-- CSS 연결 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage_common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage_user.css">
</head>
<body>

<jsp:include page="/WEB-INF/views/header.jsp" />

<div class="mypage-body">

  <div class="mypage-title">
    <h2>마이페이지</h2>
    <p>회원님의 예약 현황과 차량 정보를 확인하세요.</p>
  </div>

  <!-- 내 정보 -->
  <section class="mypage-section">
    <h3>내 정보</h3>
    <table class="info-table">
      <tr><th>이름</th><td>${user.user_name}</td></tr>
      <tr><th>이메일</th><td>${user.email}</td></tr>
      <tr><th>전화번호</th><td>${user.phone_number}</td></tr>
      <tr><th>가입일</th><td><fmt:formatDate value="${user.reg_date}" pattern="yyyy-MM-dd"/></td></tr>
    </table>
    <button class="btn-normal" onclick="location.href='editProfile'">정보 수정</button>
  </section>

  <!-- 예약 내역 -->
  <section class="mypage-section">
    <h3>예약 내역</h3>
    <c:choose>
      <c:when test="${not empty reservationList}">
        <table class="data-table">
          <thead>
            <tr><th>예약일자</th><th>업체명</th><th>서비스</th><th>상태</th></tr>
          </thead>
          <tbody>
            <c:forEach var="r" items="${reservationList}">
              <tr>
                <td>${r.reserve_date}</td>
                <td>${r.store_name}</td>
                <td>${r.service_name}</td>
                <td class="status ${r.status eq '예약중' ? 'reserved' : ''}">${r.status}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:when>
      <c:otherwise>
        <p>예약 내역이 없습니다.</p>
      </c:otherwise>
    </c:choose>
  </section>

  <!-- 차량 관리 -->
  <section class="mypage-section">
    <h3>내 차량</h3>
    <c:choose>
      <c:when test="${not empty carList}">
        <ul class="car-list">
          <c:forEach var="c" items="${carList}">
            <li>
              ${c.car_name} (${c.car_number})
              <button class="btn-sub" onclick="deleteCar('${c.car_id}')">삭제</button>
            </li>
          </c:forEach>
        </ul>
      </c:when>
      <c:otherwise>
        <p>등록된 차량이 없습니다.</p>
      </c:otherwise>
    </c:choose>
    <button class="btn-normal" onclick="location.href='addCar'">차량 추가</button>
  </section>

  <!-- 내가 쓴 리뷰 -->
  <section class="mypage-section">
    <h3>내가 쓴 리뷰</h3>
    <c:choose>
      <c:when test="${not empty reviewList}">
        <c:forEach var="r" items="${reviewList}">
          <div class="review-box">
            <p><strong>${r.store_name}</strong> — “${r.content}” ★${r.rating}</p>
          </div>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <p>작성한 리뷰가 없습니다.</p>
      </c:otherwise>
    </c:choose>
  </section>

</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />

<script>
  function deleteCar(id) {
    if(confirm("차량을 삭제하시겠습니까?")) {
      location.href = "deleteCar?car_id=" + id;
    }
  }
</script>

</body>
</html>
