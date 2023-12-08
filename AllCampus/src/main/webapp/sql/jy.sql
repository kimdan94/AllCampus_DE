create table all_member(
 mem_num number not null,
 mem_id varchar2(12) not null unique,
 mem_auth number(1) default 1 not null,-- 회원 등급 : 0 탈퇴, 1 비인증, 2 인증, 3 정지, 9 관리
 mem_nick varchar2(36) not null unique,
 constraint all_member_pk1 primary key (mem_num)
);
create sequence all_member_seq;

create table all_member_univ(
 univ_num number not null,
 univ_name varchar2(60) not null,
 constraint all_member_univ_pk1 primary key (univ_num)
);
create sequence all_univ_seq;

create table all_member_detail(
 mem_num number not null,
 mem_name varchar2(18) not null,
 mem_passwd varchar2(20) not null,
 mem_email varchar2(30) not null unique,
 univ_num number not null,
 mem_univNum number(7),
 mem_major varchar2(100),
 mem_reg_date date default sysdate not null,
 mem_certify date,
 mem_photo varchar2(150),
 constraint all_member_detail_pk1 primary key (mem_num),
 constraint all_member_detail_fk1 foreign key (mem_num) references all_member (mem_num),
 constraint all_member_detail_fk2 foreign key (univ_num) references all_member_univ (univ_num)
); 