<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직접 문의하기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/yen.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#question_filename').change(function(){
		let newImg = this.files[0];
		if(newImg.size > 3*1024*1024){
			alert(Math.round(newImg.size/1024) + 'kbytes(3072kbytes까지만 업로드 가능)');
			$(this).val('');//선택한 파일의 경로 정보 삭제
			return;
		}
	});//end of change
	
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
<div class="page-main-custom">
	<h2 class="board-title">문의하기</h2>
	<hr width="10%" class="board-underline">
	<form id="faq_form" action="faq.do" method="post" enctype="multipart/form-data">
			<ul>
				<li>    
					<label for="question_title">제목</label>
					<input type="text" name="question_title" id="question_title" 
						placeholder="제목을 입력해주세요." size="40px" class="input-check" maxlength="50">
				</li>
				<br>
				<li>
					<label for="question_content">내용</label><br>
					<textarea rows="15" cols="100"
						name="question_content" id="question_content" class="input-check"></textarea>
				</li>
				<br>
				<li>
					<label for="question_filename">파일</label>
					<input type="file" name="question_filename"
						id="question_filename" accept="image/gif,image/png,image/jpeg" 
						class="input-check">
						<div class="file-guide">*3,072KB(3MB) 이하의 jpg, gif, png 파일만
						첨부 가능</div>
				</li>
				<br>
				<li>
				<label for="question_board_email">연락받을 이메일</label>
				<input type="email" id="question_board_email" class="input-check"
					 placeholder="example@email.com" required>
				<li>
				<br>
				<label>개인정보 수집 및 이용</label>
				<br>
				<div>개인정보 수집 및 이용 동의(필수)<input type="checkbox" name="agree" value="checked"></div><br>
				<div style="color: gray; border: 1px solid gray; padding: 10px; width:570px;">문의 처리를 위해 이메일, 회원정보, 기기정보, 문의내용에 포함된 개인정보를 수집하며,개인정보
				처리방침에 따라 3년 후 파기됩니다.</div>
			</li>
			<br>
			</ul>
			<div class="align-center">
 			<input type="submit" value="문의 접수하기" class="input-button4">
 			</div>
		</form>
</div>
</body>
</html>