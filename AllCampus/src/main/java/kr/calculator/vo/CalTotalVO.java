package kr.calculator.vo;

public class CalTotalVO {
	private int mem_num; 				//회원번호  	FK
	private double cal_total_avgscore;	//전체평점 
	private double cal_total_majorscore;//전공평점
	private int cal_total_acq;			//취득학점
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public double getCal_total_avgscore() {
		return cal_total_avgscore;
	}
	public void setCal_total_avgscore(double cal_total_avgscore) {
		this.cal_total_avgscore = cal_total_avgscore;
	}
	public double getCal_total_majorscore() {
		return cal_total_majorscore;
	}
	public void setCal_total_majorscore(double cal_total_majorscore) {
		this.cal_total_majorscore = cal_total_majorscore;
	}
	public int getCal_total_acq() {
		return cal_total_acq;
	}
	public void setCal_total_acq(int cal_total_acq) {
		this.cal_total_acq = cal_total_acq;
	}
}
