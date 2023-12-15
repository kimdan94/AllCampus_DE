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
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
<div class="detail-main" style="margin-left:370px;">
	<div class="align-right">
		<ul>
			<li>
				<!-- 판매 여부 토글 필요 -->
				<input type="button" value="목록" class="sc-btn"
					onclick="location.href='secondhand_list.do'">
				<c:if test="${user_num == sc.mem_num}">
					<input type="button" value="수정" class="sc-btn"
						onclick="location.href='updateForm.do?secondhand_num=${sc.secondhand_num}}'">
					<input type="button" value="삭제" id="delete_btn" class="sc-btn">
					<script type="text/javascript">
						let delete_btn = document.getElementById('delete_btn');
						//이벤트 연결
						delete_btn.onclick = function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								location.replace('delete.do?secondhand_num=${sc.secondhand_num}');
							}
						};
					</script>
				</c:if>
			</li>
		</ul>
	</div>
	<div style="border:1px solid black;">
		<h3>${sc.secondhand_name}</h3>
		<table>
			<tr>
				<td><b>저자</b> : ${sc.secondhand_writer}</td>
			</tr>
			<tr>
				<td><b>출판사</b> : ${sc.secondhand_company}</td>
			</tr>
			<tr>
				<td style="padding-left:620px;"><b>판매자</b> : ${sc.mem_id}</td>
			</tr>
			<tr class="align-right">
				<td style="padding-left:620px;">
					<c:if test="${!empty sc.secondhand_modifydate}">
						<b>수정일</b> : <fmt:formatDate value="${sc.secondhand_modifydate}" type="date" dateStyle="full"/>
					</c:if>
					<b>등록일</b> : <fmt:formatDate value="${sc.secondhand_reg_date}" type="date" dateStyle="full"/>
				</td>
			</tr>
			<tr class="align-right" style="color:#6699cc;font-weight:bold;font-size:25px;">
				<td><fmt:formatNumber value="${sc.secondhand_price}"/>원</td>
			</tr>
		</table>
	</div>
	<div style="border:1px solid black;">
		${sc.secondhand_content}
		<p>
		<img src="${pageContext.request.contextPath}/upload/${sc.secondhand_filename}" width="150">
		<table style="text-align:left;">
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
				<td style="padding-bottom:10px;">${sc.secondhand_openchat}</td>
			</tr>
		</table>
	</div>
</div>
</div>
</body>
</html>