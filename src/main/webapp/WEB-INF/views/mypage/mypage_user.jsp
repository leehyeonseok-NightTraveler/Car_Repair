<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

<jsp:include page="/WEB-INF/views/header.jsp"/>

<div class="mypage-body">

    <div class="mypage-title">
        <h2>마이페이지</h2>
        <p>회원님의 문의 내역과 계정 정보를 확인하세요.</p>
    </div>

    <!-- 내 정보 -->
    <section class="mypage-section">
        <h3>내 정보</h3>
        <table class="info-table">
            <tr>
                <th>이름</th>
                <td>${user.userName}</td>
            </tr>
            <tr>
                <th>이메일</th>
                <td>${user.email}</td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td>${user.phoneNumber}</td>
            </tr>
            <tr>
                <th>가입일</th>
                <td><fmt:formatDate value="${user.regDate}" pattern="yyyy-MM-dd"/></td>
            </tr>
        </table>
        <button class="btn-normal"
                onclick="location.href='${pageContext.request.contextPath}/mypage_user/mypage_useredit'">
            정보 수정
        </button>
    </section>

    <!-- 1:1 문의 내역 -->
    <section class="mypage-section">
        <h3>1:1 문의 내역</h3>
        <c:choose>
            <c:when test="${not empty inquiryList}">
                <table class="data-table">
                    <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성일</th>
                        <th>상태</th>
                        <th>보기</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="q" items="${inquiryList}">
                        <tr>
                            <td>${q.inquiry_no}</td>
                            <td>${q.inquiry_title}</td>
                                <%--                <td><fmt:formatDate value="${q.inquiry_created}" pattern="yyyy-MM-dd"/></td>--%>
                            <td><c:out value="${q.inquiry_created}"/></td>
                            <td>${q.inquiry_status}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/inquiry/inquiry_view?inquiry_no=${q.inquiry_no}"
                                   class="btn-sub">상세보기</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>등록된 문의 내역이 없습니다.</p>
            </c:otherwise>
        </c:choose>

        <nav class="pagination-container">
            <ul class="pagination-list">
                <%-- 이전 페이지 --%>
                <c:if test="${pageMaker.prev}">
                    <li class="pagination-item prev paginate_button">
                        <a class="pagination-link"
                           href="mypage_user?pageNum=${pageMaker.startPage - 1}&amount=<c:out value='${pageMaker.cri.amount}'/>&type=<c:out value='${pageMaker.cri.type}'/>&keyword=<c:out value='${pageMaker.cri.keyword}'/>">[이전]</a> <%-- 👈 수정: 경로 명시 및 파라미터 추가 --%>
                    </li>
                </c:if>

                <%-- 페이지 번호 --%>
                <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                    <li class="pagination-item page-num paginate_button
                <c:out value='${pageMaker.cri.pageNum == num ? "active" : ""}'/>">
                        <a class="pagination-link"
                           href="mypage_user?pageNum=<c:out value='${num}'/>&amount=<c:out value='${pageMaker.cri.amount}'/>&type=<c:out value='${pageMaker.cri.type}'/>&keyword=<c:out value='${pageMaker.cri.keyword}'/>">[${num}]</a> <%-- 👈 수정: 경로 명시 --%>
                    </li>
                </c:forEach>

                <%-- 다음 페이지 --%>
                <c:if test="${pageMaker.next}">
                    <li class="pagination-item next paginate_button">
                        <a class="pagination-link"
                           href="mypage_user?pageNum=${pageMaker.endPage + 1}&amount=<c:out value='${pageMaker.cri.amount}'/>&type=<c:out value='${pageMaker.cri.type}'/>&keyword=<c:out value='${pageMaker.cri.keyword}'/>">[다음]</a> <%-- 👈 수정: 경로 명시 및 파라미터 추가 --%>
                    </li>
                </c:if>
            </ul>
        </nav>
    </section>

</div>

<jsp:include page="/WEB-INF/views/footer.jsp"/>

</body>
</html>
