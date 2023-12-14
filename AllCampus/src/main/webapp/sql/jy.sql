-- 회원 기본 정보
create table all_member(
 mem_num number not null,
 mem_id varchar2(12) not null unique,
 mem_auth number(1) default 2 not null,-- 회원 등급 : 0 탈퇴, 1 정지, 2 비인증, 3 인증, 9 관리
 mem_nick varchar2(36) not null unique,
 mem_email varchar2(30) not null unique,
 constraint all_member_pk1 primary key (mem_num)
);
-- 학교
create sequence all_member_seq;

create table all_member_univ(
 univ_num number not null,
 univ_name varchar2(60) not null,
 constraint all_member_univ_pk1 primary key (univ_num)
);

create sequence all_univ_seq;

-- 회원 상세 정보
create table all_member_detail(
 mem_num number not null,
 mem_name varchar2(18) not null,
 mem_passwd varchar2(20) not null,
 univ_num number not null,
 mem_univNum number(9) not null,
 mem_major varchar2(100) not null,
 mem_reg_date date default sysdate not null,
 mem_certify date,
 mem_certifyFilename varchar2(150),
 mem_photo varchar2(150),
 constraint all_member_detail_pk1 primary key (mem_num),
 constraint all_member_detail_fk1 foreign key (mem_num) references all_member (mem_num),
 constraint all_member_detail_fk2 foreign key (univ_num) references all_member_univ (univ_num)
);

-- 중고 거래
create table all_secondhand(
 secondhand_num number not null,
 mem_num number not null,
 secondhand_name varchar2(120) not null,
 secondhand_writer varchar2(60) not null,
 secondhand_company varchar2(30) not null,
 secondhand_content clob not null,
 secondhand_price number(6) not null,
 secondhand_way varchar2(16) not null,
 secondhand_ip varchar2(40) not null,
 secondhand_status varchar2(3) not null,
 secondhand_filename varchar2(150) not null,
 secondhand_openchat varchar2(200) not null,
 secondhand_complaint number(9) default 0 not null,
 secondhand_show number(1) default 2 not null, -- 표시 여부 : 1 미표시, 2 표시
 secondhand_reg_date date default sysdate not null,
 secondhand_modifydate date,
 secondhand_sell number(1) default 2 not null, -- 표시 여부 : 1 미표시, 2 표시
 constraint all_secondhand_pk1 primary key (secondhand_num),
 constraint all_secondhand_fk1 foreign key (mem_num) references all_member (mem_num)
);

create sequence all_secondhand_seq;

-- 중고 거래 댓글
create table all_secondhand_reply(
 secondhand_num number not null,
 mem_num number not null,
 reply_num number not null,
 reply_content varchar2(900) not null,
 reply_date date default sysdate not null,
 repy_modifydate date,
 reply_ip varchar2(40) not null,
 constraint all_secondhand_reply_fk1 foreign key (secondhand_num) references all_secondhand (secondhand_num),
 constraint all_secondhand_reply_fk2 foreign key (mem_num) references all_member (mem_num),
 constraint all_secondhand_reply_pk1 primary key (reply_num)
);

create sequence all_resecondhand_seq;

-- 중고 거래 신고
create table all_secondhand_warn(
 secondhand_num number not null,
 mem_num number not null,
 constraint all_secondhand_warn_fk1 foreign key (secondhand_num) references all_secondhand (secondhand_num),
 constraint all_secondhand_warn_fk2 foreign key (mem_num) references all_member (mem_num)
);