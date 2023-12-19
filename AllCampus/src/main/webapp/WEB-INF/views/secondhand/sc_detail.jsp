<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${sc_name} - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/secondhand.sell.js"></script>
<script type="text/javascript">
$(function(){
	$('#secondhand_complaint').click(function(){
		let choice = confirm('신고하시겠습니까?');
		if(choice){
			$.ajax({
				url:'secondhand_complaint.do',
				type:'post',
				data:{secondhand_num:${sc.secondhand_num}},
				dataType:'json',
				success:function(param){
					if(param.status == 'success'){
						alert('신고 완료되었습니다.');
						location.href='secondhand_detail.do?secondhand_num=${sc.secondhand_num}';
					}else if(param.status == 'duplicated'){
						alert('이미 신고 처리된 게시글입니다.');
						location.href='secondhand_detail.do?secondhand_num=${sc.secondhand_num}';
					}else if(param.status == 'noLogin'){
						alert('로그인 후 이용 가능합니다.');
						location.href='${pageContext.request.contextPath}/member/loginForm.do';
					}else if(param.status == 'noCertify'){
						alert('학교 인증을 마친 학생들만 이용할 수 있어요!');
					}else{
						alert('신고 처리 오류 발생');
					}
				},
				error:function(){
					alert('이미 신고되어 숨겨진 게시물입니다.');
				}
			});
		}
	});
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
<div class="detail-main">
	<div class="align-right">
		<ul>
			<li>
				<img id="sell_status" data-num="${sc.secondhand_num}" class="toggle-style"
					src="${pageContext.request.contextPath}/images/toggleon.png" width="45"
					<c:if test="${user_num != sc.mem_num}">hidden</c:if>>
					<br>
				<span id="sell_text">[판매 중]</span>
				<input type="button" value="목록" class="sc-btn"
					onclick="location.href='secondhand_list.do'">
				<c:if test="${user_num == sc.mem_num}">
					<input type="button" value="수정" class="sc-btn"
						onclick="location.href='secondhand_updateForm.do?secondhand_num=${sc.secondhand_num}'">
					<input type="button" value="삭제" id="delete_btn" class="sc-btn">
					<script type="text/javascript">
						let delete_btn = document.getElementById('delete_btn');
						//이벤트 연결
						delete_btn.onclick = function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								location.replace('secondhand_delete.do?secondhand_num=${sc.secondhand_num}');
							}
						};
					</script>
				</c:if>
			</li>
		</ul>
	</div>
	<div style="border:1px solid black;">
		<h3 style="padding-left:10px;">${sc.secondhand_name}</h3>
		<table class="sc-detail">
			<tr>
				<td style="padding-left:10px;"><b>저자</b> : ${sc.secondhand_writer}</td>
			</tr>
			<tr>
				<td style="padding-left:10px;"><b>출판사</b> : ${sc.secondhand_company}</td>
			</tr>
			<tr>
				<td style="padding-left:620px;"><b>판매자</b> : ${sc.mem_id}</td>
			</tr>
			<tr class="align-right">
				<td style="padding-left:620px;">
					<c:if test="${!empty sc.secondhand_modifydate}">
						<b>수정일</b> : <fmt:formatDate value="${sc.secondhand_modifydate}" type="date" dateStyle="full"/>
					</c:if>
					<c:if test="${empty sc.secondhand_modifydate}">
					<b>등록일</b> : <fmt:formatDate value="${sc.secondhand_reg_date}" type="date" dateStyle="full"/>
					</c:if>
				</td>
			</tr>
			<tr class="sc-price">
				<td><fmt:formatNumber value="${sc.secondhand_price}"/>원</td>
			</tr>
		</table>
	</div>
	<br>
	<div style="border:1px solid black;padding-left:10px;">
		<p>${sc.secondhand_content}</p>
		<p>
		<img src="${pageContext.request.contextPath}/upload/${sc.secondhand_filename}" 
			width="150" style="padding-left:10px;" id="sc_img">
		<table class="sc-choice">
			<tr>
				<th>교재 상태</th>
				<td>${sc.secondhand_status}</td>
			</tr>
			<tr>
				<th>거래 수단/방법</th>
				<td>${sc.secondhand_way}</td>
			</tr>
			<tr>
				<th style="padding-right:13px;padding-bottom:10px;">오픈 카카오톡 URL</th>
				<td style="padding-bottom:10px;"><a href="${sc.secondhand_openchat}">${sc.secondhand_openchat}</a></td>
			</tr>
		</table>
	</div>
	<div class="align-right" style="margin-top:10px;">
		<a href="#" id="secondhand_complaint"><b>[신고하기]</b></a>
	</div>
</div>
</div>
</body>
</html>