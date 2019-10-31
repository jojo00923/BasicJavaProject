package dao;

import java.util.ArrayList;
import java.util.HashMap;

import vo.MovieVO;
import vo.NoticeVO;
import vo.ReserveVO;
import vo.ReviewVO;
import vo.ShowingVO;
import vo.UserVO;

public interface UserDao {
	// 단일회원 조회
	UserVO selectUser(String key, String value);
	
	UserVO selectUser(HashMap<String, String> param);
	
	// 회원 삽입
	void insertUser(UserVO user);
	
	// 전체 회원 조회
	ArrayList<UserVO> selectUserList();
	
	// 영화 정보 삽입
	void insertMovie(MovieVO movie);
	
	// 영화 목록 조회
	ArrayList<MovieVO> selectMovieList();
	
	// 공지사항 정보 삽입
	void insertNotice(NoticeVO notice);
	
	// 공지사항 목록 조회
	ArrayList<NoticeVO> selectNoticeList();
	
	// 리뷰 및 평점 삽입
	void insertReview(ReviewVO review);
	
	// 리뷰 및 평점 조회
	ArrayList<ReviewVO> selectReviewList();
	
	// 영화 예매
	void reserveMovie(ReserveVO reserve);
	
	// 예매 목록 조회
	ArrayList<ReserveVO> selectReserveList();

	ArrayList<ShowingVO> selectShowingList();


}
