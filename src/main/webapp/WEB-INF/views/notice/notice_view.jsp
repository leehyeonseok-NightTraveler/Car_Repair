<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>공지사항 상세보기</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/notice.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/notice.css">
</head>
<body>
<main id="notice-view-container" class="notice-view-container">
    <!-- 상세보기 헤더 -->
    <section id="notice-view-header" class="notice-view-header">
        <table id="notice-view-table" class="notice-view-table">
            <thead>
            <tr>
                <th colspan="2" class="notice-view-title">${view.notice_title}</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="2" class="notice-view-content">${view.notice_content}</td>
            </tr>
            <tr>
                <td class="notice-view-date">${view.notice_created}</td>
                <td class="notice-view-views">조회수: ${view.notice_views}</td>
            </tr>
            </tbody>
        </table>
    </section>

    <!-- 버튼 그룹 -->
    <section id="notice-button-group" class="notice-button-group">
        <form method="get" id="notice-action-form" class="notice-action-form">
            <input type="hidden" name="notice_no" value="${view.notice_no}">
            <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
            <input type="hidden" name="amount" value="${pageMaker.cri.amount}">

            <input type="submit" value="수정" formaction="notice_modify" class="btn btn-modify" id="btn-modify">
            <input type="submit" value="목록" formaction="notice_list" class="btn btn-list" id="btn-list">
            <input type="submit" value="삭제" formaction="deleteProcess" class="btn btn-delete" id="btn-delete">
        </form>
    </section>
</main>
</body>
</html>