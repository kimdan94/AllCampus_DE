package kr.timetable.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.timetable.vo.TimetableVO;
import kr.util.DBUtil;

public class TimetableDAO {
	//싱글톤 패턴
	private static TimetableDAO instance = new TimetableDAO();
	
	public static TimetableDAO getInstance() {
		return instance;
	}
	
	private TimetableDAO() {}
	
	
	
	// INSERT 문
	public void insertTimetable(Integer mem_num, String course_code, String timetable_table_id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();

			System.out.println("mem num : " + mem_num + " course code : " + course_code + " timetable table id : " + timetable_table_id);
			//SQL문 작성
			sql = "INSERT INTO all_timetable "
					+ "(mem_num,course_code,timetable_year,timetable_semester,timetable_course_name,timetable_course_prof,timetable_credit,timetable_table_id,timetable_color) "
					+ "SELECT DISTINCT ?,a.course_code,a.course_year,a.course_semester,a.course_name,a.course_prof,a.course_credit,?,'red' "
					+ "FROM all_course a WHERE a.course_code=?";
			//PreparedStatment 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setString(2, timetable_table_id);
			pstmt.setString(3, course_code);
			
			//SQL문 실행
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	
	public List<TimetableVO> getListPrint(int mem_num, int year, int semester, int course_day) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TimetableVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			sql = "SELECT * FROM all_timetable WHERE mem_num=? AND timetable_table_id LIKE ? AND timetable_year=? AND timetable_semester=?";

			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//? 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setString(2, course_day+"%");
			pstmt.setInt(3, year);
			pstmt.setInt(4, semester);
			
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
	
	// course_num만 검색
	public List<TimetableVO> getListCourseCode(int mem_num, int year, int semester, int course_day) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TimetableVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			sql = "SELECT DISTINCT course_code FROM all_timetable WHERE mem_num=? AND timetable_table_id LIKE ? AND timetable_year=? AND timetable_semester=?";

			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//? 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setString(2, course_day+"%");
			pstmt.setInt(3, year);
			pstmt.setInt(4, semester);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<TimetableVO>();
			
			while(rs.next()) {
				TimetableVO timetable = new TimetableVO();
				timetable.setCourse_code(rs.getString("course_code"));
				
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
