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
<!-- 공통 헤더 -->
<jsp:include page="/WEB-INF/views/header.jsp"/>

<main id="inquiry-view-container" class="inquiry-view-container">
    <!-- 왼쪽 플로팅 메뉴 -->
    <div id="floating-wrapper" class="floating-wrapper">
        <div id="floating-menu" class="floating-menu">
            <a id="write-link" class="floating-link" href="<c:url value='/inquiry/inquiry_write'/>">1:1 문의</a>
            <a id="history-link" class="floating-link" href="<c:url value='/inquiry/inquiry_history'/>">문의 내역</a>
        </div>
    </div>

    <!-- 제목 영역 -->
    <section id="inquiry-header" class="inquiry-header">
        <h2 id="inquiry-title" class="inquiry-title">${inquiryView.inquiry_title}</h2>
        <hr id="inquiry-divider" class="inquiry-divider">
    </section>

    <!-- 본문 영역 -->
    <article id="inquiry-body" class="inquiry-body">
        <div id="inquiry-content" class="inquiry-content">
            ${inquiryView.inquiry_content}
        </div>
        <div id="inquiry-meta" class="inquiry-meta">
            <span id="inquiry-date" class="inquiry-date">${inquiryView.inquiry_created}</span>
        </div>
    </article>

    <c:choose>
        <c:when test="${userInfo.accountRole eq 'ADMIN'}">
            <form>
                <section id="inquiry-reply" class="inquiry-reply">
                    <h3 class="reply-title">관리자 답변</h3>
                    <div class="reply-content">
                            ${inquiryView.reply_content}
                    </div>
                    <!-- ✅ 관리자 전용 답변 작성 버튼 -->
                    <div class="reply-button">
                        <a href="<c:url value='/inquiry/reply_write?inquiry_no=${inquiryView.inquiry_no}'/>" class="btn btn-primary">
                            답변 작성
                        </a>
                    </div>
                </section>
            </form>
        </c:when>
        <c:when test="${not empty inquiryView.reply_content}">
            <section id="inquiry-reply" class="inquiry-reply">
                <h3 class="reply-title">관리자 답변</h3>
                <div class="reply-content">
                        ${inquiryView.reply_content}
                </div>
            </section>
        </c:when>
    </c:choose>


</main>

<!-- 공통 푸터 -->
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>