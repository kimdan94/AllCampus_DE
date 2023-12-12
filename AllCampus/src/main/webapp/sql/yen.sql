-- 공지사항 
create table all_notice(
 notice_num number not null,
 notice_filename varchar2(150) not null,
 notice_title varchar2(150) not null,
 notice_content clob not null,
 notice_reg_date date default sysdate not null,
 notice_modify_date date default sysdate not null,
 constraint all_notice_pk1 primary key (notice_num)
);

create sequence all_notice_seq;

-- 공지사항 좋아요
create table all_notice_fav(
 notice_num number not null,
 mem_num number not null,
 constraint all_notice_fav_pk1 primary key (notice_num),
 constraint all_notice_fav_fk1 foreign key (notice_num) references all_notice (notice_num),
 constraint all_notice_fav_fk2 foreign key (mem_num) references all_member (mem_num)
);

-- FAQ
create table all_question(
 mem_num number not null,
 question_board_ask number not null,
 question_filename varchar2(50) not null,
 question_board_email varchar2(30) not null,
 question_content varchar2(30) not null,
 question_title varchar2(30) not null,
 constraint all_question_pk1 primary key (mem_num),
 constraint all_question_fk1 foreign key (mem_num) references all_question (mem_num)
 );
 
 create sequence all_question_seq;