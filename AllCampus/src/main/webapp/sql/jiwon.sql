--게시판
create table all_board(
 board_num number not null,
 board_title varchar2(150) not null,
 board_content clob not null,
 board_hit number(9) default 0 not null,
 board_reg_date date default sysdate not null,
 board_modify_date date,
 board_filename varchar2(150),
 board_ip varchar2(40) not null,
 board_anonymous number(1) not null,  --익명 여부 
 board_complaint number(9) not null,
 board_show number(1) not null,
 mem_num number not null,				--FK
 constraint all_board_pk primary key (board_num),
 constraint all_board_fk foreign key (mem_num) 
                             references zmember (mem_num)
);
create sequence all_board_seq;

--게시판 좋아요
create table all_board_fav(
 board_num number not null,
 mem_num number not null,
 constraint all_board_fav_fk1 foreign key (board_num)
                                  references zboard (board_num),
 constraint all_board_fav_fk2 foreign key (mem_num)
                                  references zmember (mem_num)
);

--게시판 좋아요
create table all_board_scrap(
 board_num number not null,
 mem_num number not null,
 constraint all_board_scrap_fk1 foreign key (board_num)
                                  references zboard (board_num),
 constraint all_board_scrap_fk2 foreign key (mem_num)
                                  references zmember (mem_num)
);