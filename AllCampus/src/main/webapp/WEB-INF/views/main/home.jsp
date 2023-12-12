<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<div class="content-main">
		<a href="${pageContext.request.contextPath}/board/list.do" style="padding:100px; display:block;">게시판 링크 테스트</a>
	</div>
</div>
<div>
	<a href="#">이용약관</a>
</div>
<div class="copyright">
	copyright(c) 2023. 올캠퍼스. All rights reserved
</div>
</body>
</html>

 