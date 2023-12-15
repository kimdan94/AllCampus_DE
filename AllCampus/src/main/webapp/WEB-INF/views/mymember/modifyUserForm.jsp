<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 변경</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

//닉네임

$(function(){
	let nickChecked = 0; //0은 중복체크 미실행 또는 중복, 1은 미중복
	//닉네임 중복체크
	$('#mem_nick_check').click(function(){
		if(!/^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{4,12}/.test($('#mem_nick').val())){
			alert('한글,영문 또는 숫자 사용, 최소 4자 ~ 최대 12자를 사용하세요!');
			$('#mem_nick').val('').focus();
			return false;
		}
		//서버와 통신
		$.ajax({
			url:'checkMyNick.do',
			type:'post',
			data:{mem_nick:$('#mem_nick').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'nickNotFound'){
					nickChecked = 1;
					$('#message_mem_nick').css('color','#000000').text('등록 가능 닉네임');
				}else if(param.result == 'isDuplicated'){
					nickChecked = 0;
					$('#message_mem_nick').css('color','red').text('중복된 닉네임');
					$('#mem_nick').val('').focus();
				}else{
					nickChecked = 0;
					alert('닉네임 중복체크 오류 발생');
				}
			},
			error:function(){
				nickChecked = 0;
				alert('네트워크 오류 발생');
			}
		});
	});
	
	//닉네임 중복 안내 메시지 초기화 및 닉네임 중복 값 초기화
	$('#mem_nick').keydown(function(){
		nickChecked = 0;
		$('#message_mem_nick').text('');
	});//end of keydown
	
	//닉네임,전공 유효성 체크
	$('#modify_form').submit(function(){
		if($('#mem_nick').val().trim()==''){
			alert('닉네임을 입력해주세요');
			$('#mem_nick').val('').focus();
			return false;
		}
		if(nickChecked == 0){
			alert('닉네임 중복체크는 필수입니다.');
			return false;
		}
		if($('#mem_major').val().trim()==''){
			alert('전공을 입력해주세요');
			$('#mem_major').val('').focus();
			return false;
		}
	});
	
//프로필 이미지

	//프로필 이미지
	$('#photo_btn').click(function(){
		$('#photo_choice').show();
		$(this).hide();//수정 버튼 감추기
	});
	
	//프로필 이미지 미리 보기
	let photo_path = $('.my-photo').attr('src');//처음 화면에 보여지는 이미지 읽기
	$('#mem_photo').change(function(){
		let my_photo = this.files[0];
		if(!my_photo){
			//선택을 취소하면 원래 처음 화면으로 되돌림
			$('.my-photo').attr('src',photo_path);
			return;
		}
		if(my_photo.size > 1400 * 1400){
			alert(Math.round(my_photo.size/1400) + 'kbytes(1400kbytes까지만 업로드 가능)');
			//용량에 걸리면 원래 처음 화면으로 되돌림
			$('.my-photo').attr('src',photo_path);
			$(this).val('');//선택한 파일 정보 지우기
			return;
		}
		
		//화면에서 프로필 이미지 미리 보기
		const reader = new FileReader();
		reader.readAsDataURL(my_photo);
		
		reader.onload=function(){
		$('.my-photo').attr('src',reader.result);
		return;
		}
		
	});//end of change
	
	//프로필 이미지 전송
	$('#mem_photo_submit').click(function(){
		if($('#mem_photo').val()==''){
			alert('파일을 선택하세요!');
			$('#mem_photo').focus();
			return;
		}
		
		//파일 전송
		const form_data = new FormData();
		
		form_data.append('mem_photo',$('#mem_photo')[0].files[0]);
		$.ajax({
			url:'modifyMyPage.do',
			type:'post',
			data:form_data,
			dataType:'json',
			contentType:false,//데이터 객체를 문자열로 바꿀지에 대한 값. true는 일반문자
			processData:false,//해당 타입을 true로 하면 일반 text로 구분
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요!');
				}else if(param.result == 'success'){
					alert('프로필 사진이 수정되었습니다.');
					//수정된 이미지 정보 저장
					photo_path = $('.my-photo').attr('src');
					$('#mem_photo').val('');
					$('#photo_choice').hide();//초기화 작업
					$('#photo_btn').show();//수정 버튼 표시
				}else{
					alert('파일 전송 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});	//end of click
	
	//프로필 이미지 미리보기 취소
	$('#mem_photo_reset').click(function(){
		//초기 이미지 표시
		//이미지 미리보기 전 이미지로 되돌리기
		$('.my-photo').attr('src',photo_path);
		$('#mem_photo').val('');
		$('#photo_choice').hide();
		$('#photo_btn').show();//수정 버튼 표시
	});
});		
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<form action="modifyNickMajor.do" method="post" id="modify_form">
		<h2>프로필 이미지 변경</h2>
		<ul>
			<li>
				<c:if test="${empty member.mem_photo}">
				<img src="${pageContext.request.contextPath}/images/face.jpeg"
				width="100" height="100" class="my-photo">
				</c:if>
				<c:if test="${!empty member.mem_photo}">
				<img src="${pageContext.request.contextPath}/upload/${member.mem_photo}"
				width="100" height="100" class="my-photo">
				</c:if>
			</li>
		<li>
			<div class="align-center">
				<input type="button" value="수정" id="photo_btn">
			</div>
			<div id="photo_choice" style="display:none;">
				<input type="file" id="mem_photo" accept="image/gif,image/png,image/jpeg">
				<br>
				<input type="button" value="전송" id="mem_photo_submit">
				<input type="button" value="취소" id="mem_photo_reset">
			</div>
		</li>
		</ul><br>
		<h2>닉네임 변경</h2>
		<div class="form-notice">*한글, 영문, 숫자 사용한 4~12자</div>
		닉네임
		
		<input type="text" name="mem_nick" id="mem_nick" maxlength="12" value="${member.mem_nick}"
			   class="input-check" style="height:30px; width:300px;">
		<input type="button" value="닉네임 중복체크" id="mem_nick_check" style="height:25px; width:120px;" >
		<span id="message_mem_nick"></span>
		<br><br>
		
		<h2>학과 설정</h2>
		<h3>주전공 변경</h3>
		<input type="text" name="mem_major" id="mem_major" maxlength="12" value="${member.mem_major}"
			placeholder="주전공을 입력하세요" class="input-check" style="height:30px; width:300px;"><br>
		※입력 시 본인 학생증에 기재된 전공명을 동일하게 입력하시오.<br>
		학과명이 다를 시 학교 인증 과정에서 불이익이 있을 수 있습니다.<br>
		예시)정치외교학과
		<h3>전공 추가 변경(선택)</h3>
		<input type="text" name="mem_major2" id="mem_major2" maxlength="12"
			placeholder="부전공을 입력하세요" class="input-check" style="height:30px; width:300px;">
			<br><br>
		<div class="align-center">
			<input type="submit" value="저장" style="height:30px; width:550px;">
		</div>
		</form>
	</div>
</div>
</body>
</html>