<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yen.css">
</head>
<body>    
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="page-main">
		<h2 class="board-title">공지사항 글 수정</h2>
		<hr width="10%" class="board-underline">
		<div>
		<form id="update_form" action="update.do" method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label for="notice_title">제목</label>
					<input type="text" name="notice_title" id="notice_title" value="${notice.title}" maxlength="50">
				</li>
				<li>
					<label for="notice_content">내용</label>
					<textarea rows="5" cols="30"
						name="notice_content" id="notice_content">${notice.content}</textarea>
				</li>
			</ul>
		</form>
		<br>
		<div class="align-right">
		<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
</div>
</div>
</body>
</html>