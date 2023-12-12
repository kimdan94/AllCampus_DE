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
	
	
	
	// 강의 전체 검색
	public List<CourseVO> getListCourse(String course_subject, String keyword, String course_sort, String course_category, String course_credit) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> list = null;
		String sql = null;
		String where_sql = "";
		String order_sql = "";
		
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(course_subject!=null || (keyword!=null && !"".equals(keyword)) || course_category!=null || course_credit!=null) {
				
				// 전공/영역 검색
				if(course_subject!=null) {
					where_sql += " WHERE course_subject=?";
				}
				//강의명 교수명 검색
				if(keyword!=null && !"".equals(keyword)) {
					// 검색 처리 // 강의명 / 교수명으로만 검색
					if(!"".equals(where_sql)) {
						where_sql += " AND course_name LIKE ? OR course_prof LIKE ?";
					} else {
						where_sql += " WHERE course_name LIKE ? OR course_prof LIKE ?";
					}
				}
				//정렬 -- 수정하기
				if(course_sort!=null) {
					order_sql += " ORDER BY ";
				}
				// 카테고리
				if(course_category!=null) {
					if(!"".equals(where_sql)) {
						where_sql += " AND course_category=?";
					} else {
						where_sql += " WHERE course_category=?";
					}
				}
				// 학점
				if(course_credit!=null) {
					if(!"".equals(where_sql)) {
						where_sql += " AND course_credit?";
					} else {
						where_sql += " WHERE course_credit=?";
					}
				}
			}
			
			// SQL문 작성
			sql = "SELECT * FROM all_course" + where_sql + order_sql;
			
			///////////////////////////////////////////////////
			
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			if(course_subject!=null) {
				pstmt.setString(++cnt, course_subject);
			}
			
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			
			//정렬 -- 수정하기
			if(course_sort!=null) {
				pstmt.setString(++cnt, course_subject);
			}
			// 카테고리
			if(course_category!=null) {
				pstmt.setString(++cnt, course_category);
			}
			// 학점
			if(course_credit!=null) {
				pstmt.setString(++cnt, course_credit);
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
	
	
}
