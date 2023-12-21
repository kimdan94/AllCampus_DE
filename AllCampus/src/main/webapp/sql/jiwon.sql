--게시판1
create table all_board(
 board_num number not null,    --PK
 board_title varchar2(150) not null,
 board_content clob not null,
 board_hit number(9) default 0 not null,
 board_reg_date date default sysdate not null,
 board_modify_date date,
 board_filename varchar2(150),
 board_ip varchar2(40) not null,
 board_anonymous number(1) not null,  --익명 여부  1: 익명X 2: 익명
 board_complaint number(9) default 0 not null,
 board_show number(1) default 2 not null,  -- 표시 여부 : 1 미표시, 2 표시
 mem_num number not null,				--FK
 constraint all_board_pk primary key (board_num),
 constraint all_board_fk foreign key (mem_num) 
                             references all_member (mem_num)
);
create sequence all_board_seq;

--게시판 좋아요
create table all_board_fav(
 board_num number not null,   --FK
 mem_num number not null,     --FK
 constraint all_board_fav_fk1 foreign key (board_num)
                                  references all_board (board_num),
 constraint all_board_fav_fk2 foreign key (mem_num)
                                  references all_member (mem_num)
);

--게시판 스크랩
create table all_board_scrap(
 board_num number not null,   --FK
 mem_num number not null,    --FK
 constraint all_board_scrap_fk1 foreign key (board_num)
                                  references all_board (board_num),
 constraint all_board_scrap_fk2 foreign key (mem_num)
                                  references all_member (mem_num)
);

--게시판 신고
create table all_board_warn(
 board_num number not null,  --FK
 mem_num number not null,   --FK
 constraint all_board_warn_fk1 foreign key (board_num)
                                  references all_board (board_num),
 constraint all_board_warn_fk2 foreign key (mem_num)
                                  references all_member (mem_num)
);

--게시판 댓글
create table all_board_reply(
 re_num number not null,    --PK
 re_content varchar2(900) not null,
 re_date date default sysdate not null,
 re_modifydate date,
 re_ip varchar2(40) not null,
 re_anonymous number(1) not null,   --익명 여부  1: 익명X 2: 익명
 board_num number not null,   --FK
 mem_num number not null,     --FK
 constraint all_board_reply_pk primary key (re_num),
 constraint all_board_reply_fk1 foreign key (board_num) references all_board (board_num),
 constraint all_board_reply_fk2 foreign key (mem_num) references all_member (mem_num)
);
create sequence all_board_reply_seq;


-------------------------------------------------

--강의평 
create table all_course_eva(
 eva_num number not null,     --PK
 course_num number not null,   --FK
 mem_num number not null,   -- FK
 eva_star number(9,2) not null,
 eva_content clob not null,
 eva_fav number(9) default 0 not null,
 eva_complaint number(9) default 0 not null,
 eva_show number(1) default 2 not null,      -- 표시 여부 : 1 미표시, 2 표시
 eva_semester varchar2(50) not null,
 eva_reg_date date default sysdate not null,
 constraint all_course_eva_pk primary key (eva_num),
 constraint all_course_eva_fk1 foreign key (course_num) references all_course (course_num),
 constraint all_course_eva_fk2 foreign key (mem_num) references all_member (mem_num)
);
create sequence all_eva_seq;


-- 강의평 추천(좋아요)
create table all_eva_fav(
 eva_num number not null,   -- FK
 mem_num number not null,   -- FK
 constraint all_eva_fav_fk1 foreign key (eva_num)
                                  references all_course_eva (eva_num),
 constraint all_eva_fav_fk2 foreign key (mem_num)
                                  references all_member (mem_num)
);

-- 강의평 신고
create table all_eva_warn( 
 eva_num number not null,  -- FK
 mem_num number not null,  -- FK
 constraint all_eva_warn_fk1 foreign key (eva_num)
                                  references all_course_eva (eva_num),
 constraint all_eva_warn_fk2 foreign key (mem_num)
                                  references all_member (mem_num)
);

------------------------------
create table all_calculator(
 mem_num number not null,      --FK
 cal_semester varchar2(30) not null,
 cal_course_name varchar2(60) not null,
 cal_credit number not null,
 cal_grade number(9,2) not null,
 cal_major number(1) not null,
 constraint all_calculator_fk1 foreign key (mem_num) references all_member (mem_num)
);

create table all_calculator_semester(
 mem_num number not null,      --FK
 cal_semester varchar2(30) not null,
 cal_avgscore number(9,2) default 0 not null,
 cal_majorscore number(9,2) default 0 not null,
 cal_acq number default 0 not null,
 cal_finclude_acq not null,
 cal_majorf_acq not null,
 constraint all_calculator__semester_fk foreign key (mem_num) references all_member (mem_num)
);

create table all_calculator_total(
 mem_num number not null,      --FK
 cal_total_avgscore number(9,2) default 0 not null,
 cal_total_majorscore number(9,2) default 0 not null,
 cal_total_acq number default 0 not null,
 constraint all_calculator_total_fk foreign key (mem_num) references all_member (mem_num)
);