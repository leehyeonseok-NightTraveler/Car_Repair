<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>공지사항 목록</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/notice.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/notice.css">
</head>
<body>
<form method="get" id="actionForm">
    <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
    <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
</form>

<main id="notice-list-container">
    <!-- 헤더 영역 -->
    <section id="notice-header">
        <h1 class="notice-title">공지사항</h1>
        <hr class="notice-divider">
    </section>

    <!-- 공지사항 테이블 -->
    <table id="notice-table" class="notice-table">
        <thead>
        <tr>
            <th class="col-no">번호</th>
            <th class="col-title">제목</th>
            <th class="col-writer">작성자</th>
            <th class="col-date">작성일</th>
            <th class="col-views">조회수</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list" items="${list}">
            <tr class="notice-row">
                <td class="notice-no">${list.notice_no}</td>
                <td class="notice-title">
                    <a class="notice-link move-link" href="${list.notice_no}">${list.notice_title}
                    </a>
                </td>
                <td class="notice-writer">${list.notice_writer}</td>
                <td class="notice-date">${list.notice_created}</td>
                <td class="notice-views">${list.notice_views}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- 페이징 영역 -->
    <nav id="notice-pagination" class="pagination-container">
        <ul class="pagination-list">
            <c:if test="${pageMaker.prev}">
                <li class="pagination-item prev">
                    <a class="pagination-link" href="${pageMaker.startPage - 1}">[이전]</a>
                </li>
            </c:if>
            <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                <li class="pagination-item page-num">
                    <a class="pagination-link" href="${num}">[${num}]</a>
                </li>
            </c:forEach>
            <c:if test="${pageMaker.next}">
                <li class="pagination-item next">
                    <a class="pagination-link" href="${pageMaker.endPage + 1}">[다음]</a>
                </li>
            </c:if>
        </ul>
    </nav>

    <!-- 글쓰기 버튼 -->
    <div id="notice-actions">
        <button type="button" id="btn-write" class="btn btn-write"
                onclick="location.href='notice_write'">글쓰기</button>
    </div>
</main>
</body>
</html>