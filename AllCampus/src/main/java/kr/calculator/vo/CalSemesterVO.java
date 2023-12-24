package kr.calculator.vo;

public class CalSemesterVO {
	private int mem_num; 				//회원번호  	FK
	private String cal_semester;		//학년학기
	private double cal_avgscore;		//평점 
	private double cal_majorscore;		//전공 점수
	private int cal_acq;				//총 취득 학점
	private int cal_finclude_acq; 		//F등급 포함한 한 학기 당 취득 점수
	private int cal_majorf_acq;			//F등급 포함한 한 학기 당 전공 취득 점수
	
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
	public double getCal_avgscore() {
		return cal_avgscore;
	}
	public void setCal_avgscore(double cal_avgscore) {
		this.cal_avgscore = cal_avgscore;
	}
	public double getCal_majorscore() {
		return cal_majorscore;
	}
	public void setCal_majorscore(double cal_majorscore) {
		this.cal_majorscore = cal_majorscore;
	}
	public int getCal_acq() {
		return cal_acq;
	}
	public void setCal_acq(int cal_acq) {
		this.cal_acq = cal_acq;
	}
	public int getCal_finclude_acq() {
		return cal_finclude_acq;
	}
	public void setCal_finclude_acq(int cal_finclude_acq) {
		this.cal_finclude_acq = cal_finclude_acq;
	}
	public int getCal_majorf_acq() {
		return cal_majorf_acq;
	}
	public void setCal_majorf_acq(int cal_majorf_acq) {
		this.cal_majorf_acq = cal_majorf_acq;
	}
}
