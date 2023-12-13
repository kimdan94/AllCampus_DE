<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header 시작 -->
<div class="header-style">
	<ul>
	<c:if test="${!empty user_num}">
		<li class="home-logo">
			<a href="${pageContext.request.contextPath}/main/home.do">
			<img src="${pageContext.request.contextPath}/images/logo_symbol_231208.png" width="100" style="margin-top:-10px;">	
			</a>
			올캠퍼스
		</li>
		<li class="clear">
			<h2>
			<a href="${pageContext.request.contextPath}/course/course_list.do">시간표</a>
			</h2>
		</li>
		<li class="clear">
			<h2>
			<a href="${pageContext.request.contextPath}/courseeva/courseeva_list.do">강의평</a>
			</h2> 
		</li>
		<li class="clear">
			<h2>
			<a href="${pageContext.request.contextPath}/calculator/calculator_list.do">학점 계산기</a>
			</h2>
		</li>
		<li class="clear">
			<h2>
			<a href="${pageContext.request.contextPath}/secondhand/secondhand_list.do">책방</a>
			</h2>
		</li>
		<c:if test="${!empty user_num && user_auth == 9}">
		<li class="clear">
			<h2>
			<a href="${pageContext.request.contextPath}/admin/adminMenu.do">관리</a>
			</h2>
		</li>
		</c:if>	
		<li class="myPage">
			<a href="${pageContext.request.contextPath}/mymember/myPage.do">
				<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" style="margin-right:10px;margin-top:5px;">
			</a>
			[<span>${user_id}</span>]
			<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
		</li>
	</c:if>		
	</ul>
</div>
<!-- header 끝 -->