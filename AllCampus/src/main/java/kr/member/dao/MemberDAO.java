package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberUnivVO;
import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getinstance() {
		return instance;
	}
	private MemberDAO() {}
	
	//학교 header 명시
	public String checkUniv(int univNum)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String univName = null;
		
		try {
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT univ_name FROM all_member_univ WHERE univ_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, univNum);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				univName = rs.getString("univ_name");
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return univName;
	}
	//회원가입
	public void insertMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		int num = 0;//시퀀스 번호 저장
		
		try {
			conn = DBUtil.getConnection();
			//오토 커밋 해제
			conn.setAutoCommit(false);
			 
			//회원 번호(mem_num) 생성
			sql = "SELECT all_member_seq.nextval FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1);
			}
			
			//all_member 테이블
			sql = "INSERT INTO all_member (mem_num,mem_id,mem_nick,mem_email) VALUES (?,?,?,?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, num);
			pstmt2.setString(2, member.getMem_id());
			pstmt2.setString(3, member.getMem_nick());
			pstmt2.setString(4, member.getMem_email());
			pstmt2.executeUpdate();
			
			//all_member_detail 테이블
			sql = "INSERT INTO all_member_detail (mem_num,mem_name,mem_passwd,"
					+ "univ_num,mem_univNum,mem_major) VALUES (?,?,?,?,?,?)";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, num);
			pstmt3.setString(2, member.getMem_name());
			pstmt3.setString(3, member.getMem_passwd());
			pstmt3.setInt(4, member.getUniv_num());
			pstmt3.setInt(5, member.getMem_univNum());
			pstmt3.setString(6, member.getMem_major());
			pstmt3.executeUpdate();
			
			//SQL문 실행 시 모두 성공하면 commit
			conn.commit();
			
		}catch(Exception e) {
			//SQL문이 하나라도 실패하면 rollback
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//ID 중복 체크 및 로그인 처리
	public MemberVO checkMember(String userId)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM all_member LEFT OUTER JOIN all_member_detail USING(mem_num) WHERE mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_auth(rs.getInt("mem_auth"));
				member.setMem_passwd(rs.getString("mem_passwd"));
				member.setMem_photo(rs.getString("mem_photo"));
				member.setMem_email(rs.getString("mem_email"));
				member.setUniv_num(rs.getInt("univ_num"));
				member.setMem_nick(rs.getString("mem_nick"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	//닉네임 중복 체크
	public String checkMemberNick(String userNick)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String nick = null;
		
		try {
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM all_member LEFT OUTER JOIN all_member_detail USING(mem_num) WHERE mem_nick=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userNick);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				nick = rs.getString("mem_nick");
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return nick;
	}
	//이메일 중복 체크
	public String checkMemberEmail(String userEmail)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String email = null;
		
		try {
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM all_member LEFT OUTER JOIN all_member_detail USING(mem_num) WHERE mem_email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userEmail);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				email = rs.getString("mem_email");
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return email;
	}
	
	//학교 옵션값 불러오기
	public List<MemberUnivVO> univOption()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<MemberUnivVO> list = null;
		
		try {
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM all_member_univ ORDER BY univ_name ASC";
			pstmt = conn.prepareStatement(sql);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<MemberUnivVO>();
			while(rs.next()) {
				MemberUnivVO univOption = new MemberUnivVO();
				univOption.setUniv_num(rs.getInt("univ_num"));
				univOption.setUniv_name(rs.getString("univ_name"));
				list.add(univOption);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//아이디 찾기
	public String checkId(String user_email)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String user_id = null;
		
		try {
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT mem_id FROM all_member LEFT OUTER JOIN all_member_detail "
					+ "USING(mem_num) WHERE mem_email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_email);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user_id = rs.getString("mem_id");
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return user_id;
	}
	//비밀번호 찾기
	public String checkPw(String user_email, String user_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String user_pw = null;
		
		try {
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT mem_passwd FROM all_member LEFT OUTER JOIN all_member_detail "
					+ "USING(mem_num) WHERE mem_email = ? AND mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_email);
			pstmt.setString(2, user_id);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user_pw = rs.getString("mem_passwd");
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return user_pw;
	}	
}