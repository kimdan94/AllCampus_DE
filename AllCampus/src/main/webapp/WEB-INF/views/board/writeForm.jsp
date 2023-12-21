<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글 작성 폼</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jiwonstyle.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    $(function(){
        $(function(){
            $('#write_Form').submit(function(){
                // 익명 체크박스가 체크되어 있으면 값을 2로, 체크되어 있지 않으면 값을 1로 설정
                if ($('#board_anonymous').is(':checked')) {
                    $('#board_anonymous').val('2');
                } else {
                    $('#board_anonymous').val('1');
                 // 체크가 되어 있지 않으면 hidden input에 값을 설정
                    $('#write_Form').append('<input type="hidden" name="board_anonymous" value="1">');
                }
                // hidden input에 값을 설정
               //삭제하기  -> $('#write_Form').append('<input type="hidden" name="board_anonymous" value="' + $('#board_anonymous').val() + '">');
            });
        });
    });
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
    <h2 class="board-title">게시판 글 작성</h2>
	<hr width="10%" class="board-underline">
    <div>
        <form id="write_Form" action="write.do" method="post" enctype="multipart/form-data">
            <ul>
                <li>
                    
                    <input type="text" name="board_title" id="board_title" maxlength="50" placeholder="글 제목">
                </li>
                <li>
                    
                    <input type="text" name="board_content" id="board_content" maxlength="50" placeholder="글 내용">
                </li>
                <li>
                   
                    <input type="file" name="board_filename" id="board_filename" accept="image/gif,image/png,image/jpeg">
                </li>
                <li>
                    
                    <input type="checkbox" name="board_anonymous" id="board_anonymous" value="1">익명
                </li>
            </ul>
            <div class="align-center">
          		<input type="submit" value="등록하기" class="input-evabutton1">
                <input type="button" value="목록" class="input-evabutton2" onclick="location.href='list.do'">
            </div>
        </form>
    </div>
</div>
</body>
</html>
