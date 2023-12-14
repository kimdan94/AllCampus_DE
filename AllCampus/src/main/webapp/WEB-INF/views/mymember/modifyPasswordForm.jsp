<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	//이벤트 연결
	$('#password_form').submit(function(){
		let items = document.querySelectorAll('input[type="password"]');
		for(let i=0;i<items.length;i++){
			if(items[i].value.trim()==''){
				let label = document.querySelector('label[for="'+items[i].id+'"]');
				alert(label.textContent + ' 항목은 필수 입력');
				items[i].value = '';
				items[i].focus();
				return false;
			}
		}
		
		if($('#mem_passwd').val()!=$('#mem_cpasswd').val()){
			alert('새 비밀번호와 새 비밀번호 확인이 불일치');
			$('#mem_passwd').val('').focus();
			$('#mem_cpasswd').val('');
			return false;
		}
	});
});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>비밀번호 수정</h2>
		<form action="modifyPassword.do" method="post" id="password_form" >
		<ul>
			<li>
				<label for="origin_passwd">현재 비밀번호</label>
				<input type="password" name="origin_passwd" id="origin_passwd" maxlength="12">
			</li>
			<li>
				<label for="mem_passwd">새 비밀번호</label>
				<input type="password" name="mem_passwd" id="mem_passwd" maxlength="12">
			</li>
			<li>
				<label for="mem_cpasswd">새 비밀번호 확인</label>
				<input type="password" id="mem_cpasswd" maxlength="12">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="비밀번호 수정">
		</div>
		</form>
	</div>
</div>
</body>
</html>