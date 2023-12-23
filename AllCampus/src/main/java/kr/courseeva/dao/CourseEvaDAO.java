package kr.courseeva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import kr.course.vo.CourseVO;
import kr.courseeva.vo.CourseEvaFavVO;
import kr.courseeva.vo.CourseEvaVO;
import kr.courseeva.vo.CourseEvaWarnVO;
import kr.util.DBUtil;

public class CourseEvaDAO {
	//싱글턴 패턴
	private static CourseEvaDAO instance = new CourseEvaDAO();
	public static CourseEvaDAO getInstance() {
		return instance;
	}
	
	private CourseEvaDAO() {}
	
	//강의평 등록 폼  검색  -  전체 레코드수/검색 레코드수
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
	
	//강의평 글 등록 폼 - 과목명 검색 기능
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
	
	//강의평 등록 폼  - 연도,학기 불러와서 처리
	public List<CourseVO> getCourseYearSemester(String course_name,String course_prof)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> list1 = null;
		String sql = null;

		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT MIN(course_num) as course_num, course_name, course_prof, course_year,"
				+ " course_semester FROM all_course WHERE course_name=? AND course_prof=? "
				+ "GROUP BY course_name, course_prof, course_year, course_semester";
			//PreparedStatement 객체
			pstmt = conn.prepareStatement(sql);	
			
			pstmt.setString(1, course_name);
			pstmt.setString(2, course_prof);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list1 = new ArrayList<CourseVO>();
			while(rs.next()) {
				CourseVO course = new CourseVO();
				course.setCourse_num(rs.getInt("course_num"));
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_prof(rs.getString("course_prof"));
				
				//연도, 학기 불러오기(등록 폼의 수강한 학기를 위해)
				course.setCourse_year(rs.getInt("course_year"));
				course.setCourse_semester(rs.getInt("course_semester"));
				
				list1.add(course);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list1;
	}	
	
	public void insertCourseEva(CourseEvaVO courseeva)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션푸로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO all_course_eva (eva_num,course_num,eva_star,eva_content,eva_semester,mem_num)"
				+ " VALUES (all_eva_seq.nextval,?,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, courseeva.getCourse_num());
			pstmt.setDouble(2, courseeva.getEva_star());
			pstmt.setString(3, courseeva.getEva_content());
			pstmt.setString(4, courseeva.getEva_semester());
			pstmt.setInt(5, courseeva.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//list 메인 -  전체 레코드수/검색 레코드 수 
	public int getCourseEvaListCount(String keyfield,String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			if(keyword!=null && !"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("1")) sub_sql += "WHERE course_name LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE course_prof LIKE ?";
			}	
			//SQL문 작성
			sql = "SELECT COUNT(*) AS row_count FROM (SELECT a.*, rownum rnum FROM "
				+ "(SELECT e.*, ac.course_name, ac.course_prof, ROW_NUMBER() OVER "
				+ "(PARTITION BY ac.course_name, ac.course_prof ORDER BY e.eva_num DESC) as rnk "
				+ "FROM all_course_eva e JOIN all_course ac ON e.course_num = ac.course_num WHERE "
				+ "(ac.course_name, ac.course_prof) IN (SELECT course_name, course_prof FROM all_course " + sub_sql
				+ ")AND e.eva_show = 2 ORDER BY ac.course_name, ac.course_prof, e.eva_num DESC) a WHERE a.rnk = 1)";
					
			//PreparedStatement 객체
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(1, "%"+keyword+"%");
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
	
	//list 메인   -  전체 글/검색 글 목록
	public List<CourseEvaVO> getCourseEvaList(int start, int end,String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseEvaVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			if(keyword!=null && !"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("1")) sub_sql += "WHERE course_name LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE course_prof LIKE ?";
			}	
			//SQL문 작성
			//eva_show가 2(표시)인것만 가져오고, 같은 (강의, 교수)에서 최근에 등록된 강의평만 메인 리스트에 보여준다. 
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT e.*, ac.course_name, ac.course_prof, ROW_NUMBER() OVER "
				+ "(PARTITION BY ac.course_name, ac.course_prof ORDER BY e.eva_num DESC) as rnk FROM all_course_eva e "
				+ "JOIN all_course ac ON e.course_num = ac.course_num WHERE (ac.course_name, ac.course_prof) IN (SELECT course_name, course_prof FROM all_course " + sub_sql 
				+ ") AND e.eva_show = 2 ORDER BY ac.course_name, ac.course_prof, e.eva_num DESC) a WHERE a.rnk = 1 ORDER BY a.eva_num DESC) WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<CourseEvaVO>();
			while(rs.next()) {
				CourseEvaVO courseeva = new CourseEvaVO();
				courseeva.setEva_num(rs.getInt("eva_num"));
				courseeva.setEva_star(rs.getDouble("eva_star"));
				courseeva.setEva_content(rs.getString("eva_content"));
				courseeva.setEva_semester(rs.getString("eva_semester"));
				
				//강의 정보를 담기 위해 CourseVO 객체 생성
				CourseVO course = new CourseVO();
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_prof(rs.getString("course_prof"));
				
				courseeva.setCourseVO(course);
				
				list.add(courseeva);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//강의평 상세 - 전체 글 개수
	public int getEvaDetailCount(String course_name, String course_prof)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			//eva_show가 2(표시)인 글 개수 가져오기
			sql = "SELECT COUNT(*) FROM all_course_eva JOIN all_course "
				+ "USING(course_num) WHERE course_name=? AND course_prof=? AND eva_show = 2";
			//PreparedStatement 객체
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, course_name);
			pstmt.setString(2, course_prof);
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
	
	//강의평 상세 
	public List<CourseEvaVO> getCourseEva(int start, int end,String course_name, String course_prof)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseEvaVO> list = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql ="SELECT * FROM (SELECT a.*,rownum rnum "
				+ "FROM (SELECT * FROM all_course_eva JOIN all_course USING(course_num) "
				+ "WHERE course_name=? AND course_prof=? AND eva_show = 2 ORDER BY eva_num DESC)a) "
				+ "WHERE rnum >=? AND rnum <=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, course_name);
			pstmt.setString(2, course_prof);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<CourseEvaVO>();
			while(rs.next()) {
				CourseEvaVO courseeva = new CourseEvaVO();
				courseeva.setCourse_num(rs.getInt("course_num"));
				courseeva.setEva_num(rs.getInt("eva_num"));
				courseeva.setMem_num(rs.getInt("mem_num"));
				courseeva.setEva_star(rs.getDouble("eva_star"));
				courseeva.setEva_content(rs.getString("eva_content"));
				courseeva.setEva_complaint(rs.getInt("eva_complaint"));
				courseeva.setEva_show(rs.getInt("eva_show"));
				courseeva.setEva_semester(rs.getString("eva_semester"));
				courseeva.setEva_reg_date(rs.getDate("eva_reg_date"));
				courseeva.setEva_fav(rs.getInt("eva_fav"));
				//강의 정보를 담기 위해 CourseVO 객체 생성
				CourseVO course = new CourseVO();
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_prof(rs.getString("course_prof"));
				courseeva.setCourseVO(course);
				
				list.add(courseeva);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}	
		return list;
	}
	
	
	//좋아요 등록
	public void insertEvaFav(CourseEvaFavVO courseevafavVO)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null; 
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			
			//all_eva_fav에 정보 등록
			//SQL문 작성
			sql = "INSERT INTO all_eva_fav (eva_num,mem_num) VALUES (?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, courseevafavVO.getEva_num());
			pstmt.setInt(2, courseevafavVO.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
			
			
			
		}catch(Exception e) {
			
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	public void insertCourseEvaFav(int eva_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null; 
		String sql = null;
		
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			//좋아요 수 1 update
			//SQL문 작성
			sql = "UPDATE all_course_eva SET eva_fav = eva_fav+1 WHERE eva_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eva_num);
			//SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	
	//좋아요 개수
	public int selectEvaFavCount(int eva_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM all_eva_fav WHERE eva_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, eva_num);
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
	
	public int selectEvafav1Count(int eva_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT eva_fav FROM all_course_eva WHERE eva_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, eva_num);
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
	
	//회원번호와 게시물 번호를 이용한 좋아요 정보
	public CourseEvaFavVO selectEvaFav(CourseEvaFavVO courseevaVO)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		CourseEvaFavVO fav = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM all_eva_fav WHERE eva_num=? AND mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩    //행이 존재하면 좋아요 한거고, 행이 존재하지 않으면 좋아요 안한거임
			pstmt.setInt(1, courseevaVO.getEva_num());
			pstmt.setInt(2, courseevaVO.getMem_num());
			//SQL문 실행
			rs= pstmt.executeQuery();
			if(rs.next()) {
				fav = new CourseEvaFavVO();
				fav.setEva_num(rs.getInt("eva_num"));
				fav.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally { 
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return fav;
	}
	
	
	//좋아요 삭제
	public void deleteEvaFav(CourseEvaFavVO favVO)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null; 
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM all_eva_fav WHERE eva_num=? AND mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, favVO.getEva_num());
			pstmt.setInt(2, favVO.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	public void deleteEvaFav1(int eva_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null; 
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE all_course_eva SET eva_fav = eva_fav-1 WHERE eva_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, eva_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	///////////////////////신고 부분 ////
	//신고 개수 업데이트(신고 클릭시 추가)
	public void addEvaComplaintCount(int eva_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE all_course_eva SET eva_complaint=eva_complaint+1 WHERE eva_num=?";//신고 클릭되면 확인창 후 1 증가
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, eva_num);
			//SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//신고 테이블에 신고 정보 등록
	public void insertEvaWarn(CourseEvaWarnVO warnVO)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO all_eva_warn (eva_num,mem_num) VALUES (?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, warnVO.getEva_num());
			pstmt.setInt(2, warnVO.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//신고 개수 
	public int selectEvaWarnCount(int eva_num,int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM all_eva_warn WHERE eva_num=? AND mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, eva_num);
			pstmt.setInt(2, mem_num);
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

	//신고 개수 3개이상 미표시 처리
	public void evaComplaintUpdateShow(int eva_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE all_course_eva SET eva_show=1 WHERE eva_complaint>=3 AND eva_num=?";//신고 클릭되면 확인창 후 1 증가
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, eva_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//신고 총 개수 가져오기
	public CourseEvaVO getEvaComplaint(int eva_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		CourseEvaVO evacomplaint = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT eva_complaint FROM all_course_eva WHERE eva_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, eva_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				evacomplaint = new CourseEvaVO();
				evacomplaint.setEva_complaint(rs.getInt("eva_complaint"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		return evacomplaint;
	}
	
}
