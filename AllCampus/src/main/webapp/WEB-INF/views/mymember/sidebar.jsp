<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header 시작 -->
<c:if test="${!empty user_num}">
<div class="side">
	<a href="${pageContext.request.contextPath}/mymember/myPage.do"><h2 style="text-align:center;">마이페이지</h2></a><br>
	<ul>
		<h3>계정</h3>
		<li>
			<a href="${pageContext.request.contextPath}/mymember/certifyForm.do">학교인증</a><p>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/mymember/modifyUserForm.do">내 정보 변경</a><p>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/mymember/modifyPasswordForm.do">비밀번호 변경</a>
		</li><br>
		<h3>내 활동</h3>
		<li>
			<a href="${pageContext.request.contextPath}/mymember/list.do">내가 쓴 글</a><p>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/mymember/listreply.do">댓글 단 글</a><p>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/mymember/listscrap.do">스크랩한 글</a>
		</li><br>
		<h3>기타</h3>
		<li>
			<a href="${pageContext.request.contextPath}/mymember/deleteForm.do">회원탈퇴</a><p>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/mymember/termForm.do">서비스 이용 약관</a>
		</li>
	</ul>
</div>
</c:if>	
<!-- header 끝 -->