package kr.courseeva.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.courseeva.dao.CourseEvaDAO;
import kr.courseeva.vo.CourseEvaFavVO;


public class WriteEvaFavAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {//로그인이 되지 않은 경우
			mapAjax.put("result","logout");
		}else {//로그인 된 경우
		
			//전송된 데이터 인코딩 처리
			request.setCharacterEncoding("utf-8");
			//전송된 데이터 반환
			int eva_num = Integer.parseInt(request.getParameter("eva_num"));
			CourseEvaFavVO courseevafavVO = new CourseEvaFavVO();
			courseevafavVO.setEva_num(eva_num);
			courseevafavVO.setMem_num(user_num);
			  							
			CourseEvaDAO dao = CourseEvaDAO.getInstance();
			//좋아요 등록 여부 체크
			CourseEvaFavVO db_fav = dao.selectEvaFav(courseevafavVO);
			
			//토글 형태를 만들어줌 !!!  이걸로 제어를 한다. 
			if(db_fav!=null) {// 좋아요 등록 O (한 건의 레코드가 있음)
				//좋아요 삭제
				dao.deleteEvaFav(db_fav); 
				dao.deleteEvaFav1(eva_num); 
				mapAjax.put("status", "noFav"); //뚫려 있는 하트 이미지
			}else {//좋아요 등록 X
				//좋아요 등록
				dao.insertEvaFav(courseevafavVO);
				dao.insertCourseEvaFav(eva_num); //좋아요 개수 1 올려줌
				mapAjax.put("status", "yesFav"); //하트가 꽉 차 있는 이미지 
			}
			mapAjax.put("result", "success");
			//eva_num에 대한 eva_fav 총 개수 가져옴
			mapAjax.put("count", dao.selectEvafav1Count(eva_num)); //변경된 count를 다시
		}
		//JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
