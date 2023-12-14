<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <!-- Include jQuery library -->

    <script type="text/javascript">
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
            };

            
            $('#button').click(function () {
            	
                $.ajax({
                    url: 'searchcoursename.do',
                    type: 'post',
                    data: { keyword: keyword.value },
                    dataType: 'json',
                    success: function (param) {
                        courseList.innerHTML = '';
                        $.each(param.list, function (index, course) {
                            $('#courseList').append('<option value="' + course.course_num + '">' + course.course_name + '(' + course.course_prof + ')</option>');
                        });
                    },
                    error: function () {
                        alert('Error fetching course data.');
                    }
                });

                
            });
            <%-- 아래 courseList의 select된 값을 받아오기  --%>
            $('#courseList').change(function(){   
            	$('#course_name').val($('#courseList > option:selected').text());
            });
            
        });
    </script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="page-main">

    <div class="content-main">
        <h2>강의평 수정 폼</h2>
        <form id="write_form" action="courseeva_list.do" method="post" enctype="multipart/form-data">
        <input type="hidden" name="course_name" id="course_name">   <%-- name과 id 값인 course_name은 내가 바꿔주기(설정) --%>
        <ul>
            <li>
                <!-- keyword를 입력할 input 태그 -->
                <input type="search" size="16" id="keyword" value="${param.keyword}">
               	<input type="button" id="button" value="검색">
                <!-- 자동완성 목록을 표시할 datalist -->
                <select name="course" id="courseList">
                    <c:forEach var="course" items="${list}">
                        <option value="${course.course_num}">${course.course_name}(${course.course_prof})</option>
                    </c:forEach>
                </select>
                
               
            </li>
        </ul>
        <div class="align-center">
            <input type="submit" value="강의평가하기"> <!--강의평가하기를 클릭시 courseeva_list.do로  -->
            <input type="button" value="목록" onclick="location.href='list.do'">
        </div>
        </form>
    </div>
</div>
</body>
</html>
