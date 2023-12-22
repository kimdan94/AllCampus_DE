<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dan.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	//이벤트 연결
	$('#password_form').submit(function(){
		let items = document.querySelectorAll('input[type="password"]');
		for(let i=0;i<items.length;i++){
			if(items[i].value.trim()==''){
				let label = document.querySelector('label[for="'+items[i].id+'"]');
				alert(label.textContent + ' 항목은 필수 입력입니다.');
				items[i].value = '';
				items[i].focus();
				return false;
			}//end of if
		}//end of for
		
		//비밀번호와 비밀번호 확인 일치 여부 체크
		if($('#mem_passwd').val()!=$('#mem_cpasswd').val()){
			alert('새 비밀번호와 새 비밀번호 확인이 불일치');
			$('#mem_passwd').val('').focus();
			$('#mem_cpasswd').val('');
			return false;
		}
		//정규 표현식에 맞는지 체크
		if(!/^[A-Za-z0-9]{8,20}$/.test($('#mem_passwd').val())){
			alert('비밀번호는 8~20자의 영문, 숫자만 사용 가능합니다.');
			$('#mem_passwd').val('').focus();
			$('#mem_cpasswd').val('')
			return false;
		}//end of if
		
	});//end of submit
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/mymember/sidebar.jsp"/>
	<h2 class="header">비밀번호 변경</h2>
	<div class="content-main">
		<h2>비밀번호 수정</h2>
		<form action="modifyPassword.do" method="post" id="password_form" >
		<ul>
			<li>
				<label for="origin_passwd">현재 비밀번호</label>
				<input type="password" name="origin_passwd" id="origin_passwd" maxlength="12"
					   class="passwd_btn origin_passwd input-check">
			</li><br>
			<li>
				<span class="form-notice">* 8~20자 영문, 숫자</span><br>
				<label for="mem_passwd">새 비밀번호</label>
				<input type="password" name="mem_passwd" id="mem_passwd" maxlength="12"
					   class="passwd_btn new_passwd input-check">
			</li>
			<li>
				<label for="mem_cpasswd">새 비밀번호 확인</label>
				<input type="password" id="mem_cpasswd" maxlength="12" 
					   class="passwd_btn confirm_passwd input-check">
			</li>
		</ul><br>
		<p class="write">
				※ 혹시 타인에게 계정을 양도하려고 하시나요?<br>
				올캠퍼스 이용약관에서는 타인에게 계정 판매,양도 및 대여 등을 엄격하게 금지하고 있습니다.<br>
				모니터링 시스템에 의해 계정 양도가 적발될 경우 해당 계정은 영구 정지, 탈퇴 등의 조치가 강해지며,<br>
				계정 양도로 인해 사기,불법 행위가 발생할 경우 관련법에 따라 <b>법적 책임을 지게 될 수 있습니다.</b>
				<br><br><br>
				※ 타인에 의한 계정 사용이 의심되시나요?<br>
				개인정보 보호를 위해 비밀번호를 변경하여 주시기 바랍니다.<br>
				비밀번호를 변경하면 모든 디바이스(앱,브라우저 등)에서 즉시 로그아웃 처리됩니다.
		</p>
		<br>
		<div>
			<input type="submit" value="비밀번호 수정" class="btn">
		</div>
		</form>
	</div>
  </div>
</div>
</body>
</html>