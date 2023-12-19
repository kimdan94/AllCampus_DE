package kr.calculator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardVO;
import kr.calculator.vo.CalSemesterVO;
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
	
	
	//학기에 대한 계산
	public void calculateScore(String cal_semester,double cal_avgscore,double cal_majorscore,int cal_acq,int cal_finclude_acq,int cal_majorf_acq,int mem_num) throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = null;
	    
	    try {
	    	//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql ="INSERT INTO all_calculator_semester (mem_num,cal_semester,cal_avgscore,cal_majorscore,cal_acq,cal_finclude_acq,cal_majorf_acq) VALUES (?,?,?,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1,mem_num);
			pstmt.setString(2,cal_semester);
			pstmt.setDouble(3,cal_avgscore);
			pstmt.setDouble(4,cal_majorscore);
			pstmt.setInt(5,cal_acq);
			pstmt.setInt(6,cal_finclude_acq);
			pstmt.setInt(7,cal_majorf_acq);
			//SQL문 실행
			pstmt.executeUpdate();
	    }catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//all_calculator에 정보 모두 넣기
	public void insertCalculator(int mem_num,String cal_semester,String cal_course_name, int cal_credit, double cal_grade, int cal_major)throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = null;
	    
	    try {
	    	//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql ="INSERT INTO all_calculator (mem_num,cal_semester,cal_course_name,cal_credit,cal_grade,cal_major) VALUES (?,?,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1,mem_num);
			pstmt.setString(2,cal_semester);
			pstmt.setString(3,cal_course_name);
			pstmt.setInt(4,cal_credit);
			pstmt.setDouble(5,cal_grade);
			pstmt.setInt(6,cal_major);
			//SQL문 실행
			pstmt.executeUpdate();
	    }catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	/*
	public List<CalSemesterVO> getListSemester(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CalSemesterVO> list = null;
		String sql = null;
		
		try {
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	*/
	
	/*지우기
	//cal_avgscore 더하기
	public double selectAvgscore()throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    double avgscore=0;
	    ResultSet rs = null;
	    String sql = null;
	    
	    try {
	    	//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql ="SELECT SUM(cal_avgscore) FROM all_calculator_semester";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				avgscore = rs.getDouble(1);
			}
	    }catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	    return avgscore;
	}
	*/
	
	//cal_avgscore 곱
	public double selectfAvgscore(int mem_num)throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    double favgscore=0;
	    ResultSet rs = null;
	    String sql = null;
	    
	    try {
	    	//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql ="SELECT SUM(cal_avgscore*cal_finclude_acq) FROM all_calculator_semester WHERE mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				favgscore = rs.getDouble(1);
			}
	    }catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	    return favgscore;
	}
	/*
	//cal_majorscore 더하기
	public double selectMajorscore()throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    double majorscore=0;
	    ResultSet rs = null;
	    String sql = null;
	    
	    try {
	    	//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql ="SELECT SUM(cal_majorscore) FROM all_calculator_semester";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				majorscore = rs.getDouble(1);
			}
	    }catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
	    
		return majorscore;
	}
	*/
	
	//cal_majorscore 곱
	public double selectfMajorscore(int mem_num)throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    double fmajorscore=0;
	    ResultSet rs = null;
	    String sql = null;
	    
	    try {
	    	//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql ="SELECT SUM(cal_majorscore * cal_majorf_acq) FROM all_calculator_semester WHERE mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				fmajorscore = rs.getDouble(1);
			}
	    }catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return fmajorscore;
	}
	
	//cal_finclude_acq 합
	public int sumFinclude(int mem_num)throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    int sumfinclude_acq=0;
	    ResultSet rs = null;
	    String sql = null;
	    
	    try {
	    	//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql ="SELECT SUM(cal_finclude_acq) FROM all_calculator_semester WHERE mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sumfinclude_acq = rs.getInt(1);
			}
	    }catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return sumfinclude_acq;
	}
		
	//cal_majorf_acq 합
	public int sumMajorf(int mem_num)throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    int summajorf_acq=0;
	    ResultSet rs = null;
	    String sql = null;
	    
	    try {
	    	//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql ="SELECT SUM(cal_majorf_acq) FROM all_calculator_semester WHERE mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				summajorf_acq = rs.getInt(1);
			}
	    }catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return summajorf_acq;
	}
	
	/*
	//calculator_semester 행 개수  -  전체 평균 구하는데 필요 
	public int semesterCount()throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    int semester_rowcount=0;
	    ResultSet rs = null;
	    String sql = null;
	    
	    try {
	    	//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql ="SELECT count(*) FROM all_calculator_semester";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				semester_rowcount = rs.getInt(1);
			}
	    }catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return semester_rowcount;
	}
	*/
	//calculator_semester 행 개수  -  전체 평균 구하는데 필요 
	public int selectAcqscore()throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    int acqscore=0;
	    ResultSet rs = null;
	    String sql = null;
	    
	    try {
	    	//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql ="SELECT SUM(cal_acq) FROM all_calculator_semester";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				acqscore = rs.getInt(1);
			}
	    }catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return acqscore;
	}
	
	//all_calculator_total에 점수 update
	public void totalScore(int mem_num,double cal_total_avgscore,double cal_total_majorscore,int cal_total_acq)throws Exception{
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    String sql = null;
	    
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql ="MERGE INTO all_calculator_total tgt USING "
				+ "(SELECT ? as mem_num, ? as cal_total_avgscore, ? as cal_total_majorscore, ? as cal_total_acq FROM dual)"
				+ " src ON (tgt.mem_num = src.mem_num) WHEN MATCHED THEN UPDATE SET "
				+ "cal_total_avgscore = src.cal_total_avgscore, cal_total_majorscore = src.cal_total_majorscore, "
				+ "cal_total_acq = src.cal_total_acq WHEN NOT MATCHED THEN INSERT (mem_num,cal_total_avgscore,cal_total_majorscore,cal_total_acq) "
				+ "VALUES (?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1,mem_num );
			pstmt.setDouble(2,cal_total_avgscore);
			pstmt.setDouble(3, cal_total_majorscore);
			pstmt.setInt(4, cal_total_acq);
			pstmt.setInt(5, mem_num);
			pstmt.setDouble(6, cal_total_avgscore);
			pstmt.setDouble(7, cal_total_majorscore);
			pstmt.setInt(8, cal_total_acq);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
		
	}
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	