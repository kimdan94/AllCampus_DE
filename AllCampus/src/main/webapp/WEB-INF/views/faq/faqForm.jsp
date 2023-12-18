<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직접 문의하기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yen.css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h2>문의하기</h2>
	<form id="faq_form" action="faq.do" method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label for="question_title">제목</label>
					<input type="text" name="question_title" id="question_title" maxlength="50">
				</li>
				<li>
					<label for="question_content">내용</label>
					<textarea rows="5" cols="30"
						name="question_content" id="question_content"></textarea>
				</li>
					<label for="question_board_email">email 주소를 입력하세요: <input type="email"
					id="question_board_email" pattern=".+@email\.com" placeholder="example@email.com"
					required>
					</label>
				<li>
					<label for="question_filename">파일</label>
					<input type="file" name="question_filename"
						id="question_filename" accept="image/gif,image/png,image/jpeg">
				</li>
				<li>
				<label>개인정보 수집 동의</label>
				<input type="radio" name="agree" value="checked">
			</li>
			</ul>
			<div class="align-right">
 			<input type="submit" value="전송">
 			<br>
 			<input type="button" value="목록" onclick="location.href='faq.do'">
			</div>
		</form>
</div>
</body>
</html>