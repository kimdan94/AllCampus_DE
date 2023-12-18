package kr.question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.question.vo.QuestionVO;
import kr.util.DBUtil;

public class QuestionDAO {
	//싱글턴 패턴
		private static QuestionDAO instance = new QuestionDAO();
		public static QuestionDAO getinstance() {
			return instance;
		}
		private QuestionDAO() {}
		
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
