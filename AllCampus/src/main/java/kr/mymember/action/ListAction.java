package kr.mymember.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.courseeva.vo.CourseEvaVO;
import kr.mymember.dao.MyMemberDAO;
import kr.secondhand.vo.SecondhandVO;
import kr.util.PageUtil;
import kr.util.PageUtil2;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer) session.getAttribute("user_num");
		if (user_num == null) {// 로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		// 로그인 된 경우
		MyMemberDAO dao = MyMemberDAO.getInstance();

		//all_board
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) pageNum = "1";
		//all_secondhand
		String secondPageNum = request.getParameter("pageNum2");
		if (secondPageNum == null) secondPageNum = "1";
		//all_course_eva
		String thirdPageNum = request.getParameter("pageNum3");
		if (thirdPageNum == null)thirdPageNum = "1";
		
		int count = dao.getBoardCount(user_num);
		int count2 = dao.getSecondCount(user_num);
		int count3 = dao.getCourse(user_num);
		PageUtil2 page = new PageUtil2(Integer.parseInt(pageNum),Integer.parseInt(secondPageNum),Integer.parseInt(thirdPageNum), count,count2,count3, 3, 10, "list.do");
		List<BoardVO> list = null;
		if (count > 0) {
			list = dao.getListBoard(page.getStartRow(), page.getEndRow(), user_num);
		}

		PageUtil2 page2 = new PageUtil2(Integer.parseInt(pageNum),Integer.parseInt(secondPageNum),Integer.parseInt(thirdPageNum), count,count2,count3, 3, 10, "list.do");
		List<SecondhandVO> list2 = null;
		if (count2 > 0) {
			list2 = dao.getListSecond(page.getStartRow2(), page.getEndRow2(), user_num);
		}

		PageUtil2 page3 = new PageUtil2(Integer.parseInt(pageNum),Integer.parseInt(secondPageNum),Integer.parseInt(thirdPageNum), count,count2,count3, 3, 10, "list.do");
		List<CourseEvaVO> list3 = null;
		if (count3 > 0) {
			list3 = dao.getListCourse(page.getStartRow3(), page.getEndRow3(), user_num);
		}

		request.setAttribute("count", count);
		request.setAttribute("count2", count2);
		request.setAttribute("count3", count3);
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		request.setAttribute("page", page.getPage());
		request.setAttribute("page2", page2.getPage2());
		request.setAttribute("page3", page3.getPage3());

		// JSP 경로 반환
		return "/WEB-INF/views/mymember/list.jsp";
	}
}
