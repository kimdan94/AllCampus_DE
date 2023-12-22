<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 표시</title> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yen.css">
</head>
<body>    
<jsp:include page="/WEB-INF/views/common/header.jsp"/><!-- header는 꼭 여기에 -->
<div class="page-main">
	<div class="content-main">
		<div class="result-display">
			<div class="align-center">
				게시글 표시로 변경되었습니다.
				<p>
				<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/warn/list.do'">
				</p>
			</div>
		</div>
	</div>
</div>
</body>
</html>