<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>자유게시판 글 수정 폼</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiwonstyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $('#update_Form').submit(function(){
            	if($('#board_title').val().trim()==''){
            		alert('글 제목을 입력하세요');
            		$('#board_title').val('').focus();
            		return false;
            	}
            	if($('#board_content').val().trim()==''){
            		alert('글 내용을 입력하세요');
            		$('#board_content').val('').focus();
            		return false;
            	}
                // 익명 체크박스가 체크되어 있으면 값을 2로, 체크되어 있지 않으면 값을 1로 설정
                if ($('#board_anonymous').is(':checked')) {
                    $('#board_anonymous').val('2');
                } else {
                    $('#board_anonymous').val('1');
                    // 체크가 되어 있지 않으면 hidden input에 값을 설정
                    $('#update_Form').append('<input type="hidden" name="board_anonymous" value="1">');
                }
            });
        });
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <div class="page-main">
    	<h2 class="board-title">게시판 글 수정</h2>
		<hr width="10%" class="board-underline">
        <div>
            <form id="update_Form" action="update.do" method="post" enctype="multipart/form-data">
                <input type="hidden" name="board_num" value="${board.board_num}">
                <ul>
                    <li>
                        <label for="board_title"></label>
                        <input type="text" name="board_title" id="board_title" value="${board.board_title}" maxlength="50">
                    </li>
                    <li>
                        <label for="board_content"></label>
                        <textarea rows="10" cols="80" style="margin-top:10px;" name="board_content" id="board_content" maxlength="50" placeholder="글 내용">${board.board_content}</textarea>
                    </li>
                    <li>
                        <label for="board_filename"></label>
                        <input type="file" name="board_filename" id="board_filename" accept="image/gif,image/png,image/jpeg">
                        <c:if test="${!empty board.board_filename}">
                            <div id="file_detail">
                                (${board.board_filename})파일이 등록되어 있습니다.
                                <input type="button" value="파일삭제" id="file_del">
                                <script type="text/javascript">
                                    $(function(){
                                        $('#file_del').click(function(){
                                            let choice = confirm('삭제하시겠습니까?');
                                            if(choice){
                                                $.ajax({
                                                    url:'deleteFile.do',
                                                    type:'post',
                                                    data:{board_num:${board.board_num}},
                                                    dataType:'json',
                                                    success:function(param){
                                                        if(param.result == 'logout'){
                                                            alert('로그인 후 사용하세요');
                                                        } else if(param.result == 'success'){
                                                            $('#file_detail').hide();  
                                                        } else if(param.result == 'wrongAccess'){
                                                            alert('잘못된 접속입니다.');
                                                        } else{
                                                            alert('파일 삭제 오류 발생');
                                                        } 
                                                    },
                                                    error:function(){
                                                        alert('네트워크 오류 발생');
                                                    }
                                                });
                                            }
                                        });                                
                                    });
                                </script>
                            </div>
                        </c:if>
                    </li>
                    <li>
                        <label for="board_anonymous"></label>
                        <input type="checkbox" style="margin-top:10px;" name="board_anonymous" id="board_anonymous" value="2" <c:if test="${board.board_anonymous == 2}">checked</c:if>>익명
                    </li>
                </ul>
                <div class="align-center">
                	<input type="submit" value="수정하기" class="input-evabutton1">
                    <input type="button" value="목록" class="input-evabutton2" onclick="location.href='list.do'">
                </div>
            </form>
        </div>
    </div>
</body>
</html>
