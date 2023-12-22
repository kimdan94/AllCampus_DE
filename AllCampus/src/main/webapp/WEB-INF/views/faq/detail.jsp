<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문 상세 정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>    
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<div class="content-main">
		<h2>${question.question_title}</h2>
		<br>
		${question.question_content}
	</div>
	<div class="align-center">
		<input type="button" style="WIDTH: 100pt; HEIGHT: 30pt"  value="홈으로" onclick="location.href='${pageContext.request.contextPath}/faq/faq.do'">
	</div>
</div>	
</body>
</html>