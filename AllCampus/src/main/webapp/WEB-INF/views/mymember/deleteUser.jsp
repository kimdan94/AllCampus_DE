<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${check}">
	<script>
		alert('탈퇴되었습니다. 올캠퍼스를 이용해주셔서 감사합니다.');
		location.href='${pageContext.request.contextPath}/main/main.do';
	</script>
</c:if>
<c:if test="${!check}">
	<script>
		alert('현재 비밀번호 불일치');
		history.go(-1);
	</script>
</c:if>