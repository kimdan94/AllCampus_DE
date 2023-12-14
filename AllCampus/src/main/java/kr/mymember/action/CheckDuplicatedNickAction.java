package kr.mymember.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.mymember.dao.MyMemberDAO;
import kr.mymember.vo.MyMemberVO;

public class CheckDuplicatedNickAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		String mem_nick = request.getParameter("mem_nick");
		
		MyMemberDAO dao = MyMemberDAO.getInstance();
		MyMemberVO member = dao.checkMember(mem_nick);
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		if(member != null) {//닉네임 중복
			mapAjax.put("result", "isDuplicated");
		}else {//닉네임 미중복
			mapAjax.put("result", "nickNotFound");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);//JSP에서 읽어가게함
		
		//JSP 경로 반환
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
