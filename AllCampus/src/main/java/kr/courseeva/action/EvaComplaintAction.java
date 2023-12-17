package kr.courseeva.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;


import kr.controller.Action;
import kr.courseeva.dao.CourseEvaDAO;
import kr.courseeva.vo.CourseEvaVO;
import kr.courseeva.vo.CourseEvaWarnVO;

public class EvaComplaintAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		int eva_num = Integer.parseInt(request.getParameter("eva_num"));
		
		if(user_num == null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			
			//신고 정보를 자바빈에 담음
			CourseEvaWarnVO warnVO = new CourseEvaWarnVO();
			warnVO.setEva_num(eva_num);
			warnVO.setMem_num(user_num);
			
			CourseEvaDAO dao = CourseEvaDAO.getInstance();
			
			//신고 테이블에서 eva_num,user_num이 같은 행 개수 반환
			int count = dao.selectEvaWarnCount(eva_num,user_num);
			if(count > 0) {//같은 사람이 해당 게시글을 한번 더 신고했을 경우
				mapAjax.put("result", "duplicated");
				
				//결과 JSON 문자열 생성
				ObjectMapper mapper = new ObjectMapper();
				String ajaxData = mapper.writeValueAsString(mapAjax);
				
				request.setAttribute("ajaxData", ajaxData);
				return "/WEB-INF/views/common/ajax_view.jsp";
			}
			//신고 개수 update (board_complaint 1증가)
			dao.addEvaComplaintCount(eva_num);
			//신고 정보 all_eva_warn에 등록
			dao.insertEvaWarn(warnVO);

			//all_course_eva에서 eva_complaint 개수 가져옴
			CourseEvaVO vo = dao.getEvaComplaint(eva_num);
			int board_complaint = vo.getEva_complaint();
			//eva_complaint 신고 3개이상이면 eva_show 미표시(1)로 변경
			if(board_complaint >= 3) {
				dao.evaComplaintUpdateShow(eva_num);
			}
			
			mapAjax.put("result", "success");
		}
		
		//결과 JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
