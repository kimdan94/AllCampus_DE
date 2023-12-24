<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강의평 작성 - 올캠퍼스</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiwonstyle.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
function getSemesterLabel(semesterValue) {
    switch (semesterValue) {
        case 0:
            return ' 1학기'; 
        case 1:
            return ' 여름';
        case 2:
            return ' 2학기';
        case 3:
            return ' 겨울';
        default:
            return 'Unknown Semester';
    }
}
$(document).ready(function () {
    let myForm = document.getElementById('write_form');
    let keyword = document.getElementById('keyword');
    let courseList = document.getElementById('courseList');
    
    myForm.onsubmit = function () {
        let keyword = document.getElementById('keyword');
        if (keyword.value.trim() == '') {
            alert('검색어를 입력하세요!');
            keyword.value = '';
            keyword.focus();
            return false;
        }
        
        let courseList = document.getElementById('courseList');
        if (courseList.value.trim() == '') {
            alert('과목을 선택하세요!');
            courseList.focus();
            return false;
        }
        
        let eva_star = document.getElementById('eva_star');
        if(eva_star.value.trim()==''){
        	alert('강의 등급을 선택하세요!');
        	eva_star.focus();
        	return false;
        }
        let eva_content = document.getElementById('eva_content');
        if(eva_content.value.trim()==''){
        	alert('내용을 입력하세요!');
        	eva_content.focus();
        	return false;
        }
        let evasemesterList = document.getElementById('evasemesterList');
        if(evasemesterList.value.trim()==''){
        	alert('수강학기를 선택하세요!');
        	evasemesterList.focus();
        	return false;
        }
    };
    
    $('#button').click(function () {
        $.ajax({
            url: 'searchcoursename.do',
            type: 'post',
            data: { keyword: keyword.value },
            dataType: 'json',
            success: function (param) {
                courseList.innerHTML = '';
                $('#courseList').append('<option value="" selected>과목을 선택하세요</option>');
                $.each(param.list, function (index, course) {
                    $('#courseList').append('<option value="' + course.course_num + '">' + course.course_name + '(' + course.course_prof + ')</option>');
                });
            },
            error: function () {
                alert('네트워크 오류 발생');
            }
        });
    });
    <%-- 아래 courseList의 select된 값을 받아오기  --%>
    $('#courseList').change(function(){   
    	 $('#course_nameprof').val($('#courseList > option:selected').text());
    	
    	 const regex = /^(.+)\((.+)\)$/; 
    	 const match = regex.exec($('#courseList > option:selected').text());


         if (match) {
             const courseName = match[1];
             const courseProf = match[2];
             $('#evasemesterList').empty();
             
             $.ajax({
                 url: 'courseYearSemester.do', 
                 type: 'post',
                 data: { course_name: courseName, course_prof: courseProf },
                 dataType: 'json',
                 success: function (result) {
                	 
                	 $('#evasemesterList').append('<option value="" selected>수강학기 선택</option>');
                	 $.each(result.list1, function(index, course1) {
                			 const semesterLabel = getSemesterLabel(course1.course_semester);
                		    $('#evasemesterList').append('<option value="' + course1.course_num + '">' + course1.course_year + '년' + semesterLabel + '</option>');
                		});
                 },
                 error: function () {
                     alert('네트워크 오류 발생');
                 }
             }); 
          }
     });
   	 $('#evasemesterList').change(function(){
    	$('#course_yearsemester').val($('#evasemesterList > option:selected').text());
   	 });
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<h2 class="board-title">강의평 작성</h2>
	<hr width="10%" class="board-underline">       
    <form id="write_form" action="evawrite.do" method="post">
    	<input type="hidden" name="course_nameprof" id="course_nameprof">   <%-- name과 id 값인 course_name은 내가 바꿔주기(설정) --%>
    	<input type="hidden" name="eva_semester" id="course_yearsemester">
    
    	<ul>
    	    <li>
    	        <!-- keyword를 입력할 input 태그 -->
       	    	<input type="search" size="16" id="keyword" value="${param.keyword}" placeholder="과목명을 입력하세요">
        	   	<input type="button" id="button" value="검색" class="eva-btn1">
            
            	<select name="course_num1" id="courseList">
            		<option value="" selected>검색 후 선택하세요</option>
                	<c:forEach var="course" items="${list}">
                    	<option value="${course.course_num}">${course.course_name}(${course.course_prof})</option>
                	</c:forEach>
             	</select>
         	</li>
         	<li> 
         		<select name="eva_star" id="eva_star">
         			<option value="" selected>강의를 등급으로 평가하세요</option>
         			<option value="4.5">A+</option>
         			<option value="4.0">A</option>
         			<option value="3.5">B+</option>
        	 		<option value="3.0">B</option>
        	 		<option value="2.5">C+</option>
        	 		<option value="2.0">C</option>
        	 		<option value="1.5">D+</option>
        	 		<option value="1.0">D</option>
        	 		<option value="0">F</option>
         		</select>
         	</li>
			<li>
				<textarea rows="10" cols="100" name="eva_content" id="eva_content" placeholder="이 강의에 대한 총평을 작성해주세요."></textarea>
			</li>            
         	<li>
         		<select name="course_num" id="evasemesterList">
         			<option value="" selected>수강 학기 선택</option>
         			<c:forEach var="course1" items="${list1}">
                    <option value="${course1.course_num}">${course1.course_year} (${course1.course_semester})</option>
          		    </c:forEach>
        		</select>
        	</li>
    	</ul>
    	<div class="align-center">
    	    <input type="submit" value="강의 평가하기" class="input-evabutton1"> <!--강의평가하기를 클릭시 courseeva_list.do로  -->
    	    <input type="button" value="목록" class="input-evabutton2" onclick="location.href='courseeva_list.do'">
  	   </div>
  	</form>
</div>
</body>
</html> 