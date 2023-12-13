<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!empty user_id && !empty user_email}">   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 결과 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
</head>
<body>
<div class="page-main align-center">
<div class="member-style">
	<div class="result-display">
		<div class="align-center">
			<a href="${pageContext.request.contextPath}/main/main.do">
			<img src="${pageContext.request.contextPath}/images/logo_symbol_231208.png" width="100">
			</a>
			<p>
			<c:if test="${empty secret_pw}">
				입력한 정보로 가입된 정보가 존재하지 않습니다.<p>
				<input type="button" value="이전으로"
					onclick="location.href='${pageContext.request.contextPath}/member/checkPwForm.do'">
				<input type="button" value="처음으로"
					onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</c:if>
			<c:if test="${!empty secret_pw}">
				[${user_id}]로 가입된 계정의 비밀번호는 [${secret_pw}]입니다.<br>
				비밀번호 변경을 원하신다면 입력한 [${user_email}]으로<br> 재설정 메일이 전송되었으니 확인해 주세요.<p>
				<input type="button" value="로그인하기"
					onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'">
			</c:if>
		</div>
	</div>
</div>
</div>
</body>
</html>
</c:if>
<c:if test="${empty user_id && empty user_email}">
	<script type="text/javascript">
		location.href='loginForm.do';
	</script>
</c:if>