<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 모드 진입</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin_enter.css">
</head>

<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<div class="admin-container">
  <h2>관리자 모드 진입</h2>
  <form action="${pageContext.request.contextPath}/admin/verify" method="post">
    <label for="adminKey">관리자 비밀번호 입력</label><br>
    <input type="password" id="adminKey" name="adminKey" placeholder="관리자 전용 비밀번호" required><br>
    <button type="submit">진입</button>
  </form>

  <c:if test="${not empty msg}">
    <p class="alert-msg">${msg}</p>
  </c:if>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
