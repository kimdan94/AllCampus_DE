<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header 시작 -->
<div id="main_nav">
	<ul>
	<c:if test="${!empty user_num}">
		<li>
			<a href="${pageContext.request.contextPath}/main/main.do">
			<img src="${pageContext.request.contextPath}/images/logo_symbol_231208.png" width="60" height="60">
			</a>
			올캠퍼스
			<br>
			모두대학교
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/course/course_list.do">시간표</a><!-- 시간표.do -->
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/courseeva/courseeva_list.do">강의평</a><!-- 강의평.do --> 
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/calculator/calculator_list.do">학점 계산기</a><!-- 학점계산기.do -->
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/secondhand/secondhand_list.do">책방</a>
		</li>
		<c:if test="${!empty user_num && user_auth == 9}">
		<li>
			<a href="#">관리</a><!-- 관리자 페이지.do -->
		</li>
		</c:if>
		<li>
			<a href="${pageContext.request.contextPath}/mymember/myPage.do">마이페이지<!-- 마이페이지.do -->
				<img src="${pageContext.request.contextPath}/images/face.png" width="50" height="50">
			</a>
		</li>
		<li class="menu-logout">
			[<span>${user_id}</span>]
			<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
		</li>
	</c:if>		
	</ul>
</div>
<!-- header 끝 -->

