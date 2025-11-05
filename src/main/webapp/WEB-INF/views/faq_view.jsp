<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/faq.css">
</head>

<jsp:include page="/WEB-INF/views/header.jsp" />

<body>
<main>
   <div class="faq-detail-container">
      <h2>FAQ 상세 보기</h2>
      
     
      <div class="faq-detail-title">
         <span class="q-icon">Q.</span> ${faq.FAQ_Title}
      </div>
      
      <div class="faq-detail-answer">
         ${faq.FAQ_Content}
      </div>
      
      <div class="button-area">
         <a href="faq" class="btn-list">목록으로</a>
      </div>
      
   </div>
</main>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>