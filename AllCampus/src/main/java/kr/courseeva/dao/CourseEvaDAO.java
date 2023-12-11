package kr.courseeva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.courseeva.vo.CourseEvaVO;

public class CourseEvaDAO {
	//싱글턴 패턴
	private static CourseEvaDAO instance = new CourseEvaDAO();
	public static CourseEvaDAO getInstance() {
		return instance;
	}
	
	private CourseEvaDAO() {}
	
	//강의평 등록
	public void insertEva(CourseEvaVO eva)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
	}
	
	
}
