<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
		
	<div class="floating-icons">
	    <a href="https://www.instagram.com/khieiorkr/" target="_blank" class="icon-instagram">
	        <img src="https://img.icons8.com/fluent/48/000000/instagram-new.png" alt="인스타그램"/>
	    </a>
	   <a href="https://www.youtube.com/@KH_academy" target="_bla	k" class="icon-youtube">
	       <img src="https://img.icons8.com/color/48/youtube-play.png" alt="유튜브"/>
	   </a>

	</div>
	<header>
	    <div class="inner">
	        <h1>          
	            <a href="<c:url value='/main' />">
	                MY CAR 정비소
	            </a>
	        </h1>
	        <ul id="gnb">
	            <li><a href="<c:url value='/recommend' />">주변 정비소</a></li>
				<li class="dropdown-parent"><a href="#">고객센터</a>
	                <ul class="submenu">
                    	<li><a href="#">1:1 문의</a></li>
                 		<li><a href="<c:url value='/notice/notice_list'/>">공지사항</a></li>
                    	<li><a href="<c:url value='/faq'/>">FAQ</a></li>
	        		</ul>
			</ul>
	        <ul class="util">
	        <c:choose>
	          <c:when test="${sessionScope.role == 'ADMIN' || sessionScope.role == 'USER'}">
	            <li><a href="<c:url value='/mypage_userinfo'/>">마이페이지</a></li>
	            <li><a href="<c:url value='/logout'/>">로그아웃</a></li>
	          </c:when>
	          <c:otherwise>
	            <li><a href="<c:url value='/login'/>">로그인</a></li>
	            <li><a href="<c:url value='/register'/>">회원가입</a></li>
	          </c:otherwise>
	        </c:choose>
	      </ul>
	    </div>
	</header>