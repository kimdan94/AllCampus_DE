<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴  - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dan.css">
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
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/mymember/sidebar.jsp"/>
	<h2 class="header">회원탈퇴</h2>
	<div class="content-main">
		<h2>탈퇴안내</h2>
		<form action="delete.do" method="post" id="delete_form">
		<ul>
			<li>
				<h3>계정 비밀번호</h3>
				<label for="passwd"></label>
				<input type="password" name="passwd" id="passwd" maxlength="12" 
				placeholder="계정 비밀번호" class="delete_passwd input-check">
				<p>
				※ 탈퇴 및 가입을 반복할 경우, 서비스 악용 방지를 위해 재가입이 제한됩니다.<br>
				최초 탈퇴 시에는 가입 시점을 기준으로 1일간 제한되며,<br> 
				2회 이상 탈퇴를 반복할 경우 30일간 제한됩니다.<p>
				※ 탈퇴 후 개인정보, 시간표 등의 데이터가 삭제되며,복구할 수 없습니다.<br>
				※ 다시 가입하여도, 게시판 등 이용 제한 기록은 초기화되지 않습니다.<br>
				※ 작성한 게시물은 삭제되지 않으며, (알수없음)으로 닉네임이 표시됩니다.<br>
				※ 자세한 내용은 개인정보처리방침을 확인해주세요.
			</li>
		</ul><br>
		<div>
			<input type="submit" value="회원탈퇴" class="btn">
		</div>
		</form>
	</div>
  </div>	
</div>
</body>
</html>