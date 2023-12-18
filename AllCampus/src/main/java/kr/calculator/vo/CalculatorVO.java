package kr.calculator.vo;

import kr.timetable.vo.TimetableVO;

public class CalculatorVO {
	private int mem_num; 		//회원번호  	FK
	private int course_num; 	//강의번호		FK
	private String cal_grade;	//성적
	private int cal_major;		//전공여부
	
	private TimetableVO timetableVO;    //시간표 VO
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getCourse_num() {
		return course_num;
	}
	public void setCourse_num(int course_num) {
		this.course_num = course_num;
	}
	public String getCal_grade() {
		return cal_grade;
	}
	public void setCal_grade(String cal_grade) {
		this.cal_grade = cal_grade;
	}
	public int getCal_major() {
		return cal_major;
	}
	public void setCal_major(int cal_major) {
		this.cal_major = cal_major;
	}
	
	public TimetableVO getTimetableVO() {
		return timetableVO;
	}
	public void setTimetableVO(TimetableVO timetableVO) {
		this.timetableVO = timetableVO;
	}
	
	
}
