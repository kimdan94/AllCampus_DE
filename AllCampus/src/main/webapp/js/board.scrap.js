$(function(){
	//좋아요 선택 여부와 선택한 총 개수 읽기
	function selectScrap(){
		$.ajax({
			url:'getScrap.do',
			type:'post',
			data:{board_num:$('#output_board_scrap').attr('data-num')},
			dataType:'json',
			success:function(param){
				displayScrap(param);
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	}
	//좋아요 등록(및 삭제) 이벤트 연결
	$('#output_board_scrap').click(function(){
		$.ajax({
            url: 'writeScrap.do',
            type: 'post',
            data: { board_num: $('#output_board_scrap').attr('data-num') },
            dataType: 'json',
            success: function (param) {
                if (param.result == 'logout') {
                    alert('로그인 후 좋아요를 눌러주세요');
                } else if (param.result == 'success') {
                    displayScrap(param);
                    
                    $('#output_board_scrap').val(param.status === 'yesScrap' ? '스크랩 취소' : '스크랩');
                } else {
                    alert('좋아요 등록 오류 발생');
                }
            },
            error: function () {
                alert('네트워크 오류 발생');
            }
        });
	});
	//좋아요 표시
	function displayScrap(param){
		/*
		let output;
		if(param.status=='yesScrap'){//좋아요 선택
			output ='../images/fav_image.png';
		}else{//좋아요 미선택
			output = '../images/fav_image.png';
		}
		*/
		//문서 객체에 설정
		//$('#output_fav').attr('src',output);
		$('#output_board_scrapcount').text(param.count);
		
	}
	//초기 데이터 호출
	selectScrap();
});
