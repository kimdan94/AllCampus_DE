<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	//비밀번호 유효성 체크
	$('#delete_form').submit(function(){
		if($('#passwd').val().trim()==''){
			alert('비밀번호를 입력해주세요');
			$('#passwd').val('').focus();
			return false;
		}
		let choice = confirm('정말 탈퇴하시겠습니까?');
		if(!choice){
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
		<h2>회원탈퇴</h2>
		<form action="delete.do" method="post" id="delete_form">
		<ul>
			<li>
				<h3>계정 비밀번호</h3>
				<label for="passwd"></label>
				<input type="password" name="passwd" id="passwd" maxlength="12" 
				placeholder="계정 비밀번호">
				<p>
				※탈퇴 및 가입을 반복할 경우, 서비스 악용 방지를 위해 재가입이<br>
				제한됩니다. 최초 탈퇴 시에는 가입 시점을 기준으로 1일간 제한되며,<br> 
				2회 이상 탈퇴를 반복할 경우 30일간 제한됩니다.<p>
				※탈퇴 후 개인정보, 시간표 등의 데이터가 삭제되며,복구할 수 없습니다.<br>
				※다시 가입하여도, 게시판 등 이용 제한 기록은 초기화되지 않습니다.<br>
				※작성한 게시물은 삭제되지 않으며,(알수없음)으로 닉네임이 표시됩니다.<br>
				※자세한 내용은 개인정보처리방침을 확인해주세요.
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="회원탈퇴" style="height:30px; width:550px;">
		</div>
		</form>
	</div>
</div>
</body>
</html>