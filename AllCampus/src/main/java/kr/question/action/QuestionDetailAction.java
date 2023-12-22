package kr.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.question.dao.QuestionDAO;
import kr.question.vo.QuestionVO;

public class QuestionDetailAction implements Action{
  
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 글번호 반환
		int question_num = Integer.parseInt(request.getParameter("question_num"));

		QuestionDAO dao = QuestionDAO.getinstance();
		QuestionVO question = dao.getDetailQuestion(question_num);
		request.setAttribute("question", question);
				
		return "/WEB-INF/views/faq/detail.jsp";
	}

}
