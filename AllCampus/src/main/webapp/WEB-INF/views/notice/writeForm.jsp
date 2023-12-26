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
	$('#notice_filename').change(function(){
		let newImg = this.files[0];
		if(newImg.size > 3*1024*1024){
			alert(Math.round(newImg.size/1024) + 'kbytes(3072kbytes까지만 업로드 가능)');
			$(this).val('');//선택한 파일의 경로 정보 삭제
			return;
		}
	});//end of change
	
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
<div class="page-main-custom">
	<h2 class="board-title">공지사항 글쓰기</h2>
	<hr width="10%" class="board-underline">
	<form id="write_form" action="write.do" method="post" enctype="multipart/form-data">
			<ul>
				<li>
					<label for="notice_title">제목</label>
					<input type="text" name="notice_title" id="notice_title" 
						placeholder="제목을 입력해주세요." class="input-check" maxlength="50" size="40px">
				</li>
				<br>
				<li>
					<label for="notice_content">내용</label><br>
					<textarea rows="15" cols="100"
						name="notice_content" id="notice_content" class="input-check"></textarea>
				</li>
				<br>
				<li>
					<label for="notice_filename">파일</label>
					<input type="file" name="notice_filename"
						id="notice_filename" accept="image/gif,image/png,image/jpeg">
						<div class="file-guide">*3,072KB(3MB) 이하의 jpg, gif, png 파일만
						첨부 가능</div>
				</li>
				<br>
			</ul>    
			<div class="btn-margin2">
 			<input type="submit" value="등록하기" class="input-button4">
 			<input type="button" value="목록으로" class="input-button5" onclick="location.href='list.do'">
			</div>
		</form>
</div>
</body>
</html>