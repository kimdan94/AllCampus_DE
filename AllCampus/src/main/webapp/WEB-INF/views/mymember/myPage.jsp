<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dan.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
  <div class="wide">
	<div class="myinfor">
	  <h2>내 정보</h2><br>
		<ul>
			<li>
				<c:if test="${empty member.mem_photo}">
				<img src="${pageContext.request.contextPath}/images/face.png"
				width="120" height="120" class="my-photo">
				</c:if>
				<c:if test="${!empty member.mem_photo}">
				<img src="${pageContext.request.contextPath}/upload/${member.mem_photo}"
				width="120" height="120" class="my-photo">
				</c:if>
				<div class="my">
				<b><div class="name_nick">${member.mem_name} / ${member.mem_nick}</div></b> <p>
				<div class="major">${member.mem_univNum} / ${member.mem_major}</div>
				</div>
			</li>
		</ul>
	</div>
		<ul>
		 <div class="parent">
			<div class="child">
			 <h2>계정</h2><br>
			 아이디 ${member.mem_id}<p>
			 <a href="certifyForm.do">학교 인증</a><p>
			 <a href="modifyUserForm.do">내 정보 변경</a><p>
			 <a href="modifyPasswordForm.do">비밀번호 변경</a>
			</div>
		
			<div class="child">
			<h2>내 활동</h2><br>
			<a href="list.do" class="write">내가 쓴 글</a><p>
			<a href="listreply.do" class="write">댓글 단 글</a><p>
			<a href="listscrap.do" class="write">스크랩한 글</a>
			</div>
			
			<div class="child">
			<h2>기타</h2><br>
			<a href="deleteForm.do" class="write">회원탈퇴</a><p>
			<a href="termForm.do" class="write">서비스 이용 약관</a>
			</div>
		 </div>	
		</ul>
	</div>
</div>
</body>
</html>