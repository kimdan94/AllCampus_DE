package kr.board.vo;

import java.sql.Date;

public class BoardVO {
	private int board_num;  	//글번호
	private String board_title;		//제목
	private String board_content;		//내용
	private int board_hit;			//조회수
	private Date board_reg_date;		//등록일
	private Date board_modify_date;	//수정일
	private String board_filename;	//파일명
	private String board_ip;			//ip주소
	private int board_anonymous; 		//익명 여부  1: 익명X 2: 익명
	private int board_complaint;		//신고수
	private int board_show;				//표시 여부
	private int mem_num;		//회원번호
	
	private String mem_id;			//회원 아이디
	private String mem_photo;		//회원 프로필 사진
	private String mem_nick;		//회원닉네임
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public int getBoard_hit() {
		return board_hit;
	}
	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
	}
	public Date getBoard_reg_date() {
		return board_reg_date;
	}
	public void setBoard_reg_date(Date board_reg_date) {
		this.board_reg_date = board_reg_date;
	}
	public Date getBoard_modify_date() {
		return board_modify_date;
	}
	public void setBoard_modify_date(Date board_modify_date) {
		this.board_modify_date = board_modify_date;
	}
	public String getBoard_filename() {
		return board_filename;
	}
	public void setBoard_filename(String board_filename) {
		this.board_filename = board_filename;
	}
	public String getBoard_ip() {
		return board_ip;
	}
	public void setBoard_ip(String board_ip) {
		this.board_ip = board_ip;
	}
	public int getBoard_anonymous() {
		return board_anonymous;
	}
	public void setBoard_anonymous(int board_anonymous) {
		this.board_anonymous = board_anonymous;
	}
	public int getBoard_complaint() {
		return board_complaint;
	}
	public void setBoard_complaint(int board_complaint) {
		this.board_complaint = board_complaint;
	}
	public int getBoard_show() {
		return board_show;
	}
	public void setBoard_show(int board_show) {
		this.board_show = board_show;
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
	public String getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
}
