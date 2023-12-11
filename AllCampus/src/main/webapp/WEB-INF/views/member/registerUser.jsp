<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<div class="result-display align-center">
			올캠퍼스 회원가입이 완료되었습니다.
			<p>
			<input type="button" value="로그인하기"
				onclick="location.href='loginForm.do'">
			<input type="button" value="처음으로"
				onclick="location.href='${pageContext.request.contextPath}/main/main.do'">	
		</div>
	</div>
</div>
</body>
</html>