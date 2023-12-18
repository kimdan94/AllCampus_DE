<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	let idChecked = 0;//0 : 중복 체크 미실행 또는 중복, 1 : 미중복
	let emailChecked = 0;
	let nickChecked = 0;
	let emailAt = /[@]/;
	//아이디 중복 체크
	$('#id_check').click(function(){
		if(!/^[A-Za-z0-9]{4,12}$/.test($('#id').val())){
			alert('아이디는 4~12자의 영문, 숫자만 사용 가능합니다.');
			$('#id').val('').focus();
			return;
		} 
		//서버와 통신
	 	$.ajax({
			url:'checkDuplicated.do',
			type:'post',
			data:{id:$('#id').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'NotFound'){
					idChecked = 1;
					alert('[' + $('#id').val() + ']은/는 사용 가능한 아이디입니다.');
					$('#id_check').attr('disabled','disabled');
				}else if(param.result == 'Duplicated'){
					idChecked = 0;
					alert('[' + $('#id').val() + ']은/는 사용 중인 아이디입니다.');
				}else{
					idChecked = 0;
					alert('아이디 중복 체크 오류 발생');
				}
			},
			error:function(){
				idChecked = 0;
				alert('네트워크 오류 발생');
			}
		});
	});//end of click - id
	
	//이메일 중복 체크
	$('#email_check').click(function(){
		if(!emailAt.test($('#email').val())){
			alert('이메일 주소에 @를 포함해주세요.');
			return;
		}
		//서버와 통신
		$.ajax({
			url:'checkDuplicated.do',
			type:'post',
			data:{email:$('#email').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'NotFound'){
					emailChecked = 1;
					alert('[' + $('#email').val() + ']은/는 사용 가능한 이메일입니다.');
					$('#email_check').attr('disabled','disabled');
				}else if(param.result == 'Duplicated'){
					emailChecked = 0;
					alert('[' + $('#email').val() + ']은/는 사용 중인 이메일입니다.');
				}else{
					emailChecked = 0;
					alert('이메일 중복 체크 오류 발생');
				}
			},
			error:function(){
				emailChecked = 0;
				alert('네트워크 오류 발생');
			}
		});
	});//end of click - email
	
	//닉네임 중복 체크
	$('#nick_check').click(function(){
		if(!/^[A-Za-z0-9가-힣]{4,12}$/.test($('#nick').val())){
			alert('닉네임은 4~12자의 한글, 영문, 숫자만 사용 가능합니다.');
			$('#nick').val('').focus();
			return;
		}
		//서버와 통신
		$.ajax({
			url:'checkDuplicated.do',
			type:'post',
			data:{nick:$('#nick').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'NotFound'){
					nickChecked = 1;
					alert('[' + $('#nick').val() + ']은/는 사용 가능한 닉네임입니다.');
					$('#nick_check').attr('disabled','disabled');
				}else if(param.result == 'Duplicated'){
					nickChecked = 0;
					alert('[' + $('#nick').val() + ']은/는 사용 중인 닉네임입니다.');
				}else{
					nickChecked = 0;
					alert('닉네임 중복 체크 오류 발생');
				}
			},
			error:function(){
				nickChecked = 0;
				alert('네트워크 오류 발생');
			}
		});
	});//end of click - nick
	
	//아이디 중복 안내 및 아이디 중복 값 초기화
	$('#register_form #id').keydown(function(){
		idChecked = 0;
		document.getElementById('id_check').removeAttribute("disabled");
	});//end of keydown - id
	
	//이메일 중복 안내 및 이메일 중복 값 초기화
	$('#register_form #email').keydown(function(){
		emailChecked = 0;
		document.getElementById('email_check').removeAttribute("disabled");
	});//end of keydown - email
	
	//닉네임 중복 안내 및 닉네임 중복 값 초기화
	$('#register_form #nick').keydown(function(){
		nickChecked = 0;
		document.getElementById('nick_check').removeAttribute("disabled");
	});//end of keydown - nick

	//회원정보 등록 유효성 체크
	$('#register_form').submit(function(){
		//비밀번호와 비밀번호 확인 일치 여부 체크
		if($('#passwd').val()!=$('#cpasswd').val()){
			alert('비밀번호와 재입력한 비밀번호가 불일치합니다.');
			$('#passwd').val('').focus();
			$('#cpasswd').val('');
			return false;
		}
		
		let items = document.querySelectorAll('.input-check');
		for(let i=0;i<items.length;i++){
			if(items[i].value.trim()==''){
				let label = document.querySelector('label[for="' + items[i].id + '"]');
				alert(label.textContent + ' 항목이 기입되지 않았습니다.');
				items[i].value = '';
				items[i].focus();
				return false;
			}//end of if
			
			//정규 표현식에 맞는지 한 번 더 체크 - 학번, 아이디, 비밀번호, 닉네임
			if(items[i].id == 'univNum' && !/[0-9]{7,9}/.test($('#univNum').val())){
				alert('학번은 7~9자리 숫자만 입력 가능합니다.');
				$('#univNum').val('').focus();
				return false;
			}
			if(items[i].id == 'id' && !/^[A-Za-z0-9]{4,12}$/.test($('#id').val())){
				alert('아이디는 4~12자의 영문, 숫자만 사용 가능합니다.');
				$('#id').val('').focus();
				return false;
			}
			if(items[i].id == 'passwd' && !/^[A-Za-z0-9]{8,20}$/.test($('#passwd').val())){
				alert('비밀번호는 8~20자의 영문, 숫자만 사용 가능합니다.');
				$('#passwd').val('').focus();
				return false;
			}
			
			if(items[i].id == 'nick' && !/^[A-Za-z0-9가-힣]{4,12}$/.test($('#nick').val())){
				alert('닉네임은 4~12자의 한글, 영문, 숫자만 사용 가능합니다.');
				$('#nick').val('').focus();
				return false;
			}
			
			//아이디 중복 체크 여부 체크
			if(items[i].id == 'id' && idChecked==0){
				alert('아이디 중복 체크는 필수입니다.');
				return false;
			}
			
			//이메일 중복 체크 여부 체크
			if(items[i].id == 'email' && emailChecked==0){
				alert('이메일 중복 체크는 필수입니다.');
				return false;
			}
			
			//닉네임 중복 체크 여부 체크
			if(items[i].id == 'nick' && nickChecked==0){
				alert('닉네임 중복 체크는 필수입니다.');
				return false;
			}	
		}//end of for
		
		//약관 동의 체크 여부
		if($('input[type="checkbox"]:checked').length < 1){
			alert('약관 미동의 시 가입이 불가합니다.');
			return false;
		}
	});//end of submit
});
</script>
</head>
<body>
<div class="page-main">
	<img src="${pageContext.request.contextPath}/images/logo_typo_231208.png" width="250">
	<hr size="1" noshade="noshade" width="100%">
	<form id="register_form" action="registerUser.do" method="post">
		<ul>
			<li>
				<label for="univ">학교 선택</label>
				<select name="univ" size="1">
					<c:forEach var="univName" items="${list}">
						<option value="${univName.univ_num}">${univName.univ_name}</option>
					</c:forEach>
				</select>
				<label for="major">학과 입력</label>
				<input type="text" name="major" id="major" maxlength="33" class="input-check"
					size="40px" autocomplete="off" placeholder="하나만 입력해주세요.">
			</li>
			<li>
				<label for="univNum">학번</label>
				<input type="text" name="univNum" id="univNum" maxlength="9"
					size="25px" placeholder="학번을 정확히 입력해주세요." class="input-check">
			</li>
			<li>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" maxlength="6" class="input-check">
			</li>
			<li>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" maxlength="12" class="input-check">
				<input type="button" value="아이디 중복 체크" id="id_check" class="check-btn">
				<div class="form-notice">* 4~12자 영문, 숫자</div>	
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" 
					size="25px" maxlength="20" class="input-check">
				<div class="form-notice">* 8~20자 영문, 숫자</div>	
			</li>
			<li>
				<label for="cpasswd">비밀번호 확인</label>
				<input type="password" name="cpasswd" id="cpasswd" maxlength="20" 
					size="25px" placeholder = "비밀번호를 재입력해주세요." class="input-check">
			</li>
			<li>
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" maxlength="30" class="input-check">
				<input type="button" value="이메일 중복 체크" id="email_check" class="check-btn">
			</li>
			<li>
				<label for="nick">닉네임</label>
				<input type="text" name="nick" id="nick" maxlength="12" class="input-check">
				<input type="button" value="닉네임 중복 체크" id="nick_check" class="check-btn">
				<div class="form-notice">* 4~12자 한글, 영문, 숫자</div>	
			</li>
			<li>
				<label for="agree">약관 동의 (필수)</label>
				<input type="checkbox" value="동의" 
					name="agree" id="check_agree">아래 약관에 모두 동의합니다.
				<div class="agree-div">
					<b>수집하는 개인정보의 항목</b>
					<br>
					회사는 서비스 제공을 위해, 회원가입 시점에 다음에 해당하는 개인정보를 수집합니다.<br>
					1. 학교, 아이디, 비밀번호, 이메일, 이름, 입학연도, 닉네임, 학과<br>
					<b>수집한 개인정보의 처리 목적</b>
					<br>
					회원가입 시점에 수집된 개인정보는 다음의 목적에 한해 이용됩니다.<br>
					1. 가입 및 탈퇴 의사 확인, 회원 식별 및 관리, 재학생 및 졸업생 확인<br>
					2. 개인정보 및 관심에 기반한 이용자 친화적 서비스 제공 및 기존, 신규 시스템 개발, 유지, 개선<br>
					3. 불법, 약관 위반 게시물 게시와 같은 부정행위 방지를 위한 운영 시스템 개발, 유지, 개선<br>
					4. 문의, 제휴 문의, 광고 문의, 게시 요청, 교내단체 게시판 개설 요청 관련 응대 및 처리<br>
					5. 회원 관리, 서비스 운영 및 유지보수를 위한 통계 자료 도출<br>
					<b>수집한 개인정보의 보관 및 파기</b>
					<br>
					회사는 서비스를 제공하는 동안 개인정보 처리방침 및 관련법에 의거하여 회원의 개인정보를 지속적으로 관리 및 보관합니다.<br>
					1. 회원 탈퇴 시 ID, 닉네임을 제외한 개인정보는 즉시 파기합니다. (ID, 닉네임 : 3년간 보관)<br>
					<b>부모님 친구 등 타인의 명의로 가입할 수 없습니다.</b>
					<br>
					올캠퍼스는 철저한 학교 인증과 안전한 익명 커뮤니티를 제공하기 위해,<br>
					가입 시 본인 인증 수단을 통해 본인 여부를 확인하고, 커뮤니티 이용 시 증명 자료 제출을 통해<br>
					재학 여부를 확인합니다. 두 정보가 일치하지 않을 경우 서비스를 이용하실 수 없습니다.
					<br>
					<b>만 14세 이상입니다.</b>
					<br>
					올캠퍼스는 국내 대학생을 위한 서비스이며, 본인 인증을 통해 만 14세 이상만 가입할 수 있습니다.
				</div>
			</li>
		</ul>
		<input type="submit" value="올캠퍼스 가입하기" class="input-button2" style="font-size:15px;">
		<input type="button" value="처음으로" class="input-button1" style="font-size:15px;"
			onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</form>
	<div style="margin-bottom:3px;">
	<a href="#" class="bottom-style">문의하기</a>
	<a href="${pageContext.request.contextPath}/mymember/termForm.do" class="bottom-style">이용약관</a>
	<br>
	<div class="copyright">copyright(c) 2023. 올캠퍼스. All rights reserved</div>
	</div>
</div>
</body>
</html>