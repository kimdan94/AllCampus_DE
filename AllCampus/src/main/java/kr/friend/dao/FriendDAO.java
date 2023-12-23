package kr.friend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
import kr.timetable.vo.TimetableVO;
import kr.util.DBUtil;

public class FriendDAO {
	//싱글톤 패턴
	private static FriendDAO instance = new FriendDAO();
	public static FriendDAO getInstance() {
		return instance;
	}
	private FriendDAO() {}
	

	// 친구추가 전에 친구 목록에 검색한 아이디가 있는지 없는지 확인
	public int checkFriend(int mem_num, String friend_id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			sql = "SELECT * FROM all_friend f JOIN all_member m ON f.friend_num = m.mem_num WHERE f.mem_num=? AND m.mem_id=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setString(2, friend_id);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		return count;
	}
	
	// 친구 추가하기 - 검색창에서 id를 검색하면 mem_num friend_num 에 데이터 insert // 등급 제한 걸어둠
	// FriendSearchAction
	public void searchFriend(int mem_num, String friend_id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			sql = "INSERT INTO all_friend VALUES (?, (SELECT mem_num FROM all_member WHERE mem_id=? AND mem_auth!=9))";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setString(2, friend_id);
			
			//SQL문 실행
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//////////////////////////아직 완성 안됨 Friend List Action
	public List<MemberVO> selectFriend() throws Exception {
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<MemberVO> list = null;
	    String sql = null;
	    String sub_sql = "";
	    
	    try {
	    	//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			//중복 없이 강의명, 강의 수강 학점 select
			sql ="SELECT a.mem_num,a.mem_name,a.univ_num,a.mem_major FROM all_friend JOIN (SELECT * FROM all_member JOIN all_member_detail USING(mem_num)) a ON a.mem_num = all_friend.friend_num";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			//SQL문 실행
			rs = pstmt.executeQuery();
			
			list = new ArrayList<MemberVO>();
			while(rs.next()) {
				MemberVO friend = new MemberVO();
				friend.setMem_num(rs.getInt(0));
				list.add(friend);
			}
	    }catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	    return list;
	}
	
	// 친구 이름 검색해서 찾기
	// FriendListAction
	public List<MemberVO> selectSearchFriend(int mem_num, String mem_name) throws Exception {
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<MemberVO> list = null;
	    String sql = null;
	    String sub_sql = " AND a.mem_name LIKE ?";
	    int count = 0;
	    
	    try {
	    	//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			//중복 없이 강의명, 강의 수강 학점 select
			sql = "SELECT DISTINCT a.mem_name FROM all_friend JOIN (SELECT * FROM all_member JOIN all_member_detail USING(mem_num)) a ON a.mem_num = all_friend.friend_num WHERE all_friend.mem_num=?";
			if(mem_name != null & mem_name != "") {
				sql += sub_sql;
			}
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++count, mem_num);
			if(mem_name != null & mem_name != "") {
				//?에 데이터 바인딩
				pstmt.setString(++count, "%"+mem_name+"%");
				
			}
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			
			list = new ArrayList<MemberVO>();
			while(rs.next()) { // mem_num mem_name univ_num mem_major
				MemberVO friend = new MemberVO();
				friend.setMem_name(rs.getString("mem_name"));
				
				list.add(friend);
			}
	    }catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	    return list;
	}
	
	
	
	// 친구 시간표 가져오기
	public List<TimetableVO> getFriendTimetableList(int mem_num, int year, int semester) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TimetableVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			sql = "SELECT * FROM all_timetable WHERE mem_num=? AND timetable_year=? AND timetable_semester=?";

			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//? 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, year);
			pstmt.setInt(3, semester);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<TimetableVO>();
			
			while(rs.next()) {
				TimetableVO timetable = new TimetableVO();
				timetable.setMem_num(rs.getInt("mem_num"));
				timetable.setCourse_code(rs.getString("course_code"));
				timetable.setTimetable_year(rs.getInt("timetable_year"));
				timetable.setTimetable_semester(rs.getInt("timetable_semester"));
				timetable.setTimetable_course_name(rs.getString("timetable_course_name"));
				timetable.setTimetable_course_prof(rs.getString("timetable_course_prof"));
				timetable.setTimetable_credit(rs.getInt("timetable_credit"));
				timetable.setTimetable_table_id(rs.getString("timetable_table_id"));
				timetable.setTimetable_color(rs.getString("timetable_color"));
				
				list.add(timetable);
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	
	
}
