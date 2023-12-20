package kr.friend.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.friend.dao.FriendDAO;
import kr.timetable.dao.TimetableDAO;
import kr.timetable.vo.TimetableVO;

public class FriendTimetableListAction implements Action { 

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int year = 2023;
		int semester = 1;
		
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		Integer friend_num = Integer.parseInt(request.getParameter("friend_num"));
//		System.out.println("친구 번호는? " + friend_num);
		
		
		FriendDAO daoFriend = FriendDAO.getInstance();
		List<TimetableVO> friendTableList = daoFriend.getFriendTimetableList(friend_num, year, semester);
		
//		mapAjax.put("friendTableList", friendTableList); 
		
//------------------------------------------------------------------
		TimetableDAO daoTime = TimetableDAO.getInstance();
		List<TimetableVO> monList = daoTime.getListPrint(friend_num,year,semester,1); // 월
		List<TimetableVO> tueList = daoTime.getListPrint(friend_num,year,semester,2); // 화
		List<TimetableVO> wedList = daoTime.getListPrint(friend_num,year,semester,3); // 수
		List<TimetableVO> thurList = daoTime.getListPrint(friend_num,year,semester,4); // 목
		List<TimetableVO> friList = daoTime.getListPrint(friend_num,year,semester,5); // 금
		mapAjax.put("monList", monList); 
		mapAjax.put("tueList", tueList); 
		mapAjax.put("wedList", wedList); 
		mapAjax.put("thurList", thurList); 
		mapAjax.put("friList", friList);
		
		List<TimetableVO> listMON = daoTime.getListCourseCode(friend_num, year, semester, 1);
		List<TimetableVO> listTUE = daoTime.getListCourseCode(friend_num, year, semester, 2);
		List<TimetableVO> listWED = daoTime.getListCourseCode(friend_num, year, semester, 3);
		List<TimetableVO> listTHUR = daoTime.getListCourseCode(friend_num, year, semester, 4);
		List<TimetableVO> listFRI = daoTime.getListCourseCode(friend_num, year, semester, 5);
		mapAjax.put("listMON", listMON);
		mapAjax.put("listTUE", listTUE);
		mapAjax.put("listWED", listWED);
		mapAjax.put("listTHUR", listTHUR);
		mapAjax.put("listFRI", listFRI);
		
		
//-----------------------------------------------------------------
		
		//JSON 문자열 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
		
	}

}
