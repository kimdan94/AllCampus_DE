package kr.main.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;

public class HomeAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}else if(user_num != null) {
			//로그인이 된 경우 home.jsp에 출력할 학교명 구하기
			Integer user_univ = (Integer)session.getAttribute("user_univ");
			MemberDAO dao = MemberDAO.getinstance();
			String univ_name = dao.checkUniv(user_univ);
			
			request.setAttribute("univ_name", univ_name);
		}
		//JSP 경로 반환
		return "/WEB-INF/views/main/home.jsp";
	}

}
 