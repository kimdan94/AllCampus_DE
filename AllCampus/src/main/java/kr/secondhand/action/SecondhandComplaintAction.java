package kr.secondhand.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.secondhand.dao.SecondhandDAO;
import kr.secondhand.vo.SecondhandVO;
import kr.secondhand.vo.SecondhandWarnVO;

public class SecondhandComplaintAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		int secondhand_num = Integer.parseInt(request.getParameter("secondhand_num"));
		
		SecondhandDAO sc = SecondhandDAO.getInstance();
		if(user_num == null) {//로그인이 되지 않은 경우
			mapAjax.put("status", "noLogin");
		}else {//로그인이 된 경우
			if(user_auth == 2) {//학교 미인증 회원이라면
				mapAjax.put("status", "noCertify");
			}else {//로그인이 되었고 학교 인증 회원 or 관리자라면
				
				//신고 정보를 자바빈에 담음
				SecondhandWarnVO warn = new SecondhandWarnVO();
				warn.setSecondhand_num(secondhand_num);
				warn.setMem_num(user_num);
				
				//신고 테이블에서 secondhand_num과 mem_num에 해당하는 행 찾기
				int count = sc.selectWarnCount(secondhand_num,user_num);
				if(count > 0) {//같은 사람이 해당 게시글을 한 번 더 신고했을 경우
					mapAjax.put("status", "duplicated");
					
					//결과 JSON 문자열 생성
					ObjectMapper mapper = new ObjectMapper();
					String ajaxData = mapper.writeValueAsString(mapAjax);
					
					request.setAttribute("ajaxData", ajaxData);
					return "/WEB-INF/views/common/ajax_view.jsp";
				}
				//신고 개수 update (secondhand_complaint 1 증가)
				sc.addWarnCount(secondhand_num);
				//신고 정보 all_secondhand_warn에 등록
				sc.insertWarn(warn);
				
				SecondhandVO db_sc = sc.getsecondhand(secondhand_num);
				int secondhand_complaint = db_sc.getSecondhand_complaint();
				//개수가 3개 이상이면 secondhand_show(default:2 -> 1) 변경
				if(secondhand_complaint >= 3) {
					sc.updateShow(secondhand_num);
				}
				
				mapAjax.put("status", "success");
			}
			//결과 JSON 문자열 생성
			ObjectMapper mapper = new ObjectMapper();
			String ajaxData = mapper.writeValueAsString(mapAjax);
			
			request.setAttribute("ajaxData", ajaxData);
		}
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
