package kr.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.notice.vo.NoticeVO;
import kr.util.DBUtil;
  
public class NoticeDAO {
	//싱글턴 패턴
	private static NoticeDAO instance = new NoticeDAO();
	public static NoticeDAO getinstance() {
		return instance;
	}
	private NoticeDAO() {}
	 
	//글 등록
	public void insertNotice(NoticeVO notice)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO all_notice (notice_num,notice_filename,notice_title,"
					+ "notice_content) VALUES (all_notice_seq.nextval,"
					+ "?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, notice.getNotice_num());
			pstmt.setString(2, notice.getNotice_filename());
			pstmt.setString(3, notice.getNotice_title());
			pstmt.setString(4, notice.getNotice_content());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글 목록
	//글의 총개수
	//글 상세
	//파일 삭제
	//글 수정
	//글 삭제
	//좋아요 등록
	//좋아요 개수
	
	
}
