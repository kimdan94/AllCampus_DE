$(function(){
	var index = 0;
	//좋아요 선택 여부와 선택한 총 개수 읽기
	function selectFav(index){
		$.ajax({
			url:'getFav.do',
			type:'post',
			data:{eva_num:$('#output_eva_fav_' + index).attr('data-num')},
			dataType:'json',
			success:function(param){
				displayFav(param,index);
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	}
	//좋아요 등록(및 삭제) 이벤트 연결
	$('.eva_fav_btn').click(function(){
		index = $(this).attr('data-index');
		
		$.ajax({
            url: 'writeFav.do',
            type: 'post',
            data: {eva_num: $('#output_eva_fav_' + index).attr('data-num')},
            dataType: 'json',
            success: function (param) {
                if (param.result == 'logout') {
                    alert('로그인 후 좋아요를 눌러주세요');
                } else if (param.result == 'success') {
                    displayFav(param,index);
                    
                    $('#output_eva_fav_' + index).val(param.status === 'yesFav' ? '추천 취소' : '추천');
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
	function displayFav(param,index){
		
		$('#output_eva_count_' + index).text(param.count);
		$('#output_eva_fav_' + index).val(param.status === 'yesFav' ? '추천 취소' : '추천');
	}
	//초기 데이터 호출
	$('.eva_fav_btn').each(function () {
        var index = $(this).attr('data-index');
        selectFav(index);
    });
});
