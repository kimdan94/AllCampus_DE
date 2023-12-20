package kr.calculator.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.calculator.dao.CalculatorDAO;
import kr.calculator.vo.CalTotalVO;
import kr.controller.Action;

public class CalculatorListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		CalculatorDAO dao = CalculatorDAO.getInstance();
		CalTotalVO totalscore = dao.totalScore(user_num);
		request.setAttribute("totalscore", totalscore);
		
		
		return "/WEB-INF/views/calculator/calculator_list.jsp";
	}
}
