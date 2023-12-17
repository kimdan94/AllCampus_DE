$(function(){
	//현재 이미지와 text 유지하기
	function selectSell(){
		$.ajax({
			url:'secondhand_getSell.do',
			type:'post',
			data:{secondhand_num:$('#sell_status').attr('data-num')},
			dataType:'json',
			success:function(param){
				displaySell(param);//ui 처리를 위해 호출
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	}
	
	//판매 여부 변경 이벤트 연결
	$('#sell_status').click(function(){
		$.ajax({
			url:'secondhand_changeSell.do',
			type:'post',
			data:{secondhand_num:$(this).attr('data-num')},
			dataType:'json',
			success:function(param){
				if(param.status == 'noLogin'){
					alert('로그인 후 이용 가능합니다.');
				}else if(param.status == 'noCertify'){
					alert('학교 인증을 마친 학생들만 이용할 수 있어요!');
				}else if(param.status == 'noSell'){
					alert('판매 완료 처리되었습니다.');
					displaySell(param);
				}else if(param.status == 'yesSell'){
					alert('판매 중으로 상태를 변경하였습니다.');
					displaySell(param);
				}else{
					alert('판매 여부 변경 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
			
		});
	});
	
	//판매 여부 표시
	function displaySell(param){
		let output;
		let outputText;
		if(param.status == 'noSell'){//판매 완료 처리
			output = '../images/toggleoff.png';
			outputText = '[판매 완료]';
		}else if(param.status == 'yesSell'){//판매 중 변경
			output = '../images/toggleon.png';
			outputText = '[판매 중]';
		}
		
		//문서 객체에 설정
		$('#sell_status').attr('src',output);
		$('#sell_text').text(outputText);
	}
	
	//초기 데이터 호출
	selectSell();
});