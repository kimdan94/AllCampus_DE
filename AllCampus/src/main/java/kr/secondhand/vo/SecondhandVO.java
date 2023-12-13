package kr.secondhand.vo;

import java.sql.Date;

public class SecondhandVO {
	private int secondhand_num;//판매글 번호
	private String secondhand_name;//판매 교재명
	private String secondhand_writer;//판매 교재 저자
	private String secondhand_company;//출판사
	private String secondhand_content;//내용
	private int secondhand_price;//판매가
	private String secondhand_way;//판매 방법
	private String secondhand_filename;//판매 교재 이미지
	private int secondhand_complaint;//신고수
	private int secondhand_show;//게시글 표시 여부
	private Date secondhand_reg_date;//등록일
	private Date secondhand_modifydate;//수정일
	private int secondhand_sell;//판매 여부
	private int mem_num;//회원번호
	private String mem_id;//아이디
	
	public int getSecondhand_num() {
		return secondhand_num;
	}
	public void setSecondhand_num(int secondhand_num) {
		this.secondhand_num = secondhand_num;
	}
	public String getSecondhand_name() {
		return secondhand_name;
	}
	public void setSecondhand_name(String secondhand_name) {
		this.secondhand_name = secondhand_name;
	}
	public String getSecondhand_writer() {
		return secondhand_writer;
	}
	public void setSecondhand_writer(String secondhand_writer) {
		this.secondhand_writer = secondhand_writer;
	}
	public String getSecondhand_company() {
		return secondhand_company;
	}
	public void setSecondhand_company(String secondhand_company) {
		this.secondhand_company = secondhand_company;
	}
	public String getSecondhand_content() {
		return secondhand_content;
	}
	public void setSecondhand_content(String secondhand_content) {
		this.secondhand_content = secondhand_content;
	}
	public int getSecondhand_price() {
		return secondhand_price;
	}
	public void setSecondhand_price(int secondhand_price) {
		this.secondhand_price = secondhand_price;
	}
	public String getSecondhand_way() {
		return secondhand_way;
	}
	public void setSecondhand_way(String secondhand_way) {
		this.secondhand_way = secondhand_way;
	}
	public String getSecondhand_filename() {
		return secondhand_filename;
	}
	public void setSecondhand_filename(String secondhand_filename) {
		this.secondhand_filename = secondhand_filename;
	}
	public int getSecondhand_complaint() {
		return secondhand_complaint;
	}
	public void setSecondhand_complaint(int secondhand_complaint) {
		this.secondhand_complaint = secondhand_complaint;
	}
	public int getSecondhand_show() {
		return secondhand_show;
	}
	public void setSecondhand_show(int secondhand_show) {
		this.secondhand_show = secondhand_show;
	}
	public Date getSecondhand_reg_date() {
		return secondhand_reg_date;
	}
	public void setSecondhand_reg_date(Date secondhand_reg_date) {
		this.secondhand_reg_date = secondhand_reg_date;
	}
	public Date getSecondhand_modifydate() {
		return secondhand_modifydate;
	}
	public void setSecondhand_modifydate(Date secondhand_modifydate) {
		this.secondhand_modifydate = secondhand_modifydate;
	}
	public int getSecondhand_sell() {
		return secondhand_sell;
	}
	public void setSecondhand_sell(int secondhand_sell) {
		this.secondhand_sell = secondhand_sell;
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
