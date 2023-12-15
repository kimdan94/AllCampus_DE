package kr.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.course.vo.CourseVO;
import kr.util.DBUtil;

public class CourseDAO {
	// 싱글톤 패턴
	private static CourseDAO instance = new CourseDAO();
	
	public static CourseDAO getInstance() {
		return instance;
	}
	
	private CourseDAO() {}
	
	
	// 강의 필터 검색 - 필터링 기능 포함 -> 전공/영역, 검색, 구분, 학점
	public List<CourseVO> getListCourse(String[] course_subject, String keyword, String[] course_category, String[] course_credit) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> list = null;
		String sql = null;
		String where_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			// course_subject - 전공/영역 검색
			if(course_subject != null) {
				for(int i=0; i<course_subject.length; i++) {
					if(i==0) {
						where_sql += " WHERE (course_subject=?";
					} else {
						where_sql += " OR course_subject=?";
					}
				}
				where_sql += ")";
			}
			
			//keyword - 강의명 교수명 검색
			if(keyword!=null && !"".equals(keyword)) {
				// 검색 처리 // 강의명 / 교수명으로만 검색
				if(!"".equals(where_sql)) {
					where_sql += " AND (course_name LIKE ? OR course_prof LIKE ?)";
				} else {
					where_sql += " WHERE (course_name LIKE ? OR course_prof LIKE ?)";
				}
			}
			
			// course_category - 카테고리
			if(course_category != null) {
				for(int i=0; i<course_category.length; i++) {
					if("".equals(where_sql)) {
						where_sql += " WHERE (course_category=?";
					} else if(!"".equals(where_sql) && i == 0){
						where_sql += " AND (course_category=?";
					} else {
						where_sql += " OR course_category=?";
					}
				}
				where_sql += ")";
			}
			
			// course_credit - 학점
			if(course_credit != null) {
				for(int i=0; i<course_credit.length; i++) {
					if("".equals(where_sql)) {
						where_sql += " WHERE (course_credit=?";
					} else if(!"".equals(where_sql) && i == 0){
						where_sql += " AND (course_credit=?";
					} else {
						where_sql += " OR course_credit=?";
					}
				}
				where_sql += ")";
			}
			
			// SQL문 작성
			sql = "SELECT * FROM all_course" + where_sql;

			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//?에 데이터 바인딩
			// course_subject - 전공/영역
			if(course_subject != null) {
				for(int i=0; i<course_subject.length; i++) {
					pstmt.setString(++cnt, course_subject[i]);
				}
			}
			// keyword - 검색어
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			// course_category - 카테고리
			if(course_category != null) {
				for(int i=0; i<course_category.length; i++) {
					pstmt.setString(++cnt, course_category[i]);
				}
			}
			// course_credit - 학점
			if(course_credit != null) {
				for(int i=0; i<course_credit.length; i++) {
					pstmt.setString(++cnt, course_credit[i]);
				}
			}
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<CourseVO>();
			
			while(rs.next()) {
				CourseVO course = new CourseVO();
				course.setCourse_num(rs.getInt("course_num"));
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_prof(rs.getString("course_prof"));
				course.setCourse_year(rs.getInt("course_year"));
				course.setCourse_semester(rs.getInt("course_semester"));
				course.setCourse_subject(rs.getString("course_subject"));
				course.setCourse_day(rs.getInt("course_day"));
				course.setCourse_start_time(rs.getString("course_start_time"));
				course.setCourse_end_time(rs.getString("course_end_time"));
				course.setCourse_category(rs.getString("course_category"));
				course.setCourse_credit(rs.getInt("course_credit"));
				course.setCourse_classroom(rs.getString("course_classroom"));
				course.setCourse_code(rs.getString("course_code"));
				course.setUniv_num(rs.getInt("univ_num"));
				
				list.add(course);
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	// 강의 전체 검색 - (주의) 필터링 기능 없음
	public List<CourseVO> getListShow(String[] course_subject, String keyword, String[] course_category, String[] course_credit) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			sql = "SELECT * FROM all_course";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<CourseVO>();
			
			while(rs.next()) {
				CourseVO course = new CourseVO();
				course.setCourse_num(rs.getInt("course_num"));
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_prof(rs.getString("course_prof"));
				course.setCourse_year(rs.getInt("course_year"));
				course.setCourse_semester(rs.getInt("course_semester"));
				course.setCourse_subject(rs.getString("course_subject"));
				course.setCourse_day(rs.getInt("course_day"));
				course.setCourse_start_time(rs.getString("course_start_time"));
				course.setCourse_end_time(rs.getString("course_end_time"));
				course.setCourse_category(rs.getString("course_category"));
				course.setCourse_credit(rs.getInt("course_credit"));
				course.setCourse_classroom(rs.getString("course_classroom"));
				course.setCourse_code(rs.getString("course_code"));
				course.setUniv_num(rs.getInt("univ_num"));
				
				list.add(course);
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}

	
	// 강의 전체 검색 - (주의) 필터링 기능 없음
	public List<CourseVO> getCourseList() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			sql = "SELECT course_subject FROM all_course GROUP BY course_subject";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<CourseVO>();
			
			while(rs.next()) {
				CourseVO course = new CourseVO();
				course.setCourse_subject(rs.getString("course_subject"));
				
				list.add(course);
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	// 강의 전체 검색 - (주의) 필터링 기능 없음
	public List<CourseVO> getRemoveDuplicateCourseList(String[] course_subject, String keyword, String[] course_category, String[] course_credit) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> list = null;
		String sql = null;
		String where_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			// course_subject - 전공/영역 검색
				if(course_subject != null) {
					for(int i=0; i<course_subject.length; i++) {
						if(i==0) {
							where_sql += " AND (course_subject=?";
						} else {
							where_sql += " OR course_subject=?";
						}
					}
					where_sql += ")";
				}
				
				//keyword - 강의명 교수명 검색
				if(keyword!=null && !"".equals(keyword)) {
					// 검색 처리 // 강의명 / 교수명으로만 검색
					where_sql += " AND (course_name LIKE ? OR course_prof LIKE ?)";
				}
				
				// course_category - 카테고리
				if(course_category != null) {
					for(int i=0; i<course_category.length; i++) {
						if(i == 0){
							where_sql += " AND (course_category=?";
						} else {
							where_sql += " OR course_category=?";
						}
					}
					where_sql += ")";
				}
				
				// course_credit - 학점
				if(course_credit != null) {
					for(int i=0; i<course_credit.length; i++) {
						if(i == 0){
							where_sql += " AND (course_credit=?";
						} else {
							where_sql += " OR course_credit=?";
						}
					}
					where_sql += ")";
				}
			
			// SQL문 작성
			sql = "SELECT DISTINCT course_name,course_prof,course_subject,course_code,course_year,"
					+ "course_semester,course_category,course_credit FROM all_course "
					+ "WHERE course_code in (SELECT DISTINCT(course_code) FROM all_course)" + where_sql;
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//?에 데이터 바인딩
			// course_subject - 전공/영역
			if(course_subject != null) {
				for(int i=0; i<course_subject.length; i++) {
					pstmt.setString(++cnt, course_subject[i]);
				}
			}
			// keyword - 검색어
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			// course_category - 카테고리
			if(course_category != null) {
				for(int i=0; i<course_category.length; i++) {
					pstmt.setString(++cnt, course_category[i]);
				}
			}
			// course_credit - 학점
			if(course_credit != null) {
				for(int i=0; i<course_credit.length; i++) {
					pstmt.setString(++cnt, course_credit[i]);
				}
			}
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<CourseVO>();
			
			while(rs.next()) {
				CourseVO course = new CourseVO();
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_prof(rs.getString("course_prof"));
				course.setCourse_subject(rs.getString("course_subject"));
				course.setCourse_code(rs.getString("course_code"));
				course.setCourse_year(Integer.parseInt(rs.getString("course_year")));
				course.setCourse_semester(Integer.parseInt(rs.getString("course_semester")));
				course.setCourse_category(rs.getString("course_category"));
				course.setCourse_credit(Integer.parseInt(rs.getString("course_credit")));
				
				list.add(course);
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//cours_code에 따른 요일 시간 필터링
	public List<CourseVO> selectDay(String course_code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			// SQL문 작성
			sql = "SELECT * FROM all_course WHERE course_code=?";
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//?에 데이터 바인딩
			pstmt.setString(1, course_code);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<CourseVO>();
			
			while(rs.next()) {
				CourseVO course = new CourseVO();
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_prof(rs.getString("course_prof"));
				course.setCourse_subject(rs.getString("course_subject"));
				course.setCourse_code(rs.getString("course_code"));
				course.setCourse_year(Integer.parseInt(rs.getString("course_year")));
				course.setCourse_semester(Integer.parseInt(rs.getString("course_semester")));
				course.setCourse_category(rs.getString("course_category"));
				course.setCourse_credit(Integer.parseInt(rs.getString("course_credit")));
				course.setCourse_day(rs.getInt("course_day"));
				course.setCourse_start_time(rs.getString("course_start_time"));
				course.setCourse_end_time(rs.getString("course_end_time"));
				
				list.add(course);
			}
		} catch(Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	
	
}