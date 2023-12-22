package kr.calculator.vo;

import kr.timetable.vo.TimetableVO;

public class CalculatorVO {
	private int mem_num; 				//회원번호  	FK
	private String cal_semester;		//학년학기
	private String cal_course_name;		//강의명
	private int cal_credit;				//강의 수강 학점(1~4학점)
	private double cal_grade;			//성적
	private int cal_major;				//전공여부
	
	private TimetableVO timetableVO;    //시간표 VO
	
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getCal_semester() {
		return cal_semester;
	}
	public void setCal_semester(String cal_semester) {
		this.cal_semester = cal_semester;
	}
	public String getCal_course_name() {
		return cal_course_name;
	}
	public void setCal_course_name(String cal_course_name) {
		this.cal_course_name = cal_course_name;
	}
	public int getCal_credit() {
		return cal_credit;
	}
	public void setCal_credit(int cal_credit) {
		this.cal_credit = cal_credit;
	}
	public double getCal_grade() {
		return cal_grade;
	}
	public void setCal_grade(double cal_grade) {
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
