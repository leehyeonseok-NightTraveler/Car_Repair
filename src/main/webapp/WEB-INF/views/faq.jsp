<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/faq.css">
   <style>
      #btn_logout{
         text-align: right;
      }
      .div_page ul{
         display: flex;
         list-style: none;
      }
   </style>
</head>

<jsp:include page="/WEB-INF/views/header.jsp" />

<body>
<main>
	<div class="faq-container">
	   
	      <%-- 2. "FAQ" 제목 추가 --%>
	      <h2>FAQ</h2>
	      <p>자주 묻는 질문을 확인하세요.</p>
		  
   <table class="faq-table">
      <tr>
         <td>번호</td>
         <td>자주 묻는 질문</td>
         <td>답변</td>
      </tr>
      <c:forEach var="dto" items="${list}">
         <tr>
            <td>${dto.FAQ_No}</td>
            <td>
<!--               <a class="move_link" href="${dto.FAQ_Title}">${dto.FAQ_Title}</a>-->
			   <a href="faq_view?faq_no=${dto.FAQ_No}">${dto.FAQ_Title}</a>
            </td>
            <td>${dto.FAQ_Content}</td>
         </tr>
      </c:forEach>
   
   </table>

   <form action="get" id="searchForm">
      <select name="type">
         <option value=""  <c:out value="${pageMaker.cri.type == null?'selected':' '}"/>>--</option>
         <option value="T"  <c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>자주 묻는 질문</option>
         <option value="C"  <c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>답변</option>
         <option value="TC"  <c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>자주 묻는 질문 or 답변</option>
      </select>
      <input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"/>'>
	  <button>Search</button>
   </form>
   
   
   <div class="div_page">
         <ul>
            <c:if test="${pageMaker.prev}">
               <li class="paginate_button">
                  <a href="${pageMaker.startPage - 1}">[Previous]</a>
               </li>
            </c:if>
			<c:set var="currentPageNum" value="${pageMaker.cri.pageNum + 0}" />
            <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<li class="paginate_button ${currentPageNum == num ? 'active' : ''}">
                  <a href="${num}">[${num}]</a>
               </li>
            </c:forEach>
            
            <c:if test="${pageMaker.next}">
               <li class="paginate_button">
                  <a href="${pageMaker.endPage + 1}">[Next]</a>
               </li>
            </c:if>
         </ul>
      </div>

   <form method="get" id="actionForm">
      <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
      <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
      <input type="hidden" name="type" value="${pageMaker.cri.type}">
      <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
   </form>
   </div>
   </main>
   <jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script>
   var actionForm = $("#actionForm");

   // 페이지번호 처리
   $(".paginate_button a").on("click", function (e) {
      e.preventDefault();
      console.log("click~!!!");
      console.log("@# href=>"+$(this).attr("href"));

      actionForm.find("input[name='pageNum']").val($(this).attr('href'));
      actionForm.attr("action","faq").submit();
   });//end of paginate_button click

   // 게시글 처리
   $(".move_link").on("click", function (e) {
      e.preventDefault();
      console.log("move_link click~!!!");
      console.log("@# href=>"+$(this).attr("href"));

      var targetBno = $(this).attr("href");

      var bno = actionForm.find("input[name='boardNo']").val();
      if (bno != "") {
         actionForm.find("input[name='boardNo']").remove();
      }

      actionForm.append("<input type='hidden' name='boardNo' value='"+targetBno+"'>");
      actionForm.attr("action","content_view").submit();
   });//end of paginate_button click
   
   var searchForm = $("#searchForm");
   
   $("#searchForm button").on("click", function (){
		
		if(searchForm.find("option:selected").val() != "" && searchForm.find("input[name='keyword']").val() == "" ){
			alert("키워드를 입력하세요.");
			return false;
		}
	
		searchForm.attr("action", "faq").submit();
   });
   
   $("#searchForm select").on("change", function (){
		if(searchForm.find("option:selected").val() == ""){
			searchForm.find("input[name='keyword']").val("");
		}
	
   });
</script>
