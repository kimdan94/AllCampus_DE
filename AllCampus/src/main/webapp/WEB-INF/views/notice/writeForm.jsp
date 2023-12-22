<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yen.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#write_form').submit(function(){
		let items = document.querySelectorAll('.input-check');
		for(let i=0;i<items.length;i++){
			if(items[i].value.trim()==''){
				let label = document.querySelector('label[for="' + items[i].id + '"]');
				alert(label.textContent + ' 항목이 기입되지 않았습니다.');
				items[i].value = '';
				items[i].focus();
				return false;
			}
		}
	});
});
</script>
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
					<input type="text" name="notice_title" id="notice_title" class="input-check" maxlength="50">
				</li>
				<li>
					<label for="notice_content">내용</label>
					<textarea rows="5" cols="30"
						name="notice_content" id="notice_content" class="input-check"></textarea>
				</li>
				<li>
					<label for="notice_filename">파일</label>
					<input type="file" name="notice_filename"
						id="notice_filename" accept="image/gif,image/png,image/jpeg">
				</li>
			</ul>
			<div class="align-right">
 			<input type="submit" value="등록">
 			<br>
 			<input type="button" value="목록" onclick="location.href='list.do'">
			</div>
		</form>
</div>
</body>
</html>