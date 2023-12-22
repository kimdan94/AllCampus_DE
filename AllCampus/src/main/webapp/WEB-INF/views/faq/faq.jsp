<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>faq 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yen.css">
</head>
<body>  
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<div class="content-main">
		<div class="board_listform">
			<h1>자주 묻는 질문</h1>
			<table style="width:600px;">
			<c:forEach var="question" items="${list}">
				<tr>
					<td><a href="detail.do?question_num=${question.question_num}">${question.question_title}</a></td>
				</tr>
			</c:forEach>
			</table>
			<br>
			<div class="align-center" style="width:600px;">${page}</div>
			<div class="align-center">
			<input type="button" style="WIDTH: 180pt; HEIGHT: 40pt" 
				value="직접 문의하기" onclick="location.href='${pageContext.request.contextPath}/faq/faqForm.do'">
		</div>
	</div>
</div>
</div>
</body>
</html>