package kr.courseeva.vo;

public class CourseEvaWarnVO {
	private int eva_num;
	private int mem_num;
	
	public CourseEvaWarnVO() {}
	
	public CourseEvaWarnVO(int eva_num,int mem_num) {
		this.eva_num = eva_num;
		this.mem_num = mem_num;
	}

	public int getEva_num() {
		return eva_num;
	}

	public void setEva_num(int eva_num) {
		this.eva_num = eva_num;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	
}
