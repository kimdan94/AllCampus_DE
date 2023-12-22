package kr.question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.notice.vo.NoticeVO;
import kr.question.vo.QuestionVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class QuestionDAO {
	//싱글턴 패턴
		private static QuestionDAO instance = new QuestionDAO();
		public static QuestionDAO getinstance() {
			return instance;
		}
		private QuestionDAO() {}
		
		
		//전체 레코드수/검색 레코드수
		public int getQuestionCount(String keyfield,String keyword)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			String sub_sql = "";//빈 문자열
			int count = 0;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				if(keyword!=null && !"".equals(keyword)) {
					if(keyfield.equals("1")) sub_sql += "WHERE question_title LIKE ?";
					else if(keyfield.equals("2")) sub_sql += "WHERE question_content LIKE ?";
				}
				//SQL문 작성
				sql = "SELECT COUNT(*) FROM all_question " + sub_sql;
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				if(keyword != null && !"".equals(keyword)) {
					pstmt.setString(1, "%" + keyword + "%");
				}
				//SQL문 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.getConnection();
			}
			return count;
		}
		//전체 글/검색 글 목록
		public List<QuestionVO> getListQuestion(int start,int end,String keyfield,String keyword)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<QuestionVO> list = null;
			String sql = null;
			String sub_sql ="";//빈 문자열
			int cnt=0;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				if(keyword!=null && !"".equals(keyword)) {
					//검색 처리
					if(keyfield.equals("1")) sub_sql += "WHERE question_title LIKE ?";
					else if(keyfield.equals("2")) sub_sql += "WHERE question_content LIKE ?";
				}
				//SQL문 작성
				sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM "
					+ "(SELECT * FROM all_question " + sub_sql
					+ " ORDER BY question_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				if(keyword != null && !"".equals(keyword)) {
					pstmt.setString(++cnt, "%" + keyword +"%");
				}
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				//SQL문 실행
				rs = pstmt.executeQuery();
				list = new ArrayList<QuestionVO>();
				while(rs.next()) {
					QuestionVO question = new QuestionVO();
					question.setQuestion_num(rs.getInt("question_num"));
					//HTML을 허용하지 않음
					question.setQuestion_title(StringUtil.useNoHtml(rs.getString("question_title")));
					question.setQuestion_content(StringUtil.useNoHtml(rs.getString("question_content")));
					list.add(question);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.getConnection();
			}
			return list;
		}

		// 글 상세
		public QuestionVO getDetailQuestion(int question_num) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			QuestionVO question = null;
			String sql = null;

			try {
				// 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				// SQL문 작성
				sql = "SELECT * FROM all_question WHERE question_num=?";
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				// ?에 데이터 바인딩
				pstmt.setInt(1, question_num);
				// SQL문 실행
				rs = pstmt.executeQuery();
				if (rs.next()) {
					question = new QuestionVO();
					question.setQuestion_num(rs.getInt("question_num"));
					question.setQuestion_title(rs.getString("question_title"));
					question.setQuestion_content(rs.getString("question_content"));
				}
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}

			return question;
		}
	//글 등록
	public void insertQuestion(QuestionVO question)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO all_question (mem_num,question_title,question_filename,question_board_email,"
					+ "question_content) VALUES (all_question_seq.nextval,"
					+ "?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, question.getQuestion_title());
			pstmt.setString(2, question.getQuestion_filename());
			pstmt.setString(3, question.getQuestion_board_email());
			pstmt.setString(4, question.getQuestion_content());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	}
