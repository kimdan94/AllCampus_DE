package kr.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardVO;
import kr.notice.vo.NoticeVO;
import kr.question.vo.QuestionVO;
import kr.secondhand.vo.SecondhandVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;

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
					+ "(SELECT * FROM all_notice ORDER BY notice_reg_date DESC)a) "
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
				
				list.add(notice);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//FAQ 미리보기
	public List<QuestionVO> getListQuestion(int start, int end)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QuestionVO> list = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM all_question ORDER BY question_num ASC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<QuestionVO>();
			while(rs.next()) {
				QuestionVO question = new QuestionVO();
				question.setQuestion_num(rs.getInt("question_num"));
				question.setQuestion_title(rs.getString("question_title"));
				
				list.add(question);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//HOT게시판 미리보기
	public List<BoardVO> getListHot(int start, int end, int hit)throws Exception{
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
					+ "(SELECT * FROM all_board WHERE board_hit >= ? AND board_show=2 "
					+ "ORDER BY board_reg_date DESC)"
					+ "a) WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, hit);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			while(rs.next()) {
				BoardVO hot = new BoardVO();
				hot.setBoard_num(rs.getInt("board_num"));
				
				//글제목 가공
				if(rs.getString("board_title").length() >= 17) {
					String title = rs.getString("board_title").substring(0, 10);
					title += " ...";
					hot.setBoard_title(title);
				}else if(rs.getString("board_title").length() < 17){
					hot.setBoard_title(rs.getString("board_title"));
				}
				
				hot.setBoard_reg_date(DurationFromNow.getTimeDiffLabel(rs.getString("board_reg_date")));
				
				list.add(hot);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
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
					+ "(SELECT * FROM all_board WHERE board_show=2 "
					+ "ORDER BY board_reg_date DESC)a) "
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
				
				board.setBoard_reg_date(DurationFromNow.getTimeDiffLabel(rs.getString("board_reg_date")));
				
				list.add(board);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//책방 미리보기
	public List<SecondhandVO> getListSecondhand(int start, int end)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SecondhandVO> list = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM all_secondhand WHERE secondhand_show=2 "
					+ "ORDER BY secondhand_reg_date DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<SecondhandVO>();
			while(rs.next()) {
				SecondhandVO sc = new SecondhandVO();
				sc.setSecondhand_num(rs.getInt("secondhand_num"));
				//글제목 가공
				if(rs.getString("secondhand_name").length() >= 15) {
					String title = rs.getString("secondhand_name").substring(0, 13);
					title += " ...";
					sc.setSecondhand_name(title);
				}else if(rs.getString("secondhand_name").length() < 15){
					sc.setSecondhand_name(rs.getString("secondhand_name"));
				}
				sc.setSecondhand_filename(rs.getString("secondhand_filename"));
				sc.setSecondhand_price(rs.getInt("secondhand_price"));
				
				list.add(sc);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
}
