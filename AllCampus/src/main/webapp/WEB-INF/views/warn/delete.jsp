<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고글 삭제</title> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yen.css">
</head>
<body>    
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<div class="result-display">
			<div class="align-center">
				게시글 삭제가 완료되었습니다.
				<p>
				<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/warn/list.do'">
				</p>
			</div>
		</div>
	</div>
</div>
</body>
</html>