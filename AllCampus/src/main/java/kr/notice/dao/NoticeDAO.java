package kr.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardVO;
import kr.notice.vo.NoticeVO;
import kr.util.DBUtil;
import kr.util.StringUtil;
    
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
					+ "?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, notice.getNotice_filename());
			pstmt.setString(2, notice.getNotice_title());
			pstmt.setString(3, notice.getNotice_content());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//전체 레코드수/검색 레코드수
	public int getNoticeCount(String keyfield,String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";//빈 문자열
		int count = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "WHERE notice_title LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE notice_content LIKE ?";
			}
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM all_notice " + sub_sql;
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(1, "%" + keyword + "%");
			}
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.getConnection();
		}
		return count;
	}
	//전체 글/검색 글 목록
	public List<NoticeVO> getListNotice(int start,int end,String keyfield,String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeVO> list = null;
		String sql = null;
		String sub_sql ="";//빈 문자열
		int cnt=0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			if(keyword!=null && !"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("1")) sub_sql += "WHERE notice_title LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE notice_content LIKE ?";
			}
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
				+ "(SELECT * FROM all_notice " + sub_sql
				+ " ORDER BY notice_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%" + keyword +"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<NoticeVO>();
			while(rs.next()) {
				NoticeVO notice = new NoticeVO();
				notice.setNotice_num(rs.getInt("notice_num"));
				//HTML을 허용하지 않음
				notice.setNotice_title(StringUtil.useNoHtml(rs.getString("notice_title")));
				notice.setNotice_content(StringUtil.useNoHtml(rs.getString("notice_content")));
				notice.setNotice_reg_date(rs.getDate("notice_reg_date"));
				list.add(notice);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.getConnection();
		}
		return list;
	}
	//글 상세
	public NoticeVO getNotice(int notice_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeVO notice = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM all_notice WHERE notice_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, notice_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				notice = new NoticeVO();
				notice.setNotice_num(rs.getInt("notice_num"));
				notice.setNotice_title(rs.getString("notice_title"));
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setNotice_reg_date(rs.getDate("notice_reg_date"));
				notice.setNotice_modify_date(rs.getDate("notice_modify_date"));
				notice.setNotice_filename(rs.getString("notice_filename"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return notice;
	}
	//파일 삭제
	//글 수정
	public void updateNotice(NoticeVO notice)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE all_notice SET notice_title=?,notice_content=?,notice_modify_date=SYSDATE" + sub_sql + "WHERE notice_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt, notice.getNotice_title());
			pstmt.setString(++cnt, notice.getNotice_content());
			pstmt.setInt(++cnt, notice.getNotice_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.getConnection();
		}
	}
	//글 삭제
	public void deleteNotice(int notice_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM all_notice WHERE notice_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//좋아요 등록
	//좋아요 개수
	
	
}
