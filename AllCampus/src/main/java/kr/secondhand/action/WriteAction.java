package kr.secondhand.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.secondhand.dao.SecondhandDAO;
import kr.secondhand.vo.SecondhandVO;
import kr.util.FileUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		SecondhandVO sc = new SecondhandVO();
		sc.setSecondhand_name(multi.getParameter("bookName"));
		sc.setSecondhand_writer(multi.getParameter("bookWriter"));
		sc.setSecondhand_writerPlus(multi.getParameter("bookWriterPlus"));
		sc.setSecondhand_content(multi.getParameter("bookContent"));
		sc.setSecondhand_company(multi.getParameter("bookCompany"));
		sc.setSecondhand_price(Integer.parseInt(multi.getParameter("bookPrice")));
		sc.setSecondhand_way(multi.getParameter("bookWay"));
		sc.setSecondhand_status(multi.getParameter("bookStatus"));
		sc.setSecondhand_filename(multi.getFilesystemName("bookImage"));
		sc.setSecondhand_openchat(multi.getParameter("bookUrl"));
		sc.setSecondhand_ip(request.getRemoteAddr());
		sc.setMem_num(user_num);
		
		SecondhandDAO dao = SecondhandDAO.getInstance();
		dao.insertSC(sc);
		
		//JSP 경로 반환
		return "/WEB-INF/views/secondhand/sc_write.jsp";
	}

}
