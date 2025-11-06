<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>문의 내역</title>
    <script src="<c:out value='${pageContext.request.contextPath}/js/jquery.js'/>"></script>
    <script src="<c:out value='${pageContext.request.contextPath}/js/inquiry.js'/>"></script>
    <link rel="stylesheet" href="<c:out value='${pageContext.request.contextPath}/css/mainpage.css'/>">
    <link rel="stylesheet" href="<c:out value='${pageContext.request.contextPath}/css/inquiry.css'/>">
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<main id="inquiry-history-container" class="inquiry-history-container">
    <form id="actionForm" method="post"></form>
    <!-- 왼쪽 플로팅 메뉴 -->
    <div class="floating-wrapper">
        <div class="floating-menu">
            <a href="<c:url value='/inquiry/inquiry_write'/>">1:1 문의</a>
            <a href="<c:url value='/inquiry/inquiry_history'/>">문의 내역</a>
        </div>
    </div>

    <!-- 콘텐츠 박스 -->
    <div class="content">
        <!-- 헤더 영역 -->
        <section class="inquiry-header">
            <h1 class="inquiry-title">문의 내역</h1>
            <hr class="inquiry-divider">
        </section>

        <!-- 문의 목록 테이블 -->
        <section class="inquiry-table-wrapper">
            <table class="inquiry-table">
                <thead>
                <tr>
                    <th class="col-no">번호</th>
                    <th class="col-title">제목</th>
                    <th class="col-date">작성일</th>
                    <th class="col-status">처리 상태</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="inquiry" items="${inquiryList}">
                    <tr class="inquiry-row">
                        <td><c:out value="${inquiry.inquiry_no}"/></td>
                        <td>
                            <a class="inquiry-link"
                               href="<c:out value='inquiry/inquiry_view?inquiry_no=${inquiry.inquiry_no}'/>">
                                <c:out value="${inquiry.inquiry_title}"/>
                            </a>
                        </td>
                        <td><c:out value="${inquiry.inquiry_created}"/></td>
                        <td class="status-cell ${inquiry.inquiry_status}">
                            <c:out value="${inquiry.inquiry_status}"/>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty inquiryList}">
                    <tr>
                        <td colspan="4" class="no-inquiry">문의 내역이 없습니다.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </section>
    </div>
</main>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>
