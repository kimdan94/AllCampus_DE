package kr.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardVO;
import kr.notice.vo.NoticeVO;
import kr.util.DBUtil;

public class HomeDAO {
	//싱글턴 패턴
	private static HomeDAO instance = new HomeDAO();
	public static HomeDAO getInstance() {
		return instance;
	}
	private HomeDAO() {}
	
	//공지사항 미리보기
	public List<NoticeVO> getListNotice(int start, int end)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeVO> list = null;
		String sql = null;
		
		try{
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM all_notice ORDER BY notice_reg_date)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<NoticeVO>();
			while(rs.next()) {
				NoticeVO notice = new NoticeVO();
				notice.setNotice_num(rs.getInt("notice_num"));
				
				//글제목 가공
				if(rs.getString("notice_title").length() >= 17) {
					String title = rs.getString("notice_title").substring(0, 10);
					title += " ...";
					notice.setNotice_title(title);
				}else if(rs.getString("notice_title").length() < 17){
					notice.setNotice_title(rs.getString("notice_title"));
				}
				
				notice.setNotice_reg_date(rs.getDate("notice_reg_date"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//FAQ 미리보기 - FAQ도 추후 작업 예정(자바빈 필요)
	
	//HOT게시판 미리보기 - hot은 추후 작업 예정(좋아요 작업 처리 완벽히 완료 시에 처리)
	
	//자유게시판 미리보기
	public List<BoardVO> getListBoard(int start, int end)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM all_board ORDER BY board_reg_date DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoard_num(rs.getInt("board_num"));
				
				//글제목 가공
				if(rs.getString("board_title").length() >= 17) {
					String title = rs.getString("board_title").substring(0, 10);
					title += " ...";
					board.setBoard_title(title);
				}else if(rs.getString("board_title").length() < 17){
					board.setBoard_title(rs.getString("board_title"));
				}
				
				board.setBoard_reg_date(rs.getDate("board_reg_date"));
				
				list.add(board);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
}
