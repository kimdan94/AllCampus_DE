-- 강의 테이블
create table all_course(
 course_num number not null,
 course_name varchar2(60) not null,
 course_prof varchar2(20) not null,
 course_year number not null,
 course_semester number not null,
 course_subject varchar2(40) not null,
 course_day number not null,
 course_start_time varchar2(20) not null,
 course_end_time varchar2(20) not null,
 course_category number not null,
 course_credit number not null,
 course_classroom varchar2(20),
 course_code varchar2(60) not null,
 course_count number not null,
 univ_num number not null,
 constraint all_course_pk primary key(course_num),
 constraint all_course_fk1 foreign key (univ_num) references all_member_univ (univ_num)
);
create sequence all_course_seq;

-- 시간표 테이블
create table all_timetable(
 mem_num number not null,
 course_code varchar2(60) not null,
 timetable_year number not null,
 timetable_semester number not null,
 timetable_course_name varchar2(60) not null,
 timetable_course_prof varchar2(20) not null, 
 timetable_credit number not null,
 timetable_table_id varchar2(60) not null,
 timetable_color varchar2(60) default 'red' not null
);
create sequence all_timetable_seq; -- 삭제하기

create table all_friend(
 mem_num number not null, -- 회원 번호
 friend_num number not null, --  친구 회원 번호
 constraint all_friend_fk1 foreign key (mem_num) references all_member (mem_num)
);