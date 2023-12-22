package kr.warn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardVO;
import kr.courseeva.vo.CourseEvaVO;
import kr.member.vo.MemberVO;
import kr.secondhand.vo.SecondhandVO;
import kr.secondhand.vo.SecondhandWarnVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class WarnDAO {
	//싱글턴 패턴
		private static WarnDAO instance = new WarnDAO();
		public static WarnDAO getInstance() {
			return instance;
		}
		
		private WarnDAO() {}
		
			//자유게시판 전체 레코드수/검색 레코드수
			public int getWarnCount() throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count=0;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT COUNT(*) FROM all_board JOIN (SELECT DISTINCT(board_num) FROM all_board_warn) USING (board_num)";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
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
		//자유게시판 전체 글/검색 글 목록
		public List<BoardVO> getListBoard(int start,int end)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<BoardVO> list = null;
			String sql = null;
			int cnt=0;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
						+ "(SELECT board_num,board_title,board_content,board_reg_date,mem_num,mem_id FROM all_board JOIN all_member USING(mem_num) JOIN (SELECT DISTINCT(board_num) FROM all_board_warn) USING (board_num) "
						+ " ORDER BY board_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				
				//SQL문 실행 
				rs = pstmt.executeQuery();
				list = new ArrayList<BoardVO>();
				while(rs.next()) {
					BoardVO board = new BoardVO(); 
					board.setBoard_num(rs.getInt("board_num")); 
					//HTML를 허용하지 않음
					board.setBoard_title(StringUtil.useNoHtml(rs.getString("board_title")));
					board.setBoard_content(StringUtil.useNoHtml(rs.getString("board_content")));
					board.setBoard_reg_date(rs.getDate("board_reg_date"));
					board.setMem_num(rs.getInt("mem_num"));
					board.setMem_id(rs.getString("mem_id"));
					
					list.add(board);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		//자유게시판 등급변경
		public void warnAuth(int mem_num)throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성(auth=3:일반회원)
				sql = "UPDATE all_member SET mem_auth = 3 WHERE mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, mem_num);
				//SQL문 실행
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//자유게시판 신고글 삭제
		public void deleteBoard(int board_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "DELETE FROM all_board_warn WHERE board_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, board_num);
				//SQL문 실행 
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//자유게시판 신고글 표시(복구 default = 1)
		public void complaintUpdateShow(int board_num)throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE all_board SET board_show=1 WHERE board_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, board_num);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}


//--------------------------------------------------------------------------------------------

		//강의평 신고 전체 레코드수/검색 레코드수
		public int getCourseWarnCount() throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count = 0;

			try {
				// 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				// SQL문 작성
				sql = "SELECT COUNT(*) FROM all_course_eva JOIN (SELECT DISTINCT(eva_num) FROM all_eva_warn) USING (eva_num)";
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// SQL문 실행
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}

			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return count;
		}

		//강의평 신고 전체 글/검색 글 목록
		public List<CourseEvaVO> getListCourseWarn(int start, int end) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<CourseEvaVO> list = null;
			String sql = null;
			int cnt = 0;

			try {
				// 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				// SQL문 작성
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
						+ "(SELECT eva_num,eva_content,eva_reg_date,mem_id FROM all_course_eva JOIN all_member USING(mem_num) JOIN (SELECT DISTINCT(eva_num) FROM all_eva_warn) USING (eva_num) "
						+ " ORDER BY eva_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);

				// SQL문 실행
				rs = pstmt.executeQuery();
				list = new ArrayList<CourseEvaVO>();
				while (rs.next()) {
					CourseEvaVO courseeva = new CourseEvaVO();
					courseeva.setEva_num(rs.getInt("eva_num"));
					// HTML를 허용하지 않음
					courseeva.setEva_content(StringUtil.useNoHtml(rs.getString("eva_content")));
					courseeva.setEva_reg_date(rs.getDate("eva_reg_date"));

					MemberVO memberVO = new MemberVO();
					memberVO.setMem_id(rs.getString("mem_id"));
					
					courseeva.setMemberVO(memberVO);
					
					list.add(courseeva);
				}
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}

		//강의평 신고 등급변경
		public void CoursewarnAuth(int mem_num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			try {
				// 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				// SQL문 작성(auth=3:일반회원)
				sql = "UPDATE all_member SET mem_auth = 3 WHERE mem_num=?";
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// ?에 데이터 바인딩
				pstmt.setInt(1, mem_num);
				// SQL문 실행
				pstmt.executeUpdate();

			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}

		//강의평 신고글 삭제
		public void deleteCourse(int eva_num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			try {
				// 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				// SQL문 작성
				sql = "DELETE FROM all_eva_warn WHERE eva_num=?";
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// ?에 데이터 바인딩
				pstmt.setInt(1, eva_num);
				// SQL문 실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}

		//강의평 신고글 표시(복구 default = 0)
		public void CourseUpdateShow(int eva_num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			try {
				// 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				// SQL문 작성
				sql = "UPDATE all_course_eva SET eva_show=1 WHERE eva_num=?";
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// ?에 데이터 바인딩
				pstmt.setInt(1, eva_num);
				// SQL문 실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}

//--------------------------------------------------------------------------------------------

		//중고거래 신고 전체 레코드수/검색 레코드수
		public int getSecondWarnCount() throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count = 0;

			try {
				// 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				// SQL문 작성
				sql = "SELECT COUNT(*) FROM all_secondhand JOIN (SELECT DISTINCT(secondhand_num) FROM all_secondhand_warn) USING (secondhand_num)";
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// SQL문 실행
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}

			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return count;
		}

		//중고거래 신고 전체 글/검색 글 목록
		public List<SecondhandVO> getListSecond(int start, int end) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<SecondhandVO> list = null;
			String sql = null;
			int cnt = 0;

			try {
				// 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				// SQL문 작성
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
						+ "(SELECT secondhand_num,secondhand_content,secondhand_reg_date,mem_id FROM all_secondhand JOIN all_member USING(mem_num) JOIN (SELECT DISTINCT(secondhand_num) FROM all_secondhand_warn) USING (secondhand_num) "
						+ " ORDER BY secondhand_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);

				// SQL문 실행
				rs = pstmt.executeQuery();
				list = new ArrayList<SecondhandVO>();
				while (rs.next()) {
					SecondhandVO Secondhand = new SecondhandVO();
					Secondhand.setSecondhand_num(rs.getInt("Secondhand_num"));
					// HTML를 허용하지 않음
					Secondhand.setSecondhand_content(StringUtil.useNoHtml(rs.getString("Secondhand_content")));
					Secondhand.setSecondhand_reg_date(rs.getDate("Secondhand_reg_date"));

					MemberVO memberVO = new MemberVO();
					memberVO.setMem_id(rs.getString("mem_id"));
					
					list.add(Secondhand);
				}
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}

		//중고거래 신고 등급변경
		public void SecondwarnAuth(int mem_num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			try {
				// 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				// SQL문 작성(auth=3:일반회원)
				sql = "UPDATE all_member SET mem_auth = 3 WHERE mem_num=?";
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// ?에 데이터 바인딩
				pstmt.setInt(1, mem_num);
				// SQL문 실행
				pstmt.executeUpdate();

			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}

		//중고거래 신고글 삭제
		public void deleteSecond(int second_num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			try {
				// 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				// SQL문 작성
				sql = "DELETE FROM all_eva_warn WHERE eva_num=?";
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// ?에 데이터 바인딩
				pstmt.setInt(1, second_num);
				// SQL문 실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}

		//강의평 신고글 표시(복구 default = 0)
		public void SecondUpdateShow(int secondhand_num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			try {
				// 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				// SQL문 작성
				sql = "UPDATE all_secondhand SET secondhand_show=1 WHERE secondhand_num=?";
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// ?에 데이터 바인딩
				pstmt.setInt(1, secondhand_num);
				// SQL문 실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
	}
