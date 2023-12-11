package kr.member.vo;

import java.sql.Date;

public class MemberVO {
	private int mem_num;//회원번호
	private String mem_id;//회원 id
	private int mem_auth;//회원 등급
	private String mem_nick;//회원 닉네임
	private String mem_name;//회원 이름
	private String mem_passwd;//회원 비밀번호
	private String mem_email;
	private int mem_univNum;//회원 학번
	private String mem_major;//회원 전공
	private Date mem_reg_date;//회원 가입일
	private Date mem_certify;//회원 학교 인증일
	private String mem_photo;//회원 프로필사진
	private int univ_num;//학교번호
	private String univ_name;//학교 이름
	
	//비밀번호 일치 여부 체크
	public boolean isCheckedPassword(String userPasswd) {
		//회원 등급(auth) : 0 탈퇴, 1 정지, 2 비인증, 3 인증, 9 관리
		if(mem_auth > 1 && mem_passwd.equals(userPasswd)) {
			return true;
		}
		return false;
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

	public int getMem_auth() {
		return mem_auth;
	}

	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_passwd() {
		return mem_passwd;
	}

	public void setMem_passwd(String mem_passwd) {
		this.mem_passwd = mem_passwd;
	}
	
	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public int getMem_univNum() {
		return mem_univNum;
	}

	public void setMem_univNum(int mem_univNum) {
		this.mem_univNum = mem_univNum;
	}

	public String getMem_major() {
		return mem_major;
	}

	public void setMem_major(String mem_major) {
		this.mem_major = mem_major;
	}

	public Date getMem_reg_date() {
		return mem_reg_date;
	}

	public void setMem_reg_date(Date mem_reg_date) {
		this.mem_reg_date = mem_reg_date;
	}

	public Date getMem_certify() {
		return mem_certify;
	}

	public void setMem_certify(Date mem_certify) {
		this.mem_certify = mem_certify;
	}

	public String getMem_photo() {
		return mem_photo;
	}

	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
	}

	public int getUniv_num() {
		return univ_num;
	}

	public void setUniv_num(int univ_num) {
		this.univ_num = univ_num;
	}

	public String getUniv_name() {
		return univ_name;
	}

	public void setUniv_name(String univ_name) {
		this.univ_name = univ_name;
	}
}
 