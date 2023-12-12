package kr.mymember.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mymember.dao.MyMemberDAO;
import kr.mymember.vo.MyMemberVO;
           
public class MyPageAction implements Action{
                           
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인 X
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//회원정보
		MyMemberDAO dao = MyMemberDAO.getInstance();
		MyMemberVO member = dao.getMember(user_num);
		
		request.setAttribute("member", member);
		//JSP 경로 반환
		return "/WEB-INF/views/mymember/myPage.jsp";
	}
}              
