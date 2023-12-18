<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학교 인증</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dan.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	//인증 파일 첨부 여부
	$('#certify_form').submit(function(){
		if($('#mem_certifyfilename').val().trim()==''){
			alert('파일을 첨부해주세요');
			$('#mem_certifyfilename').val('').focus();
			return false;
		}
		//약관 동의 체크 여부
		if($('input[type="checkbox"]:checked').length < 1){
			alert('약관 미동의 시 인증이 불가합니다.');
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
		<h2>학교 인증 안내</h2>
		대학교에서 발급된 증명서 자료를 이용한 인증 방법입니다.<br>
		가능한 증명서 자료에는 <b>학생증,재학증명서,졸업증명서</b>가 있습니다.<br>
		발급된 증명서를 스캔하거나 사진으로 찍어 첨부해 주세요.<br>
		증명서에는 <b>발급일자,대학교,이름,학번,학과</b>가 모두 포함되어 있어야 하며,<br>
		<b>3개월 이내 발급</b>된 증명서만 유효합니다.<br>
		인증 처리에는 최대 72시간(휴일 제외)이 소요될 수 있습니다.<br>
		
		<br><br>
		
		<h2>증명서 첨부</h2>
		<form action="certifyAlert.do" method="post" enctype="multipart/form-data"
			  id="certify_form" >
		증명서 첨부(이미지 파일)<br>
		<input type="file" name="mem_certifyfilename" id="mem_certifyfilename" 
			   accept="image/gif,image/png.imgae/jpeg">
		
		<br><br><br>
		
		<h2>약관 동의</h2>
		<div class="agree" >
		 <b>약관 동의 안내</b><br>
		 도용,사문서 위조,해킹 등의 행위가 적발될 경우,관련 법에 따라 법적 책임이 따를 수 있습니다.<br>
		 회원가입 및 본인 인증 시 수집된 본인 정보와 일치하지 않는 경우 인증되지 않습니다.<br>
		 증빙 자료 사본은 3개월간 보관되고, 학번 정보는 탈퇴 후 14일간 보관되며, 이후 즉시 파기됩니다.<br>
		 자세한 내용은 개인정보처리방침을 참고하시기 바랍니다.<br>
		 커뮤니티 이용규칙은 누구나 기분 좋게 참여할 수 있는 커뮤니티를 만들기 위해 제정되었습니다.<br> 서비스 내 모든
		 커뮤니티는 커뮤니티 이용규칙에 의해 운영되므로, 이용자는 커뮤니티 이용 전 반드시 모든 내용을 숙지하여야 합니다.
		</div><p>
		<input type="checkbox" name="checkbox" id="input-check">개인정보 수집 및 이용 동의(필수)
		<div><br>
			<input type="submit" value="인증 요청하기" class="btn">
		</div>
		</form>
	</div>
	
</div>
</body>
</html>