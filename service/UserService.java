package service;

import vo.MovieVO;

public interface UserService {
	
	void join(); // 회원가입
	
	void id(String id); // 아이디
//	
//	void password(); //비밀번호
//	
//	void name();
	
//	void birth();
	
	void login(); // 로그인
	
	void logout(); // 로그아웃
	
	void managerLogin(); // 관리자 로그인
	
	void userLogin(); // 관리자 로그인
	
	void userList(); // 회원 전체 목록 출력
	
	void manageMovie(); // 영화 관리
	
	void insertMovie(); // 영화 등록
	
	void deleteMovie(); // 영화 삭제
	
	void selectMovie(); // 영화 목록
	
	void userNotice(); // 사용자 공지사항
	
	void manageNotice(); // 공지사항 관리
	
	void insertNotice(); // 공지사항 등록
	
	void deleteNotice(); // 공지사항 삭제
	                                                                                                                                                                
	void selectNotice(); // 공지사항 목록

	void printMovie(); // 영화 출력
	
	void reserveMovie(); // 영화 예매
	
	void reserveList(); // 예매 내역
	
	void reserveDelete(); // 예매 취소
	
	void insertReview(); // 평점 및 리뷰 등록
	
	void deleteReview(); // 평점 및 리뷰 삭제
	
	void printReview();	// 평점 및 리뷰 뿌려주
	
	void selecetReview(); // 평점 및 리뷰 목록(메인메뉴에서)
	
	void selecetReview1(); // 평점 및 리뷰 목록(마이페이지에서)
	
	void myPage(); // 마이페이지
	
	void myInfo();	//내정보
	
	void myMovieList(); //내가 본 영화

//	void choiceMovie(MovieVO mv); //영화 선택
	
	void choiceDate(MovieVO mv); // 영화 날짜 선택
	
	void choiceTime(MovieVO mv ,String date , int movieCode); // 영화 시간대 선택
	
//	void peoplecount(); // 인원수 선택
	
	void choiceSeat(int showingCode, MovieVO mv ,String date , int movieCode, int screenNum); // 좌석 선택
	
}
