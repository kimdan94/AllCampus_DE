<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문 상세 정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yen.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>    
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<div class="content-main">
	<hr width="135%" class="board-underline1">
		<h2 style="font-size:22px;">${question.question_title}</h2>
		<hr width="135%" class="board-underline1">
		<div class="faq_content" style="position: relative; left: 30px; top: 10px; width:670px;" >${question.question_content}</div>
		<div class="align-center" style="padding-left: 180px">
		<br>
		<br>
		<input type="button" style="WIDTH: 100pt; HEIGHT: 30pt" value="목록으로" class="input-button4"onclick="location.href='${pageContext.request.contextPath}/faq/faq.do'">
	</div>
	</div>
	
</div>	
</body>
</html>