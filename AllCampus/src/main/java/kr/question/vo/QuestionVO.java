package kr.question.vo;

public class QuestionVO {
	private int question_num;
	private int mem_num;
	private int question_board_ask;
	private String question_filename;
	private String question_board_email;
	private String question_content;
	private String question_title;
	
	public int getQuestion_num() {
		return question_num;
	}
	public void setQuestion_num(int question_num) {
		this.question_num = question_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getQuestion_board_ask() {
		return question_board_ask;
	}
	public void setQuestion_board_ask(int question_board_ask) {
		this.question_board_ask = question_board_ask;
	}
	public String getQuestion_filename() {
		return question_filename;
	}
	public void setQuestion_filename(String question_filename) {
		this.question_filename = question_filename;
	}
	public String getQuestion_board_email() {
		return question_board_email;
	}
	public void setQuestion_board_email(String question_board_email) {
		this.question_board_email = question_board_email;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
}
