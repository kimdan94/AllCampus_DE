<h1 align="left">All Campus Project</h1>

![image](https://github.com/kimdan94/ZIBI_DE/assets/123554665/54bdf3db-393c-4055-b5b4-6e08a9fe8f28)

###
- 프로젝트 기간 : 2023년 11월 27일 ~ 2023년 12월 27일

<h3 align="left">1. 기획 스케치</h3>
<hr>

<h4 align="left">1) 기획 의도</h4>

###

<p align="left">
하나의 사이트만으로 대학 생활을 마스터 할 수 있도록 다양한 정보를 제공함은 물론,<br>
  '우리가 만들어가는, 모두를 위한 대학생활'이라는 브랜드 슬로건에 따라 <b>학생들이 주도하는 대학 커뮤니티로서 학생들이 안전하게 대화를 나눌 수 있는 장</b>을 마련하고자 올캠퍼스를 만들었습니다.

###

<h4 align="left">2) 기획 단계</h4>

- **요구사항 정의서**
  - Word를 활용하여 구현하려는 기능들을 기반으로 웹사이트에 필요한 기능을 구체적으로 나열했습니다
 <br>
    
  ![image](https://github.com/kimdan94/ZIBI_DE/assets/123554665/4eabaae6-5d68-463b-b428-f9033846ba6b)


- **ERD 작성**
  - [ERDCloud](https://www.erdcloud.com/)을 활용하여 구현하려는 기능들을 기반으로 데이터를 구조화하였습니다.
  <br>
  
![image](https://github.com/kimdan94/ZIBI_DE/assets/123554665/0a3d9615-301e-4cae-b3a4-4908a8a8a406)



<h4 align="left">3) 협업 과정</h4>

- **디스코드를 활용해 중요 이슈를 공유하고, 테이블 및 개인 기능의 변동사항 또한 공유했습니다.**
- **에러 없이 안전하게 돌아가는 기능에 한해 풀 리퀘를 하기로 정해두었습니다.**

<hr>

<h3 align="left">2. 프로젝트 소개</h3>

<h4 align="left">1) 개발 환경</h4>

![image](https://github.com/kimdan94/ZIBI_DE/assets/123554665/c5cd372e-3b76-414b-a100-e01e63fea0ca)


<h4 align="left">2) 실행화면</h4>

<b>마이페이지 메인 페이지</b>

![image](https://github.com/kimdan94/ZIBI_DE/assets/123554665/7d7b5d37-768b-4a6e-a3b3-0fd5df5fb969)<br>



- **가입 회원의 회원 정보 노출**

- **변경 불가능한 ID 제외, 각 영역 클릭 시 해당 페이지로 이동**

<br>
<b>학교 인증 페이지</b>
<p></p>

![image](https://github.com/kimdan94/ZIBI_DE/assets/123554665/3e614424-5427-49bb-80ad-9510e4044e1e)
<br>

- **파일 첨부, 약관 동의 입력 유효성 체체크**

- **사이드바 - 마이페이지 서비스 바로가기 가능**
<br>

<b>내 정보 변경 페이지</b>

![image](https://github.com/kimdan94/ZIBI_DE/assets/123554665/3b83d068-068f-4717-ab3f-8400a941e41b)
<br>

- **ajax 통신 : 프로필 이미지 변경**

- **닉네임 변경 : 중복 체크 버튼**
  - 중복 여부 문구 명시
  - 정규표현식 처리

- **학과 설정 : 입력 유효성 체크**
  - 필수 변경인 주전공만 체크, 전공 추가는 선택 사항으로 처리

<br>

<b>비빌번호 변경 페이지</b>
<p></p>

![image](https://github.com/kimdan94/ZIBI_DE/assets/123554665/2b150505-1c79-4cb9-9336-464aa9c988db)


- **입력 유효성 체크**

- **비빌번호 정규표현식 체**

- **현재 비밀번호 불일치 시 비밀번호 수정 불가 처리**

<br>

<b>내가 쓴 글 페이지</b>
<p></p>

![image](https://github.com/kimdan94/ZIBI_DE/assets/123554665/9bef912c-e998-442e-ab1b-c1f08ebf091b)

- **로그인한 회원이 각 게시판에 작성한 글 모아보기 가능**
  - 작성한 글이 없을 경우 문구 처리

- **글 제목 클릭 시 해당 글의 상세 페이지로 이동**

<br>

<b>회원탈퇴 페이지</b>

![image](https://github.com/kimdan94/ZIBI_DE/assets/123554665/3f2f3cf0-c924-44e3-97f0-346d09ebb91b)
<br>

- **회원 탈퇴 버튼 클릭 시 confirm**

- **비밀번호 입력 시 회원 탈퇴 성공**
  - 비밀번호 불일치 시 탈퇴 폼으로 다시 이동
<br>

<b>이용약관 페이지</b>

![image](https://github.com/kimdan94/ZIBI_DE/assets/123554665/ee03f84b-8db8-4b12-b279-ad363b9201aa)

- **로그인한 경우 마이페이지 사이드바 함께 확인**
  - 미가입 사용자에게는 미노출
