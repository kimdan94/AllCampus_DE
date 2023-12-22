<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직접 문의하기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#faq_form').submit(function(){
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
		
		if($('input[type=checkbox]:checked').length < 1){
			alert('개인정보 수집 동의를 체크하세요');
			return false;
		}
	});
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<h2>문의하기</h2>
	<form id="faq_form" action="faq.do" method="post" enctype="multipart/form-data">
			<ul>
				<li>    
					<label for="question_title">제목</label>
					<input type="text" name="question_title" id="question_title" class="input-check" maxlength="50">
				</li>
				<li>
					<label for="question_content">내용</label>
					<textarea rows="5" cols="30"
						name="question_content" id="question_content" class="input-check"></textarea>
				</li>
				<li>
					<label for="question_filename">파일</label>
					<input type="file" name="question_filename"
						id="question_filename" accept="image/gif,image/png,image/jpeg">
				</li>
				<li>
				<label for="question_board_email">email</label>
				<input type="email" id="question_board_email" class="input-check"
					 placeholder="example@email.com" required>
				<li>
				<label>개인정보 수집 동의</label>
				<input type="checkbox" name="agree" value="checked">
			</li>
			</ul>
			<div class="align-center">
 			<input type="submit" value="전송">
 			</div>
		</form>
</div>
</body>
</html>