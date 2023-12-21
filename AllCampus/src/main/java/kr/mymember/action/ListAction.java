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
		if (pageNum == null)
			pageNum = "1";

		int count = dao.getBoardCount(user_num);
		PageUtil page = new PageUtil(Integer.parseInt(pageNum), count, 3, 10, "list.do");
		List<BoardVO> list = null;
		if (count > 0) {
			list = dao.getListBoard(page.getStartRow(), page.getEndRow(), user_num);
		}
		
		//all_secondhand
		String secondPageNum = request.getParameter("secondPageNum");
		if (secondPageNum == null)
			secondPageNum = "1";
		
		int count2 = dao.getSecondCount(user_num);
		PageUtil page2 = new PageUtil(Integer.parseInt(secondPageNum), count2, 3, 10, "list.do");
		List<SecondhandVO> list2 = null;
		if (count2 > 0) {
			list2 = dao.getListSecond(page.getStartRow(), page.getEndRow(), user_num);
		}
		
		//all_course_eva
		String thirdPageNum = request.getParameter("thirdPageNum");
		if (thirdPageNum == null)
			thirdPageNum = "1";
		
		int count3 = dao.getCourse(user_num);
		PageUtil page3 = new PageUtil(Integer.parseInt(thirdPageNum), count3, 3, 10, "list.do");
		List<CourseEvaVO> list3 = null;
		if (count3 > 0) {
			list3 = dao.getListCourse(page.getStartRow(), page.getEndRow(), user_num);
		}

		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		request.setAttribute("page", page.getPage());
		request.setAttribute("page2", page2.getPage());
		request.setAttribute("page3", page3.getPage());

		// JSP 경로 반환
		return "/WEB-INF/views/mymember/list.jsp";
	}
}
