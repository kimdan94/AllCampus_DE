package kr.secondhand.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardVO;
import kr.secondhand.vo.SecondhandVO;
import kr.util.DBUtil;

public class SecondhandDAO {
	//싱글턴 패턴
	private static SecondhandDAO instance = new SecondhandDAO();
	
	public static SecondhandDAO getInstance() {
		return instance;
	}
	
	private SecondhandDAO() {}
	
	//글 등록
	public void insertSC(SecondhandVO sc)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO all_secondhand (secondhand_num,secondhand_name,"
					+ "secondhand_writer,secondhand_company,secondhand_content,"
					+ "secondhand_price,secondhand_way,secondhand_status,secondhand_filename,"
					+ "secondhand_openchat,secondhand_ip,mem_num) "
					+ "VALUES (all_secondhand_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, sc.getSecondhand_name());
			
			//저자명 가공
			if(sc.getSecondhand_writerPlus()!= null) {
				String writerPlus = sc.getSecondhand_writer() + " " + sc.getSecondhand_writerPlus();
				pstmt.setString(2,writerPlus);
			}else {
				pstmt.setString(2, sc.getSecondhand_writer());
			}
			
			pstmt.setString(3, sc.getSecondhand_company());
			pstmt.setString(4, sc.getSecondhand_content());
			pstmt.setInt(5, sc.getSecondhand_price());
			pstmt.setString(6, sc.getSecondhand_way());
			pstmt.setString(7, sc.getSecondhand_status());
			pstmt.setString(8, sc.getSecondhand_filename());
			pstmt.setString(9, sc.getSecondhand_openchat());
			pstmt.setString(10, sc.getSecondhand_ip());
			pstmt.setInt(11, sc.getMem_num());
			
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//전체 레코드 수/검색 레코드 수
	public int getSecondhandCount(String keyfield,String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				//검색 처리 - 교재명
				if(keyfield.equals("1")) sub_sql += "WHERE secondhand_name LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE secondhand_writer LIKE ?";
			}
			
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM all_secondhand JOIN all_member USING(mem_num) "+ sub_sql;
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
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
	//전체 글/검색 글 목록
	public List<SecondhandVO> getListSecondhand(int start, int end, 
											String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SecondhandVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				//검색 처리 - 교재명
				if(keyfield.equals("1")) sub_sql += "WHERE secondhand_name LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE secondhand_writer LIKE ?";
			}
			
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM all_secondhand JOIN all_member USING(mem_num) " + sub_sql
					+ " ORDER BY secondhand_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<SecondhandVO>();
			while(rs.next()) {
				SecondhandVO sc = new SecondhandVO();
				sc.setSecondhand_num(rs.getInt("secondhand_num"));
				sc.setSecondhand_filename(rs.getString("secondhand_filename"));
				sc.setSecondhand_name(rs.getString("secondhand_name"));
				sc.setSecondhand_writer(rs.getString("secondhand_writer"));
				sc.setSecondhand_company(rs.getString("secondhand_company"));
				sc.setSecondhand_price(rs.getInt("secondhand_price"));
				
				list.add(sc);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//글 상세
	//글 수정
	//글 삭제
	//물건 판매 여부 변경
	//신고 수 변경
	//댓글 등록
	//댓글 개수
	//댓글 목록
	//댓글 상세(댓글 수정, 삭제 시 작성자 회원번호 체크 용도로 사용)
	//댓글 수정
	//댓글 삭제
}
