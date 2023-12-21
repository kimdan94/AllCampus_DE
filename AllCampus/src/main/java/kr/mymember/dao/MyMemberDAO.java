package kr.mymember.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardReplyVO;
import kr.board.vo.BoardVO;
import kr.course.vo.CourseVO;
import kr.courseeva.vo.CourseEvaVO;
import kr.member.vo.MemberVO;
import kr.mymember.vo.MyMemberVO;
import kr.secondhand.vo.SecondhandVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class MyMemberDAO {
	// 싱글턴 패턴
	private static MyMemberDAO instance = new MyMemberDAO();

	public static MyMemberDAO getInstance() {
		return instance;
	}

	private MyMemberDAO() {
	}

	// 회원 상세 정보
	public MyMemberVO getMember(int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MyMemberVO member = null;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT * FROM all_member JOIN all_member_detail USING(mem_num) " 
				+ "WHERE mem_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			// SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MyMemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_auth(rs.getInt("mem_auth"));
				member.setMem_passwd(rs.getString("mem_passwd"));
				member.setMem_nick(rs.getString("mem_nick"));
				member.setMem_name(rs.getString("mem_name"));
				member.setMem_email(rs.getString("mem_email"));
				member.setMem_univNum(rs.getInt("mem_univNum"));
				member.setMem_major(rs.getString("mem_major"));
				member.setMem_major2(rs.getString("mem_major2"));
				member.setMem_photo(rs.getString("mem_photo"));
				member.setMem_reg_date(rs.getDate("mem_reg_date"));
				member.setMem_certify(rs.getDate("mem_certify"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}

	// 회원 정보 변경
	// 내 정보 변경(프로필 사진 변경)
	public void updateMyPhoto(String mem_photo, int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "UPDATE all_member_detail SET mem_photo=? WHERE mem_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setString(1, mem_photo);
			pstmt.setInt(2, mem_num);
			// SQL문 실행
			pstmt.executeUpdate();

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 내 정보 변경(닉네임&전공)
	public void updateMyNick_Major(MyMemberVO member) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			// SQL문 작성
			sql = "UPDATE all_member SET mem_nick=? WHERE mem_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setString(1, member.getMem_nick());
			pstmt.setInt(2, member.getMem_num());
			// SQL문 실행
			pstmt.executeUpdate();

			sql = "UPDATE all_member_detail SET mem_major=?,mem_major2=? WHERE mem_num=?";
			// PreparedStatement 객체 생성
			pstmt2 = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt2.setString(1, member.getMem_major());
			pstmt2.setString(2, member.getMem_major2());
			pstmt2.setInt(3, member.getMem_num());
			// SQL문 실행
			pstmt2.executeUpdate();
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 닉네임 중복 체크
	public MyMemberVO checkMember(String mem_nick) throws Exception {
		System.out.println(mem_nick);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MyMemberVO member = null;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT * FROM all_member WHERE mem_nick=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setString(1, mem_nick);
			// SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MyMemberVO();
				member.setMem_nick(rs.getString("mem_nick"));

			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}

	// ID 체크
	public MyMemberVO checkId(String mem_id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		MyMemberVO member = null;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT * FROM all_member LEFT OUTER JOIN all_member_detail USING(mem_num) WHERE mem_id=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setString(1, mem_id);
			// SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MyMemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setMem_id(rs.getString("mem_id"));
				member.setMem_auth(rs.getInt("mem_auth"));
				member.setMem_passwd(rs.getString("mem_passwd"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}

	// 비밀번호 수정
	public void updatePassword(String mem_passwd, int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "UPDATE all_member_detail SET mem_passwd=? WHERE mem_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setString(1, mem_passwd);
			pstmt.setInt(2, mem_num);
			// SQL문 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 학교 인증(auth변경)
	public void insertFile(MyMemberVO mymember) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			// 인증파일,인증일 변경
			// SQL문 작성
			sql = "UPDATE all_member_detail SET mem_certify=SYSDATE,mem_certifyfilename=? " + "WHERE mem_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setString(1, mymember.getMem_certifyfilename());
			pstmt.setInt(2, mymember.getMem_num());
			// SQL문 실행
			pstmt.executeUpdate();

			// all_member의 auth값 변경
			sql = "UPDATE all_member SET mem_auth=3 WHERE mem_num=?";
			// PreparedStatement 객체 생성
			pstmt2 = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt2.setInt(1, mymember.getMem_num());
			// SQL문 실행
			pstmt2.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 회원탈퇴
	public void deleteMember(int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			// auto COMMIT 해제
			conn.setAutoCommit(false);

			// all_member의 auth값 변경
			sql = "UPDATE all_member SET mem_auth=0 WHERE mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			pstmt.executeUpdate();

			// all_member_detail의 레코드 삭제
			sql = "DELETE FROM all_member_detail WHERE mem_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, mem_num);
			pstmt2.executeUpdate();

			// 전체 SQL문 실행이 성공하면
			conn.commit();
		} catch (Exception e) {
			// SQL문이 하나라도 실패하면 ROLLBACK
			conn.rollback();
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt2, conn);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	// 내 글&댓글&스크랩
	// 내가 쓴 글 전체 레코드 수 - all_board
	public int getBoardCount(int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT COUNT(*) FROM all_board JOIN all_member USING(mem_num) WHERE mem_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			// SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {// 전체 레코드에 페이지 처리
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}

	// 내가 쓴 글 목록 - all_board
	public List<BoardVO> getListBoard(int start, int end, int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<BoardVO> list = null;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();

			// SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM all_board JOIN all_member USING(mem_num) WHERE mem_num=? "
					+ "ORDER BY board_reg_date DESC)a) WHERE rnum >=? AND rnum <=?";

			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			// SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_title(StringUtil.useNoHtml(rs.getString("board_title")));
				board.setBoard_reg_date(rs.getDate("board_reg_date"));
				
				list.add(board);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}

	// 내가 쓴 글 전체 레코드 수 - all_secondhand
	public int getSecondCount(int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count2 = 0;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT COUNT(*) FROM all_secondhand JOIN all_member USING(mem_num) WHERE mem_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			// SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {// 전체 레코드에 페이지 처리
				count2 = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count2;
	}

	// 내가 쓴 글 전체 글 - all_secondhand
	public List<SecondhandVO> getListSecond(int start, int end, int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<SecondhandVO> list2 = null;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();

			// SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM all_secondhand JOIN all_member USING(mem_num) WHERE mem_num=? "
					+ "ORDER BY secondhand_reg_date DESC)a) WHERE rnum >=? AND rnum <=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			// SQL문 실행
			rs = pstmt.executeQuery();
			list2 = new ArrayList<SecondhandVO>();
			while (rs.next()) {
				SecondhandVO second = new SecondhandVO();
				second.setSecondhand_num(rs.getInt("secondhand_num"));
				second.setSecondhand_name(rs.getString("secondhand_name"));
				second.setSecondhand_writer(rs.getString("secondhand_writer"));
				second.setSecondhand_price(rs.getInt("secondhand_price"));
				second.setSecondhand_reg_date(rs.getDate("secondhand_reg_date"));

				list2.add(second);

			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list2;
	}

	// 내가 쓴 글 전체 레코드 수 - all_course_eva
	public int getCourse(int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count3 = 0;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT COUNT(*) FROM all_course_eva JOIN all_member USING(mem_num) WHERE mem_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			// SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {// 전체 레코드에 페이지 처리
				count3 = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count3;
	}

	// 내가 쓴 글 전체 글 - all_course_eva
	public List<CourseEvaVO> getListCourse(int start, int end, int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<CourseEvaVO> list3 = null;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();

			// SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM all_course_eva JOIN all_course USING(course_num) JOIN all_member USING(mem_num) WHERE mem_num=? "
					+ "ORDER BY eva_reg_date DESC)a) WHERE rnum >=? AND rnum <=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			// SQL문 실행
			rs = pstmt.executeQuery();
			list3 = new ArrayList<CourseEvaVO>();
			while (rs.next()) {
				CourseEvaVO courseeva = new CourseEvaVO();
				courseeva.setEva_num(rs.getInt("eva_num"));
				courseeva.setEva_reg_date(rs.getDate("eva_reg_date"));
				
				//강의명 담기 위해 CourseVO 객체 생성
				CourseVO course = new CourseVO();
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_prof(rs.getString("course_prof"));
				
				courseeva.setCourseVO(course);
				
				list3.add(courseeva);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list3;
	}

	// 내가 댓글 전체 레코드 수
	public int getBoardReplyCount(int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();

			// SQL문 작성
			sql = "SELECT COUNT(*) FROM all_board_reply JOIN all_member USING(mem_num) WHERE mem_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			// SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {// 전체 레코드에 페이지 처리
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}

	// 내가 댓글 단 글 전체 글
	public List<BoardVO> getListBoardReply(int start, int end, int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<BoardVO> list = null;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT distinct(board_num),board_title,board_reg_date FROM (SELECT a.*, rownum rnum FROM "
				+ "(SELECT * FROM all_board JOIN all_member USING(mem_num) "
				+ "JOIN all_board_reply f USING(board_num) WHERE f.mem_num=? "
				+ "ORDER BY board_reg_date DESC)a) WHERE rnum >=? AND rnum <= ?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			// SQL문 작성
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_title(StringUtil.useNoHtml(rs.getString("board_title")));
				board.setBoard_reg_date(rs.getDate("board_reg_date"));

				list.add(board);
			}

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}

	// 스크랩 전체 레코드 수
	public int getBoardScrapCount(int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();

			// SQL문 작성
			sql = "SELECT COUNT(*) FROM all_board_scrap JOIN all_member USING(mem_num) WHERE mem_num=?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			// SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {// 전체 레코드에 페이지 처리
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}

	// 내가 스크랩한 전체 글
	public List<BoardVO> getListScrapReply(int start, int end, int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<BoardVO> list = null;

		try {
			// 커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			// SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM all_board JOIN all_member USING(mem_num) "
					+ "JOIN all_board_scrap f USING(board_num) WHERE f.mem_num=? "
					+ "ORDER BY board_num DESC)a) WHERE rnum >=? AND rnum <= ?";
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			// ?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			// SQL문 작성
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_title(StringUtil.useNoHtml(rs.getString("board_title")));
				board.setBoard_content(StringUtil.useNoHtml(rs.getString("board_content")));
				board.setBoard_reg_date(rs.getDate("board_reg_date"));

				list.add(board);
			}

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}

	//
}
