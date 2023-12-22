package kr.question.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.question.dao.QuestionDAO;
import kr.question.vo.QuestionVO;
import kr.controller.Action;
import kr.util.PageUtil;

public class QuestionAction implements Action {
  
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum"); //처음에는 페이지가 null이니까 1로 간주하게 됨
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield"); //안넘어오면 null
		String keyword = request.getParameter("keyword");
		
		QuestionDAO dao = QuestionDAO.getinstance();
		int count = dao.getQuestionCount(keyfield, keyword);
		
		//페이지 처리
		PageUtil page = new PageUtil(keyfield, keyword, Integer.parseInt(pageNum),count,20,10,"faq.do");
		List<QuestionVO> list = null;
		if(count > 0 ) {
			list = dao.getListQuestion(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		}
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		//JSP 경로 반환
		return "/WEB-INF/views/faq/faq.jsp";
	}
}