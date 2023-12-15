package kr.courseeva.vo;

import kr.course.vo.CourseVO;


public class CourseEvaVO {
	private int eva_num;			//강의평 글번호	--PK
	private int course_num;			//강의번호  	--FK
	private int mem_num;			//회원번호		--FK
	private double eva_star;			//별점     //double로 받아오기
	private String eva_content;		//강의평 내용
	private int eva_complaint;		//강의평 게시글 신고수
	private int eva_show;			//게시글 표시 여부     1 미표시, 2 표시
	private String eva_semester;
	
	private CourseVO courseVO;
	
	public int getEva_num() {
		return eva_num;
	}
	public void setEva_num(int eva_num) {
		this.eva_num = eva_num;
	}
	public int getCourse_num() {
		return course_num;
	}
	public void setCourse_num(int course_num) {
		this.course_num = course_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public double getEva_star() {
		return eva_star;
	}
	public void setEva_star(double eva_star) {
		this.eva_star = eva_star;
	}
	public String getEva_content() {
		return eva_content;
	}
	public void setEva_content(String eva_content) {
		this.eva_content = eva_content;
	}
	public int getEva_complaint() {
		return eva_complaint;
	}
	public void setEva_complaint(int eva_complaint) {
		this.eva_complaint = eva_complaint;
	}
	public int getEva_show() {
		return eva_show;
	}
	public void setEva_show(int eva_show) {
		this.eva_show = eva_show;
	}
	public String getEva_semester() {
		return eva_semester;
	}
	public void setEva_semester(String eva_semester) {
		this.eva_semester = eva_semester;
	}
	public CourseVO getCourseVO() {
		return courseVO;
	}
	public void setCourseVO(CourseVO courseVO) {
		this.courseVO = courseVO;
	}
	
}
