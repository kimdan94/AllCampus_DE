<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yen.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<h2 class="board-title">공지사항 글쓰기</h2>
	<hr width="10%" class="board-underline">
	<form id="write_form" action="write.do" method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label for="notice_title">제목</label>
					<input type="text" name="notice_title" id="notice_title" maxlength="50">
				</li>
				<li>
					<label for="notice_content">내용</label>
					<textarea rows="5" cols="30"
						name="notice_content" id="notice_content"></textarea>
				</li>
				<li>
					<label for="notice_filename">파일</label>
					<input type="file" name="notice_filename"
						id="notice_filename" accept="image/gif,image/png,image/jpeg">
				</li>
			</ul>
			<div class="align-right">
 			<input type="submit" value="등록" onclick="location.href='write.do'">
 			<br>
 			<input type="button" value="목록" onclick="location.href='list.do'">
			</div>
		</form>
</div>
</body>
</html>