<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의사항 전송</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="content-main">
			<div class="result-display">
				<div class="align-center">
					문의등록이 완료되었습니다.<br> (3일 이내 이메일 확인 요망)
					<p>
						<input type="button" value="목록으로" onclick="location.href='faq.do'">
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>