<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메뉴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yen.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="page-main">
		<h2 class="board-title"><br>[관리자 전용]</h2>
		<hr width="10%" class="board-underline">
		<br>
		<div class="align-center">
			<input type="button" style="WIDTH: 180pt; HEIGHT: 40pt" 
				value="신고 게시글 관리" class="admin-btn" onclick="location.href='${pageContext.request.contextPath}/warn/list.do'">
			<br>    
			<input type="button" style="WIDTH: 180pt; HEIGHT: 40pt" 
				value="공지사항 관리" class="admin-btn" onclick="location.href='${pageContext.request.contextPath}/notice/list.do'">
			<br>
			<input type="button" style="WIDTH: 180pt; HEIGHT: 40pt" 
				value="문의 관리" class="admin-btn" onclick="location.href='${pageContext.request.contextPath}/faq/faq.do'">
		</div>
</div>
</body>
</html>