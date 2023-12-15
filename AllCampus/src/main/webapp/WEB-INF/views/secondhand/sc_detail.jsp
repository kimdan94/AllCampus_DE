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
<div class="detail-main" style="margin-left:210px;">
	<div class="align-right">
		<ul>
			<li>
				<!-- 판매 여부 토글 필요 -->
				<input type="button" value="목록"
					onclick="location.href='secondhand_list.do'">
				<c:if test="${user_num == sc.mem_num}">
					<input type="button" value="수정"
						onclick="location.href='updateForm.do?secondhand_num=${sc.secondhand_num}}'">
					<input type="button" value="삭제" id="delete_btn">
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
	<div style="border:1px solid black">
		<h3>${sc.secondhand_name}</h3>
		${sc.mem_id}
		<table>
			<tr>
				<th>저자</th>
				<td>${sc.secondhand_writer}</td>
			</tr>
			<tr>
				<th>출판사</th>
				<td>${sc.secondhand_company}</td>
			</tr>
			<tr class="align-right">
				<td>
					<c:if test="${!empty sc.secondhand_modifydate}">
						수정일 : <fmt:formatDate value="${sc.secondhand_modifydate}" type="date" dateStyle="full"/>
					</c:if>
					등록일 : <fmt:formatDate value="${sc.secondhand_reg_date}" type="date" dateStyle="full"/>
				</td>
			</tr>
			<tr class="align-right">
				<td><fmt:formatNumber value="${sc.secondhand_price}"/>원</td>
			</tr>
		</table>
	</div>
	<div style="border:1px solid black">
		${sc.secondhand_content}
		<p>
		<img src="${pageContext.request.contextPath}/upload/${sc.secondhand_filename}" width="150">
		<table>
			<tr>
				<th>교재 상태</th>
				<td>${sc.secondhand_status}</td>
			</tr>
			<tr>
				<th>거래 수단/방법</th>
				<td>${sc.secondhand_way}</td>
			</tr>
			<tr>
				<th>오픈 카카오톡 URL</th>
				<td>${sc.secondhand_openchat}</td>
			</tr>
		</table>
	</div>
</div>
</div>
</body>
</html>