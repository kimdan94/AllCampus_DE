<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 스크랩 목록 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dan.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/mymember/sidebar.jsp"/>
	<h2 class="header">내 스크랩</h2>
	<div class="content">
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 게시물이 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table>
		<h3 class="write">[ 자유게시판 ]</h3>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="board" items="${list}">
			<tr>
				<td>${board.board_num}</td>
				<td><a href="${pageContext.request.contextPath}/board/detail.do?board_num=${board.board_num}">${board.board_title}</a></td>
				<td>${board.board_reg_date}</td>
			</tr>
			</c:forEach>
		</table>
		<br><br>
		<div class="align-center">${page}</div>
		</c:if>
	</div>
 </div>
 </div>
</body>
</html>