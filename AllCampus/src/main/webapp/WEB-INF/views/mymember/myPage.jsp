<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h3>내 정보</h3>
		<ul>
			<li>
				<c:if test="${empty member.mem_photo}">
				<img src="${pageContext.request.contextPath}/images/face.jpeg"
				width="100" height="100" class="my-photo">
				</c:if>
				<c:if test="${!empty member.mem_photo}">
				<img src="${pageContext.request.contextPath}/upload/${member.mem_photo}"
				width="100" height="100" class="my-photo">
				</c:if>
				<div class="my-infor">
				${member.mem_name} / ${member.mem_nick} <br>
				${member.univ_name}<br>
				${member.mem_major}
				</div>
			</li>
			
			<br><br>
			
		<ul>
			<div>
			 <h2>계정</h2>
			 아이디 ${member.mem_id}<p>
			 <a href="${pageContext.request.contextPath}/mymember/certifyForm.do">학교 인증</a><p>
			 <a href="${pageContext.request.contextPath}/mymember/modifyUserForm.do">내 정보 변경</a><p>
			 <a href="${pageContext.request.contextPath}/mymember/modifyPasswordForm.do">비밀번호 변경</a>
			</div>
		
			<div>
			<h2>내 활동</h2>
			<a href="${pageContext.request.contextPath}/">내가 쓴 글</a><p>
			<a href="${pageContext.request.contextPath}/">댓글 단 글</a><p>
			<a href="${pageContext.request.contextPath}/">스크랩한 글</a>
			</div>
			
			<div>
			<h2>기타</h2>
			<a href="${pageContext.request.contextPath}/mymember/deleteForm.do">회원탈퇴</a><p>
			<a href="${pageContext.request.contextPath}/mymember/termForm.do">서비스 이용 약관</a>
			</div>
			</ul>
		</ul>
	</div>
</div>
</body>
</html>