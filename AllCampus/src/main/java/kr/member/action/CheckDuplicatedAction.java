package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class CheckDuplicatedAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String nick = request.getParameter("nick");
		
		MemberDAO dao = MemberDAO.getinstance();
		Map<String,String> mapAjax = new HashMap<String,String>();
		if(id!=null) {
			MemberVO memberId = dao.checkMember(id);
			if(memberId == null) {//아이디 미중복
				mapAjax.put("result", "NotFound");
			}else {//아이디 중복
				mapAjax.put("result", "Duplicated");
			}
		}else if(email!=null) {
			String memberEmail = dao.checkMemberEmail(email);
			if(memberEmail == null) {//이메일 미중복
				mapAjax.put("result", "NotFound");
			}else {//이메일 중복
				mapAjax.put("result", "Duplicated");
			}
		}else if(nick!=null) {
			String memberNick = dao.checkMemberNick(nick);
			if(memberNick == null) {//닉네임 미중복
				mapAjax.put("result", "NotFound");
			}else {//닉네임 중복
				mapAjax.put("result", "Duplicated");
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		//JSP 경로 반환
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
