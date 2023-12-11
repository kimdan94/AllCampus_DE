package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberUnivVO;

public class RegisterUserFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//학교 목록을 select 하는 메서드 호출
		MemberDAO dao = MemberDAO.getinstance();
		List<MemberUnivVO> list = dao.univOption();
		
		request.setAttribute("list", list);
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/registerUserForm.jsp";
	}

}
 