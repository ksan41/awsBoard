# awsBoard

로그인, 게시글, 댓글 기능이 들어간 간단한 게시판 프로젝트 입니다.

AWS에 연동해 배포까지 진행하는 과정을 담았습니다.  



> 참고 강의, 저서
>
> - 스프링 부트와 AWS로 혼자 구현하는 웹 서비스 - 이동욱
> - 실전! 스프링 부트와 JPA 활용1 웹 애플리케이션 개발 - 김영한



배포링크



---

## 목차

1. 개발환경
2. 설계
   - 기능분석
   - 도메인 모델, 테이블 설계
   - 화면 설계

3. 엔티티 구현
4. 도메인 개발
   - 회원 도메인 개발
   - 게시글 도메인 개발
   - 댓글 도메인 개발

5. 웹 계층 개발
   - 홈 화면, 레이아웃
   - 회원가입
   - 로그인/로그아웃
   - 회원정보 수정
   - 게시글 등록
   - 게시글 수정/삭제
   - 댓글 등록
   - 댓글 수정/삭제
6. AWS 연동



---

## 1. 개발환경

- 운영체제: Window OS (10)
- IDE: IntelliJ IDEA Community (2020.1.4), VS Code
- Language: Java (jdk8)
- Framework: SpringBoot (2.2.11 SNAPSHOT)
- WAS: Apache Tomcat 8.5
- Build: Gradle 6.6.1
- DBMS: MariaDB
- Front: Thymeleaf
- Plugins: Lombok
- 형상관리: Git - Git Bash, GitHub Desktop

---

## 2. 설계

#### - 기능분석

1. 회원기능
    -회원가입
    -로그인/로그아웃
    -회원정보 수정

2. 게시판
    -게시글 조회
    -게시글 등록
    -게시글 수정
    -게시글 삭제
    -게시글 검색
    -페이징바

3. 댓글
     -댓글 등록
     -댓글 수정
     -댓글 삭제

   

   *기타 요구사항
    -로그인하지 않으면 게시글은 등록만 가능하다.
    -게시글 검색은 제목/내용/작성자로 검색할 수 있다.
    -게시글을 열람할 때 조회수도 증가한다.



#### 도메인 모델, 테이블 설계

https://drive.google.com/file/d/1wt-kVGhdkoJ5Z1ddMWoAxwHYOpzumfwr/view?usp=sharing



회원은 여러 게시글을 작성할 수 있다. (1대 다)
하나의 게시글에는 여러개의 댓글이 달릴 수 있다. (1대 다)



#### 화면 설계

**https://ovenapp.io/view/EmXhOdyDCMGCrPOgfWhHYBhLDwwEqyak/ALD8x**

카카오 오븐 사용

---

