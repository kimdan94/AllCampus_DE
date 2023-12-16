package kr.timetable.vo;

public class TimetableVO {
	private int mem_num;
	private int timetable_num;
	private int course_num;
	private int timetable_year;
	private int timetable_semester;
	private String timetable_course_name;
	private String timetable_course_code;
	private int timetable_day;
	private String timetable_start_time;
	private String timetable_end_time;
	private int timetable_credit;
	private String timetable_table_id;
	private String timetable_color;
	
	public String getTimetable_table_id() {
		return timetable_table_id;
	}
	public void setTimetable_table_id(String timetable_table_id) {
		this.timetable_table_id = timetable_table_id;
	}
	public String getTimetable_color() {
		return timetable_color;
	}
	public void setTimetable_color(String timetable_color) {
		this.timetable_color = timetable_color;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getTimetable_num() {
		return timetable_num;
	}
	public void setTimetable_num(int timetable_num) {
		this.timetable_num = timetable_num;
	}
	public int getCourse_num() {
		return course_num;
	}
	public void setCourse_num(int course_num) {
		this.course_num = course_num;
	}
	public int getTimetable_year() {
		return timetable_year;
	}
	public void setTimetable_year(int timetable_year) {
		this.timetable_year = timetable_year;
	}
	public int getTimetable_semester() {
		return timetable_semester;
	}
	public void setTimetable_semester(int timetable_semester) {
		this.timetable_semester = timetable_semester;
	}
	public String getTimetable_course_name() {
		return timetable_course_name;
	}
	public void setTimetable_course_name(String timetable_course_name) {
		this.timetable_course_name = timetable_course_name;
	}
	public String getTimetable_course_code() {
		return timetable_course_code;
	}
	public void setTimetable_course_code(String timetable_course_code) {
		this.timetable_course_code = timetable_course_code;
	}
	public int getTimetable_day() {
		return timetable_day;
	}
	public void setTimetable_day(int timetable_day) {
		this.timetable_day = timetable_day;
	}
	public String getTimetable_start_time() {
		return timetable_start_time;
	}
	public void setTimetable_start_time(String timetable_start_time) {
		this.timetable_start_time = timetable_start_time;
	}
	public String getTimetable_end_time() {
		return timetable_end_time;
	}
	public void setTimetable_end_time(String timetable_end_time) {
		this.timetable_end_time = timetable_end_time;
	}
	public int getTimetable_credit() {
		return timetable_credit;
	}
	public void setTimetable_credit(int timetable_credit) {
		this.timetable_credit = timetable_credit;
	}
}
