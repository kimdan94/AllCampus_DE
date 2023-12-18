<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>FAQ</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="container">
	<h4>자주 묻는 질문</h4>
	<div class="accordion" id="accordionExample">
		<!-- 1 item start -->
		<div class="accordion-item">
			<h2 class="accordion-header" id="headingOne">
				<button class="accordion-button" type="button"
				  data-bs-toggle="collapse" 
				  data-bs-target="#collapseOne">
					Question 1
				</button>
			</h2>
			<div id="collapseOne" class="accordion-collapse collapse"
			    data-bs-parent="#accordionExample">
			    <div class="accordion-body">
			    	<strong>2023년 11월의 첫 날입니다. 활기차게 시작하자~~</strong>
			    	오후에 한 차례 비가 예상됩니다.
			    </div>
			</div>
		</div>
		<!-- 1 item end -->
		<!-- 2 item start -->
		<div class="accordion-item">
			<h2 class="accordion-header" id="headingTwo">
				<button class="accordion-button collapsed" type="button"
				  data-bs-toggle="collapse" 
				  data-bs-target="#collapseTwo">
					Question 2
				</button>
			</h2>
			<div id="collapseTwo" class="accordion-collapse collapse"
			    data-bs-parent="#accordionExample">
			    <div class="accordion-body">
			    	<strong>2023년 11월의 첫 날입니다. 활기차게 시작하자~~</strong>
			    	오후에 한 차례 비가 예상됩니다.
			    </div>
			</div>
		</div>
		<!-- 2 item end -->
		<!-- 3 item start -->
		<div class="accordion-item">
			<h2 class="accordion-header" id="headingThree">
				<button class="accordion-button collapsed" type="button"
				  data-bs-toggle="collapse" 
				  data-bs-target="#collapseThree">
					Question 3
				</button>
			</h2>
			<div id="collapseThree" class="accordion-collapse collapse"
			    data-bs-parent="#accordionExample">
			    <div class="accordion-body">
			    	<strong>2023년 11월의 첫 날입니다. 활기차게 시작하자~~</strong>
			    	오후에 한 차례 비가 예상됩니다.
			    </div>
			</div>
		</div>
		<!-- 3 item end -->
		<!-- 4 item start -->
		<div class="accordion-item">
			<h2 class="accordion-header" id="heading">
				<button class="accordion-button collapsed" type="button"
				  data-bs-toggle="collapse" 
				  data-bs-target="#collapseFour">
					Question 4
				</button>
			</h2>
			<div id="collapseFour" class="accordion-collapse collapse"
			    data-bs-parent="#accordionExample">
			    <div class="accordion-body">
			    	<strong>2023년 11월의 첫 날입니다. 활기차게 시작하자~~</strong>
			    	오후에 한 차례 비가 예상됩니다.
			    </div>
			</div>
		</div>
		<!-- 4 item end -->
	</div>
</div>
<br>
<div class="align-center">
	<input type="button" style="WIDTH: 170pt; HEIGHT: 30pt" value="직접 문의하기"
		onclick="location.href='faqForm.do'">
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
</body>
</html>
	