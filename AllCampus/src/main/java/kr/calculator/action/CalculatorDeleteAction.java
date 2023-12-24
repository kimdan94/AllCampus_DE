package kr.calculator.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.calculator.dao.CalculatorDAO;
import kr.controller.Action;

public class CalculatorDeleteAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//System.out.println("-------------CalculatorDeleteAction--------------------------------");
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		String cal_semester = request.getParameter("cal_semester");
		if(user_num == null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			CalculatorDAO dao = CalculatorDAO.getInstance();
			dao.deleteScore(cal_semester,user_num);
			
			//모든 학기의 cal_avgscore  * finclude_acq  합한 값 
			double favgscore = dao.selectfAvgscore(user_num);  //cal_avgscore * cal_majorf_acq
			
			//모든 학기의 cal_majorscore  * majorf_acq  합한 값
			double fmajorscore = dao.selectfMajorscore(user_num);//cal_majorscore * cal_finclude_acq
			int acqscore = dao.selectAcqscore(user_num);
			
			int sumfinclude_acq = dao.sumFinclude(user_num);	// cal_finclude_acq 합
			int summajorf_acq = dao.sumMajorf(user_num);		//cal_majorf_acq 합
			
			double cal_total_avgscore;
			if(sumfinclude_acq > 0) {
				cal_total_avgscore = favgscore/(double)sumfinclude_acq;
			}else {
				cal_total_avgscore = 0;
			}
			double cal_total_majorscore;
			if(summajorf_acq>0) {
				cal_total_majorscore = fmajorscore/(double)summajorf_acq;
			}else {
				cal_total_majorscore = 0;
			}
			int cal_total_acq = acqscore;
			
			//System.out.println(cal_total_avgscore+","+cal_total_majorscore+","+cal_total_acq);
			dao.totalScore(user_num, cal_total_avgscore, cal_total_majorscore, cal_total_acq);
			
			mapAjax.put("result", "success");
		} 
		//JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		//JSP 경로 반환
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}