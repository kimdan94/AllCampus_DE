package kr.secondhand.vo;

public class SecondhandReplyVO {
	private int reply_num;//댓글 번호
	private String reply_content;//내용
	private String reply_date;//작성일
	private String reply_modifydate;//수정일
	private String reply_ip;//아이피 주소
	private int secondhand_num;//판매글 번호
	private int mem_num;//작성자 회원번호
	private String mem_id;//작성자 아이디
	
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_date() {
		return reply_date;
	}
	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}
	public String getReply_modifydate() {
		return reply_modifydate;
	}
	public void setReply_modifydate(String reply_modifydate) {
		this.reply_modifydate = reply_modifydate;
	}
	public String getReply_ip() {
		return reply_ip;
	}
	public void setReply_ip(String reply_ip) {
		this.reply_ip = reply_ip;
	}
	public int getSecondhand_num() {
		return secondhand_num;
	}
	public void setSecondhand_num(int secondhand_num) {
		this.secondhand_num = secondhand_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}	
}
