package kr.courseeva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.course.vo.CourseVO;
import kr.courseeva.vo.CourseEvaVO;
import kr.util.DBUtil;

public class CourseEvaDAO {
	//싱글턴 패턴
	private static CourseEvaDAO instance = new CourseEvaDAO();
	public static CourseEvaDAO getInstance() {
		return instance;
	}
	
	private CourseEvaDAO() {}
	
	//전체 레코드수/검색 레코드수
	public int getCourseEvaCount(String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql ="";//빈 문자열
		int count=0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//검색어가 null이 아니고 공백과 같지 않을 때
			if(keyword != null && !"".equals(keyword)) {
				sub_sql += "WHERE course_name LIKE ?";
			}
			//SQL문 작성
			sql = "SELECT COUNT(*) AS row_count FROM (SELECT MIN(course_num) "
				+ "AS course_num, course_name, course_prof FROM all_course " + sub_sql 
				+ " GROUP BY course_name, course_prof)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(1, "%" + keyword +"%");
			}
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	
	public List<CourseVO> getCourseNameProf(String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//검색어가 null이 아니고 공백과 같지 않을 때
			if(keyword != null && !"".equals(keyword)) {
				sub_sql += "WHERE course_name LIKE ?";
			}
			//SQL문 작성
			sql = "SELECT MIN(course_num) AS course_num, course_name, course_prof FROM all_course " 
					+ sub_sql + " GROUP BY course_name, course_prof";
			//PreparedStatement 객체
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<CourseVO>();
			while(rs.next()) {
				CourseVO course = new CourseVO();
				course.setCourse_num(rs.getInt("course_num"));
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_prof(rs.getString("course_prof"));
				
				list.add(course);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	/*
	public List<CourseEvaVO> getAllCourseEvaluations(int start, int end, String keyfield, String keyword) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<CourseEvaVO> list = null;
        String sql = null;
        String sub_sql = "";
        int cnt = 0;

        try {
            // Get a database connection
            conn = DBUtil.getConnection();
            
            // Handle search conditions
            if (keyword != null && !"".equals(keyword)) {
                if (keyfield.equals("1")) sub_sql += "WHERE eva_content LIKE ?";
                // Add more conditions if needed
            }

            // SQL query to retrieve course evaluations
            sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
                    + "(SELECT * FROM all_course_eva " + sub_sql
                    + " ORDER BY eva_num DESC) a) WHERE rnum >= ? AND rnum <= ?";

            // Prepare the SQL statement
            pstmt = conn.prepareStatement(sql);
            
            // Set parameters for search conditions
            if (keyword != null && !"".equals(keyword)) {
                pstmt.setString(++cnt, "%" + keyword + "%");
            }

            pstmt.setInt(++cnt, start);
            pstmt.setInt(++cnt, end);

            // Execute the SQL query
            rs = pstmt.executeQuery();

            // Process the result set
            list = new ArrayList<>();
            while (rs.next()) {
                CourseEvaVO courseEva = new CourseEvaVO();
                courseEva.setEva_num(rs.getInt("eva_num"));
                courseEva.setCourse_num(rs.getInt("course_num"));
                courseEva.setMem_num(rs.getInt("mem_num"));
                courseEva.setEva_star(rs.getInt("eva_star"));
                courseEva.setEva_content(rs.getString("eva_content"));
                courseEva.setEva_complaint(rs.getInt("eva_complaint"));
                courseEva.setEva_show(rs.getInt("eva_show"));

                list.add(courseEva);
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            // Close resources
            DBUtil.executeClose(rs, pstmt, conn);
        }
        return list;
    }

	
	//강의평 등록
	public void insertEva(CourseEvaVO eva)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	*/
	
}
