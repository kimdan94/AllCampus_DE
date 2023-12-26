<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세글</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yen.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>    
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<h2 class="board-title">공지사항 상세</h2> 
	<hr width="10%" class="board-underline">
	<div class="content-main" style="border:2px solid #d6d6d6; width:750px; margin-left: 475px;  ">
	<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40"><div style="font-size:16px">[관리자 전용]</div>
		<h1>제목 : ${notice.notice_title}</h1>
		<hr size="1" noshade="noshade" width="100%">
		<div style="font-size:16px"> 
				<p style="text-align:right" style="color:Grey;">최근 수정일 : ${notice.notice_modify_date}
				작성일 : ${notice.notice_reg_date}</p>
			</div>
		<c:if test="${!empty notice.notice_filename}">
		<div>
			<img src="${pageContext.request.contextPath}/upload/${notice.notice_filename}" style="max-width:500px;">
		</div>
		</c:if>
			<div style="font-size:20px">${notice.notice_content}</div>
			<br><br>
			<div class="align-center">
			<%-- 관리자로 로그인된 계정만
					 수정,삭제 가능 (일반회원은 접근 불가)--%>
			<c:if test="${user_auth == 9}">
			<input type="button" value="삭제" class="input-button4" id="delete_btn">
			</c:if>
			<script type="text/javascript">
				let delete_btn = document.getElementById('delete_btn');
				//이벤트 연결
				delete_btn.onclick = function() {
					let choice = confirm('삭제하시겠습니까?');
					if (choice) {
						location
								.replace('delete.do?notice_num=${notice.notice_num}');
					}
				};
			</script>
			</div>
		</div>
		</div>
		