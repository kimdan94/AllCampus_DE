package kr.calculator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.calculator.vo.CalculatorVO;
import kr.timetable.vo.TimetableVO;
import kr.util.DBUtil;

public class CalculatorDAO {
	//싱글턴 패턴
	private static CalculatorDAO instance = new CalculatorDAO();
	
	public static CalculatorDAO getInstance() {
		return instance;
	}
	
	private CalculatorDAO() {}
	
	//timetable_year와 timetable_semester가 있는 시간표 정보 가져오기 (강의명, 강의수강학점) 
	public List<CalculatorVO> getTimetable(int timetable_year,int timetable_semester)throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    List<CalculatorVO> list = null;
	    String sql = null;
	    
	    try {
	    	//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			//중복 없이 강의명, 강의 수강 학점 select
			sql ="SELECT DISTINCT timetable_course_name, timetable_year, timetable_semester,timetable_credit FROM all_timetable WHERE timetable_year=? AND timetable_semester=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setInt(1, timetable_year);
			pstmt.setInt(2, timetable_semester);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<CalculatorVO>();
			while(rs.next()) {
				
				CalculatorVO calculator = new CalculatorVO();
				
				//시간표 정보를 담기 위해 TimetableVO 객체 생성
				TimetableVO timetable = new TimetableVO();
				timetable.setTimetable_course_name(rs.getString("timetable_course_name"));	//강의명
				timetable.setTimetable_credit(rs.getInt("timetable_credit"));				//강의 수강 학점
				
				//TimetableVO를 
				calculator.setTimetableVO(timetable);
				
				list.add(calculator);
			}
	    }catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	    return list;
	}
	
	
}
