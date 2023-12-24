package kr.calculator.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.calculator.dao.CalculatorDAO;
import kr.calculator.vo.CalculatorVO;
import kr.controller.Action;

public class SelectTimetableAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		int timetable_year = Integer.parseInt(request.getParameter("timetableYear"));
		int timetable_semester = Integer.parseInt(request.getParameter("timetableSemester"));
		
		if(user_num == null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout"); 
		}else {//로그인 된 경우
			List<CalculatorVO> list = null;
			CalculatorDAO calDao = CalculatorDAO.getInstance();
			//시간표 정보 가져오기(강의명, 강의수강학점) 
			list = calDao.getTimetable(timetable_year,timetable_semester,user_num);
			mapAjax.put("list", list);
			mapAjax.put("result", "success");
		}
		
		//결과 JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
