<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>공지사항 목록</title>
    <script src="<c:out value='${pageContext.request.contextPath}/js/jquery.js'/>"></script>
    <script src="<c:out value='${pageContext.request.contextPath}/js/notice.js'/>"></script>
    <link rel="stylesheet" href="<c:out value='${pageContext.request.contextPath}/css/mainpage.css'/>">
    <link rel="stylesheet" href="<c:out value='${pageContext.request.contextPath}/css/notice.css'/>">
</head>
<body>

<jsp:include page="/WEB-INF/views/header.jsp" />

<main id="notice-list-container" class="notice-list-container">

    <form method="get" id="actionForm">
        <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
        <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
        <input type="hidden" name="type" value="${pageMaker.cri.type}">
        <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
    </form>

    <!-- 헤더 영역 -->
    <section class="notice-header">
        <h1 class="notice-title">공지사항</h1>
        <hr class="notice-divider">
    </section>

    <form method="get" id="searchForm">
        <select name="type">
            <option value="" <c:out value="${pageMaker.cri.type == null?'selected':''}"/>>--</option>
            <option value="T" <c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>제목</option>
            <option value="C" <c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option>
            <option value="TC" <c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>제목 or 내용</option>
        </select>
        <input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"/>'>
        <button>Search</button>
    </form>

    <!-- 공지사항 목록 -->
    <section class="notice-table-wrapper">
        <table class="notice-table">
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
                    <td><c:out value='${list.notice_no}'/></td>
                    <td>
                        <a class="notice-link move-link"
                           href="<c:out value='notice/notice_view?notice_no=${list.notice_no}'/>">
                            <c:out value='${list.notice_title}'/>
                        </a>
                    </td>
                    <td><c:out value='${list.notice_writer}'/></td>
                    <td><c:out value='${list.notice_created}'/></td>
                    <td><c:out value='${list.notice_views}'/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>

    <!-- 페이징 영역 -->
    <nav class="pagination-container">
        <ul class="pagination-list">
            <c:if test="${pageMaker.prev}">
                <li class="pagination-item prev paginate_button">
                    <a class="pagination-link"
                       href="<c:out value='${pageMaker.startPage - 1}'/>">[이전]</a>
                </li>
            </c:if>
            <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                <li class="pagination-item page-num paginate_button">
                    <a class="pagination-link"
                       href="<c:out value='${num}'/>">[${num}]</a>
                </li>
            </c:forEach>
            <c:if test="${pageMaker.next}">
                <li class="pagination-item next paginate_button">
                    <a class="pagination-link"
                       href="<c:out value='${pageMaker.endPage + 1}'/>">[다음]</a>
                </li>
            </c:if>
        </ul>
    </nav>

    <!-- 글쓰기 버튼 (관리자만 보임) -->
    <c:if test="${role.equals('ADMIN')}">
        <div id="notice-actions">
            <button type="button" class="btn btn-submit"
                    onclick="location.href='<c:url value='/notice/notice_write'/>'">글쓰기</button>
        </div>
    </c:if>

</main>

<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>