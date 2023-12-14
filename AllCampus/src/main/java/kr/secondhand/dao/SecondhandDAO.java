package kr.secondhand.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.secondhand.vo.SecondhandVO;
import kr.util.DBUtil;

public class SecondhandDAO {
	//싱글턴 패턴
	private static SecondhandDAO instance = new SecondhandDAO();
	
	public static SecondhandDAO getInstance() {
		return instance;
	}
	
	private SecondhandDAO() {}
	
	//글 등록
	public void insertSC(SecondhandVO sc)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO all_secondhand (secondhand_num,secondhand_name,"
					+ "secondhand_writer,secondhand_company,secondhand_content,"
					+ "secondhand_price,secondhand_way,secondhand_status,secondhand_filename,"
					+ "secondhand_openchat,secondhand_ip,mem_num) "
					+ "VALUES (all_secondhand_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, sc.getSecondhand_name());
			
			//저자명 가공
			if(sc.getSecondhand_writerPlus()!= null) {
				String writerPlus = sc.getSecondhand_writer() + " " + sc.getSecondhand_writerPlus();
				pstmt.setString(2,writerPlus);
			}else {
				pstmt.setString(2, sc.getSecondhand_writer());
			}
			
			pstmt.setString(3, sc.getSecondhand_company());
			pstmt.setString(4, sc.getSecondhand_content());
			pstmt.setInt(5, sc.getSecondhand_price());
			pstmt.setString(6, sc.getSecondhand_way());
			pstmt.setString(7, sc.getSecondhand_status());
			pstmt.setString(8, sc.getSecondhand_filename());
			pstmt.setString(9, sc.getSecondhand_openchat());
			pstmt.setString(10, sc.getSecondhand_ip());
			pstmt.setInt(11, sc.getMem_num());
			
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//전체 레코드 수/검색 레코드 수
	//전체 글/검색 글 목록
	//글 상세
	//글 수정
	//글 삭제
	//물건 판매 여부 변경
	//신고 수 변경
	//댓글 등록
	//댓글 개수
	//댓글 목록
	//댓글 상세(댓글 수정, 삭제 시 작성자 회원번호 체크 용도로 사용)
	//댓글 수정
	//댓글 삭제
}
