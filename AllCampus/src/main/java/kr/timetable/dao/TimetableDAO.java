package kr.timetable.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.course.vo.CourseVO;
import kr.member.vo.MemberVO;
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
	// TimetableAddAction
	public void insertTimetable(int mem_num, String course_code, String timetable_table_id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();

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
	
	
	// 색 바꾸기 기본색 : red
	public void updateColor(int mem_num, String course_code, String timetable_color)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE all_timetable SET timetable_color=? WHERE mem_num=? AND course_code=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, timetable_color);
			pstmt.setInt(2, mem_num);
			pstmt.setString(3, course_code);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
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
	

	public List<CourseVO> timetableView(String timetableID) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			//sql = "SELECT * FROM all_course JOIN (SELECT course_code FROM all_timetable WHERE timetable_table_id=?) USING(course_code);";
			sql = "SELECT * FROM all_course JOIN (SELECT course_code FROM all_timetable WHERE timetable_table_id=?) USING(course_code)";
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//?에 데이터 바인딩
			pstmt.setString(1, timetableID);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<CourseVO>();
			
			while(rs.next()) {
				CourseVO course = new CourseVO();
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_prof(rs.getString("course_prof"));
				course.setCourse_year(Integer.parseInt(rs.getString("course_year")));
				course.setCourse_semester(Integer.parseInt(rs.getString("course_semester")));
				course.setCourse_subject(rs.getString("course_subject"));
				course.setCourse_day(rs.getInt("course_day"));
				course.setCourse_start_time(rs.getString("course_start_time"));
				course.setCourse_end_time(rs.getString("course_end_time"));
				course.setCourse_category(rs.getString("course_category"));
				course.setCourse_credit(Integer.parseInt(rs.getString("course_credit")));
				course.setCourse_classroom(rs.getString("course_classroom"));
				course.setCourse_code(rs.getString("course_code"));
				
				list.add(course);
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	
	// 시간표에서 삭제하고 싶은 강의 클릭 -> 모달창에서 강의 삭제 시 -> 강의 삭제됨
	public void deleteCourse(String[] code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM all_timetable WHERE course_code=?"; // 삭제 X 업데이트 O
			//PreparedStatment 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			for(int i=0; i<code.length-1; i++) {
				sql += " OR course_code=?";
			}
			for(int i=0; i<code.length; i++) {
				pstmt.setString(i+1, code[i]);
			}
			//SQL문 실행
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	// TimetableAddAction에서 year이랑 semester 구하기
	public List<String> selectYearAndSemester(String course_code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT DISTINCT course_year, course_semester FROM all_course WHERE course_code=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, course_code);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
//			list = new ArrayList<CourseVO>();
			
			if(rs.next()) {
				list.add(rs.getString("course_year"));
				list.add(rs.getString("course_semester"));
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	
	// TimetableWholeInit
	// 시간표 삭제
	public void initTimetable(int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "DELETE FROM all_timetable WHERE mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			
			//SQL문 실행
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	
	
	
	
	
}
