package kr.mymember.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mymember.dao.MyMemberDAO;
import kr.mymember.vo.MyMemberVO;

public class ModifyMajorAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인이 된 경우
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//자바빈(VO) 생성
		MyMemberVO member = new MyMemberVO();
		member.setMem_num(user_num);
		member.setMem_major(request.getParameter("mem_major"));
		member.setMem_major2(request.getParameter("mem_major2"));
		
		
		MyMemberDAO dao = MyMemberDAO.getInstance();
		dao.updateMyMajor(member);
		
		//JSP 경로 반환
		return "/WEB-INF/views/mymember/modifyUser.jsp";
	}
}
