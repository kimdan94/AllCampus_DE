package kr.secondhand.dao;

public class SecondhandDAO {
	//싱글턴 패턴
	private static SecondhandDAO instance = new SecondhandDAO();
	
	public static SecondhandDAO getInstance() {
		return instance;
	}
	
	private SecondhandDAO() {}
	
	//글 등록
	//전체 레코드 수/검색 레코드 수
	//전체 글/검색 글 목록
	//글 상세
	//글 수정
	//글 삭제
	//물건 판매 여부 변경
	//댓글 등록
	//댓글 개수
	//댓글 목록
	//댓글 상세(댓글 수정, 삭제 시 작성자 회원번호 체크 용도로 사용)
	//댓글 수정
	//댓글 삭제
}
