package kr.secondhand.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.secondhand.dao.SecondhandDAO;

public class GetSellAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		int secondhand_num = Integer.parseInt(request.getParameter("secondhand_num"));
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		SecondhandDAO sc = SecondhandDAO.getInstance();
		if(user_num == null) {//로그인이 되지 않은 경우
			mapAjax.put("status", "noSell");
		}else {//로그인이 된 경우
			if(user_auth == 2) {//학교 미인증 회원이라면
				mapAjax.put("status", "noSell");
			}else {//로그인이 되었고 학교 인증 회원 or 관리자라면
				int sellCheck = sc.selectSell(secondhand_num);
				
				if(sellCheck == 2) {
					mapAjax.put("status", "yesSell");
				}else {
					mapAjax.put("status", "noSell");
				}
			}
		}
		//JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
