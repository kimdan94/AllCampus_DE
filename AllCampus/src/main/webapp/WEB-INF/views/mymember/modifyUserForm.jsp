<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 변경 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dan.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mymember.modify.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/mymember/sidebar.jsp"/>
	<h2 class="header">내 정보 변경</h2>
	<div class="content-main">
		<form action="modifyNickMajor.do" method="post" id="modify_form">
		<h2>프로필 이미지 변경</h2><br>
		<ul>
			<li>
				<c:if test="${empty member.mem_photo}">
				<img src="${pageContext.request.contextPath}/images/face.png"
				width="120" height="120" class="my-photo">
				</c:if>
				<c:if test="${!empty member.mem_photo}">
				<img src="${pageContext.request.contextPath}/upload/${member.mem_photo}"
				width="120" height="120" class="my-photo">
				</c:if>
			</li>
		<li>
			<div>
				<input type="button" value="수정" id="photo_btn" class="modify-btn">
			</div>
			<div id="photo_choice" style="display:none;">
			 <div class="filebox">
				<input class="upload-name" value="첨부파일" placeholder="첨부파일">
				<label for="mem_photo">파일 찾기</label>
				<input type="file" id="mem_photo" accept="image/gif,image/png,image/jpeg">
			</div>	
				<br>
				<input type="button" value="전송" id="mem_photo_submit" class="submit-btn">
				<input type="button" value="취소" id="mem_photo_reset" class="submit-btn">
			</div>
		</li>
		</ul>
		</form>
		<br>
		<form action="modifyNick.do" method="post" id="modify_nick">
		<h2>닉네임 변경</h2><br>
		<div class="form-notice write">*한글, 영문, 숫자 사용한 4~12자</div>
		<div class="write">닉네임
		
		<input type="text" name="mem_nick" id="mem_nick" maxlength="12" value="${member.mem_nick}"
			   class="input-check" style="height:30px; width:300px;">
		<input type="button" value="닉네임 중복체크" id="mem_nick_check" class="nick" >
		<span id="message_mem_nick"></span><br><br><br>
		<input type="submit" value="수정" class="modify">
		</div>
		</form>
		<br>
		
		<form action="modifyMajor.do" method="post" id="modify_major">
		<h2>학과 설정</h2>
		<div class="write">
		<h3>주전공 변경</h3>
		<input type="text" name="mem_major" id="mem_major" maxlength="12" value="${member.mem_major}"
			placeholder="주전공을 입력하세요" class="input-check" style="height:30px; width:300px;"><br>
		<br>※ 입력 시 본인 학생증에 기재된 전공명을 동일하게 입력하시오.<br>
		학과명이 다를 시 학교 인증 과정에서 불이익이 있을 수 있습니다.<br>
		예시) 정치외교학과
		<h3>전공 추가 변경(선택)</h3>
		<input type="text" name="mem_major2" id="mem_major2" maxlength="12"  value="${member.mem_major2}"
			placeholder="부전공을 입력하세요" class="input-check" style="height:30px; width:300px;">
		</div>	
		<div><br><br>
			<input type="submit" value="수정" class="modify">
		</div>
		</form>
	</div>
  </div>
</div>
</body>
</html>