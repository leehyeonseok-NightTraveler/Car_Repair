<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 - 관리자</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
</head>
<body>

<div class="mypage-body">
  <div class="mypage-title">
    <h2>관리자 페이지</h2>
    <p>회원과 업체를 관리하고 공지사항을 작성할 수 있습니다.</p>
  </div>

  <!-- 회원 관리 -->
  <section class="mypage-section">
    <h3>회원 관리</h3>
    <table class="data-table">
      <tr><th>ID</th><th>이름</th><th>등급</th><th>상태</th><th>관리</th></tr>
      <tr>
        <td>user001</td><td>박지민</td><td>USER</td><td>활성</td>
        <td><button class="btn-sub">정지</button> <button class="btn-sub">삭제</button></td>
      </tr>
    </table>
  </section>

  <!-- 업체 승인 -->
  <section class="mypage-section">
    <h3>업체 승인 요청</h3>
    <ul class="approval-list">
      <li>강북오토 — 승인 대기 <button class="btn-sub">승인</button> <button class="btn-sub">거절</button></li>
      <li>인천정비센터 — 승인 대기 <button class="btn-sub">승인</button> <button class="btn-sub">거절</button></li>
    </ul>
  </section>

  <!-- 공지사항 -->
  <section class="mypage-section">
    <h3>공지사항 관리</h3>
    <button class="btn-normal">공지 작성</button>
    <ul class="notice-list">
      <li>[2025-11-01] 시스템 점검 안내</li>
      <li>[2025-10-30] 신규 기능 업데이트</li>
    </ul>
  </section>
</div>

</body>
</html>
