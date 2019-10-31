package data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import service.UserServiceImpl;
import vo.MovieVO;
import vo.NoticeVO;
import vo.ReserveVO;
import vo.ReviewVO;
import vo.SeatVO;
import vo.ShowingVO;
import vo.UserVO;

public class Database {
	private static Database instance;

	private Database(){}

	public static Database getInstance(){
		if(instance == null){
			instance = new Database();
		}
		return instance;
	}

	public ArrayList<UserVO> tb_user = new ArrayList<UserVO>();
	
	{
		UserVO user = new UserVO();
		user.setId("admin");
		user.setPassword("admin");
		user.setName("관리자");
		user.setManager(true);
		
		tb_user.add(user);
		
		UserVO user2 = new UserVO();
		
		user2.setId("test");
		user2.setPassword("test");
		user2.setName("test");
		user2.setBirthdate("19900202");
		tb_user.add(user2);
		
		UserVO user3 = new UserVO();
		
		user3.setId("t");
		user3.setPassword("t");
		user3.setName("t");
		user3.setBirthdate("19930506");
		tb_user.add(user3);
	}
	
	
	
	public ArrayList<NoticeVO> tb_notice = new ArrayList<NoticeVO>();
	
	{
		NoticeVO notice1 = new NoticeVO();
		notice1.setTitle("[[기타]] 온라인전용 Family 요금제종료 안내");
		notice1.setContents("안녕하세요, CGV입니다. \r\r온라인 전용 Family 요금제가 아래 일정에 따라 종료됩니다. \r\r이용에 참고하시기 바랍니다. \r\r- 종료 일시 : 2019년 10월 30일(수) 10시~\r\r \t(결제 시점 기준) \r\r감사합니다.");
		tb_notice.add(notice1);
		
		NoticeVO notice2 = new NoticeVO();
		notice2.setTitle("[[시스템점검]] 2019년 11월 시스템 점검 안내");
		notice2.setContents("안녕하십니까, CGV 입니다.\r\r 원활하고 안정된 서비스 제공을 위하여 2019년 11월 새벽 시스템 점검 작업이 예정되어 있습니다.  \r\r 점검 시간 중 CGV홈페이지 및 모바일의 모든 서비스가 중단될 예정이오니 이용에 불편 없으시기 바랍니다.     \r\r1. 일시\r\r- 2019년 11월 05일(화) 02:00~07:00 (5시간)\r\r- 2019년 11월 12일(화) 02:00~07:00 (5시간)          \r\r\r2. 내용\r\r- 정기 PM작업(시스템 성능 개선 작업)      \r\r- 작업 대상 : 극장영업시스템, 온라인 서비스(홈페이지/모바일)  \r\r\r더욱 안정적이고 편리한 서비스를 제공하는 CGV가 되겠습니다.\r\r감사합니다");
		tb_notice.add(notice2);
	}
	
	public ArrayList<MovieVO> tb_movie = new ArrayList<MovieVO>();
	
	{
		MovieVO mv1 = new MovieVO();
		mv1.setMovieCode(1);
		mv1.setMovieName("말레피센트 ");
		mv1.setRunTime(120);
		mv1.setDirector("요아킴 뢰닝");
		mv1.setScore(8.5);
		mv1.setActors("안젤리나 졸리");
		mv1.setAgeLimit(15);
		mv1.setStory("강력한 어둠의 지배자이자 무어스 숲의 수호자 ‘말레피센트’는 딸처럼 돌봐온\r\t\t\t\t\t\t\t\t‘"
				+ "오로라’와 ‘필립 왕자’의  결혼 약속으로 인간 세계의 ‘잉그리스 왕비’와 대립하게 된다.");
		tb_movie.add(mv1);
		
		MovieVO mv2 = new MovieVO();
		mv2.setMovieCode(2);
		mv2.setMovieName("조       커");
		mv2.setRunTime(120);
		mv2.setDirector("토드 필립스");
		mv2.setScore(10.0);
		mv2.setActors("호아킨 피닉스");
		mv2.setAgeLimit(15);
		mv2.setStory("고담시의 광대 아서 플렉은 코미디언을 꿈꾸는 남자 하지만 모두가 미쳐가는\r\t\t\t\t\t\t\t\t"
				+ "코미디 같은 세상에서 맨 정신으로는 그가 설 자리가 없음을 깨닫게 되는데\r\t\t\t\t\t\t\t\t"
				+ "이제껏 본 적 없는 진짜 ‘조커’를 만나라!");
		tb_movie.add(mv2);
		
		MovieVO mv3 = new MovieVO();
		mv3.setMovieCode(3);
		mv3.setMovieName("퍼펙트   맨");
		mv3.setRunTime(120);
		mv3.setDirector("용       수");
		mv3.setScore(8.0);
		mv3.setActors("조진웅 설경구");
		mv3.setAgeLimit(15);
		mv3.setStory("조직 보스의 돈 7억을 빼돌려 주식에 투자하지만, 사기꾼에게 속아 주식은\r\t\t\t\t\t\t\t\t"
				+ "휴지조각이 되고 만다. 목숨을 부지하기 위해 어떻게든 7억을 구해야 하는 영기 앞에\r\t\t\t\t\t\t\t\t"
				+ "까칠한 로펌 대표 ‘장수’(설경구)가 나타난다.");
		tb_movie.add(mv3);
	}
	
	public ArrayList<ReserveVO> tb_reserve = new ArrayList<ReserveVO>();
	
	{
		
		ReserveVO rv1 = new ReserveVO();
		rv1.setReserveCode(1);
		rv1.setSeatCode(1);
		rv1.setShowingCode(2);
		rv1.setUserID("test"); 
		tb_reserve.add(rv1);
		
		ReserveVO rv2 = new ReserveVO();
		rv2.setReserveCode(2);
		rv2.setSeatCode(5);
		rv2.setShowingCode(10);
		rv2.setUserID("test");
		tb_reserve.add(rv2);

	}
	
	public ArrayList<ReviewVO> tb_review = new ArrayList<ReviewVO>();
	{
		
/*		private int reviewCode;
		private int movieCode;
		private String contents;
		private double score;
		private String userID;
		private Date date;*/
		
		// 말레피센트
		ReviewVO rv1 = new ReviewVO();
		rv1.setReviewCode(1);
		rv1.setMovieCode(1);
		rv1.setContents("너무 재밌어요. 시간가는줄 몰랐네요.");
		rv1.setScore(9.0);
		rv1.setUserID("test");
		tb_review.add(rv1);
		
		// 말레피센트
		ReviewVO rv2 = new ReviewVO();
		rv2.setReviewCode(2);
		rv2.setMovieCode(1);
		rv2.setContents("역대급 영화입니다. 잘봤어요.");
		rv2.setScore(8.0);
		rv2.setUserID("test");
		tb_review.add(rv2);
		
		//조커
		ReviewVO rv3 = new ReviewVO();
		rv3.setReviewCode(3);
		rv3.setMovieCode(2);
		rv3.setContents("영화 내용, 전개, 배우들의 연기 모두 완벽했습니다.");
		rv3.setScore(10.0);
		rv3.setUserID("test");
		tb_review.add(rv3);
		
		//퍼펙트맨
		ReviewVO rv4 = new ReviewVO();
		rv4.setReviewCode(4);
		rv4.setMovieCode(3);
		rv4.setContents("인생영화 등극");
		rv4.setScore(8.0);
		rv4.setUserID("test");
		tb_review.add(rv4);
	}
	
	///동규꺼
	public ArrayList<ShowingVO> tb_showing = new ArrayList<ShowingVO>();
	{
		// 시연할때는 이 소스 삭제
		Date day = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM월dd일");
		String today = format.format(day);
		String tomorrow = format.format(day.getTime() + (long)( 1000 * 60 * 60 * 24 ));
		String tomorrow1 = format.format(day.getTime() + (long)( 1000 * 60 * 60 * 48));
		// 시연할때는 이 소스 삭제
		
		//영화1번 말레피센트
		//상영관 1관
		//오늘
		//12:40분
		ShowingVO sv = new ShowingVO();
		sv.setShowingCode(1);
		sv.setMovieCode(1);
		sv.setScreen(1);
		sv.setDate(today);
		sv.setTime("12:40");
		
		tb_showing.add(sv);
		
		//영화1번 말레피센트
		//상영관 1관
		//오늘
		//14:50분
		ShowingVO sv2 = new ShowingVO();
		sv2.setShowingCode(2);
		sv2.setMovieCode(1);
		sv2.setScreen(1);
		sv2.setDate(today);
		sv2.setTime("14:50");
		
		tb_showing.add(sv2);
		
		//영화1번 말레피센트
		//상영관 1관
		//오늘
		//17:00분
		ShowingVO sv3 = new ShowingVO();
		sv3.setShowingCode(3);
		sv3.setMovieCode(1);
		sv3.setScreen(1);
		sv3.setDate(today);
		sv3.setTime("17:00");
		
		tb_showing.add(sv3);
		
		//영화1번 말레피센트
		//상영관 1관
		//내일
		//12:40분
		ShowingVO sv4 = new ShowingVO();
		sv4.setShowingCode(4);
		sv4.setMovieCode(1);
		sv4.setScreen(1);
		sv4.setDate(tomorrow);
		sv4.setTime("12:40");
		
		tb_showing.add(sv4);
		
		//영화1번 말레피센트
		//상영관 1관
		//내일
		//14:50분
		ShowingVO sv5 = new ShowingVO();
		sv5.setShowingCode(5);
		sv5.setMovieCode(1);
		sv5.setScreen(1);
		sv5.setDate(tomorrow);
		sv5.setTime("14:50");
		
		tb_showing.add(sv5);
		
		//영화1번 말레피센트
		//상영관 1관
		//내일
		//17:00분
		ShowingVO sv6 = new ShowingVO();
		sv6.setShowingCode(6);
		sv6.setMovieCode(1);
		sv6.setScreen(1);
		sv6.setDate(tomorrow);
		sv6.setTime("17:00");
		
		tb_showing.add(sv6);
		
		//영화1번 말레피센트
		//상영관 1관
		//내일모레
		//12:40분
		ShowingVO sv7 = new ShowingVO();
		sv7.setShowingCode(7);
		sv7.setMovieCode(1);
		sv7.setScreen(1);
		sv7.setDate(tomorrow1);
		sv7.setTime("12:40");
		
		tb_showing.add(sv7);
		
		//영화1번 말레피센트
		//상영관 1관
		//내일모레
		//14:50분
		ShowingVO sv8 = new ShowingVO();
		sv8.setShowingCode(8);
		sv8.setMovieCode(1);
		sv8.setScreen(1);
		sv8.setDate(tomorrow1);
		sv8.setTime("14:50");
		
		tb_showing.add(sv8);
		
		//영화1번 말레피센트
		//상영관 1관
		//내일모레
		//17:00분
		ShowingVO sv9 = new ShowingVO();
		sv9.setShowingCode(9);
		sv9.setMovieCode(1);
		sv9.setScreen(1);
		sv9.setDate(tomorrow1);
		sv9.setTime("17:00");
		
		tb_showing.add(sv9);
		
		
		/*조커*/
		
		//영화2번 조커
		//상영관 2관
		//오늘
		//12:40분
		ShowingVO sv10 = new ShowingVO();
		sv10.setShowingCode(10);
		sv10.setMovieCode(2);
		sv10.setScreen(2);
		sv10.setDate(today);
		sv10.setTime("12:40");
		
		tb_showing.add(sv10);
		
		//영화2번 조커
		//상영관 2관
		//오늘
		//14:50분
		ShowingVO sv11 = new ShowingVO();
		sv11.setShowingCode(11);
		sv11.setMovieCode(2);
		sv11.setScreen(2);
		sv11.setDate(today);
		sv11.setTime("14:50");
		
		tb_showing.add(sv11);
		
		//영화2번 조커
		//상영관 2관
		//오늘
		//17:00분
		ShowingVO sv12 = new ShowingVO();
		sv12.setShowingCode(12);
		sv12.setMovieCode(2);
		sv12.setScreen(2);
		sv12.setDate(today);
		sv12.setTime("17:00");
		
		tb_showing.add(sv12);
		
		//영화2번 조커
		//상영관 2관
		//내일
		//12:40분
		ShowingVO sv13 = new ShowingVO();
		sv13.setShowingCode(13);
		sv13.setMovieCode(2);
		sv13.setScreen(2);
		sv13.setDate(tomorrow);
		sv13.setTime("12:40");
		
		tb_showing.add(sv13);
		
		//영화2번 조커
		//상영관 2관
		//내일
		//14:50분
		ShowingVO sv14 = new ShowingVO();
		sv14.setShowingCode(14);
		sv14.setMovieCode(2);
		sv14.setScreen(2);
		sv14.setDate(tomorrow);
		sv14.setTime("14:50");
		
		tb_showing.add(sv14);
		
		//영화2번 조커
		//상영관 2관
		//내일
		//17:00분
		ShowingVO sv15 = new ShowingVO();
		sv15.setShowingCode(15);
		sv15.setMovieCode(2);
		sv15.setScreen(2);
		sv15.setDate(tomorrow);
		sv15.setTime("17:00");
		
		tb_showing.add(sv15);
		
		//영화2번 조커
		//상영관 2관
		//내일모레
		//12:40분
		ShowingVO sv16 = new ShowingVO();
		sv16.setShowingCode(16);
		sv16.setMovieCode(2);
		sv16.setScreen(2);
		sv16.setDate(tomorrow1);
		sv16.setTime("12:40");
		
		tb_showing.add(sv16);
		
		//영화2번 조커
		//상영관 2관
		//내일모레
		//14:50분
		ShowingVO sv17 = new ShowingVO();
		sv17.setShowingCode(17);
		sv17.setMovieCode(2);
		sv17.setScreen(2);
		sv17.setDate(tomorrow1);
		sv17.setTime("14:50");
		
		tb_showing.add(sv17);
		
		//영화2번 조커
		//상영관 2관
		//내일모레
		//17:00분
		ShowingVO sv18 = new ShowingVO();
		sv18.setShowingCode(18);
		sv18.setMovieCode(2);
		sv18.setScreen(2);
		sv18.setDate(tomorrow1);
		sv18.setTime("17:00");
		
		tb_showing.add(sv18);
		
		/*제미니 맨*/
		
		//영화3번 제미니 맨
		//상영관 3관
		//오늘
		//12:40분
		ShowingVO sv19 = new ShowingVO();
		sv19.setShowingCode(19);
		sv19.setMovieCode(3);
		sv19.setScreen(3);
		sv19.setDate(today);
		sv19.setTime("12:40");
		
		tb_showing.add(sv19);
		
		//영화3번 퍼펙트 맨
		//상영관 3관
		//오늘
		//14:50분
		ShowingVO sv20 = new ShowingVO();
		sv20.setShowingCode(20);
		sv20.setMovieCode(3);
		sv20.setScreen(3);
		sv20.setDate(today);
		sv20.setTime("14:50");
		
		tb_showing.add(sv20);
		
		//영화3번 퍼펙트 맨
		//상영관 3관
		//오늘
		//17:00분
		ShowingVO sv21 = new ShowingVO();
		sv21.setShowingCode(21);
		sv21.setMovieCode(3);
		sv21.setScreen(3);
		sv21.setDate(today);
		sv21.setTime("17:00");
		
		tb_showing.add(sv21);
		
		//영화3번 퍼펙트 맨
		//상영관 3관
		//내일
		//12:40분
		ShowingVO sv22 = new ShowingVO();
		sv22.setShowingCode(22);
		sv22.setMovieCode(3);
		sv22.setScreen(3);
		sv22.setDate(tomorrow);
		sv22.setTime("12:40");
		
		tb_showing.add(sv22);
		
		//영화3번 퍼펙트 맨
		//상영관 3관
		//내일
		//14:50분
		ShowingVO sv23 = new ShowingVO();
		sv23.setShowingCode(23);
		sv23.setMovieCode(3);
		sv23.setScreen(3);
		sv23.setDate(tomorrow);
		sv23.setTime("14:50");
		
		tb_showing.add(sv23);
		
		//영화3번 퍼펙트 맨
		//상영관 3관
		//내일
		//17:00분
		ShowingVO sv24 = new ShowingVO();
		sv24.setShowingCode(24);
		sv24.setMovieCode(3);
		sv24.setScreen(3);
		sv24.setDate(tomorrow);
		sv24.setTime("17:00");
		
		tb_showing.add(sv24);
		
		//영화3번 퍼펙트 맨
		//상영관 3관
		//내일모레
		//12:40분
		ShowingVO sv25 = new ShowingVO();
		sv25.setShowingCode(25);
		sv25.setMovieCode(3);
		sv25.setScreen(3);
		sv25.setDate(tomorrow1);
		sv25.setTime("12:40");
		
		tb_showing.add(sv25);
		
		//영화3번 퍼펙트 맨
		//상영관 3관
		//내일모레
		//14:50분
		ShowingVO sv26 = new ShowingVO();
		sv26.setShowingCode(26);
		sv26.setMovieCode(3);
		sv26.setScreen(3);
		sv26.setDate(tomorrow1);
		sv26.setTime("14:50");
		
		tb_showing.add(sv26);
		
		//영화3번 퍼펙트 맨
		//상영관 3관
		//내일모레
		//17:00분
		ShowingVO sv27 = new ShowingVO();
		sv27.setShowingCode(27);
		sv27.setMovieCode(3);
		sv27.setScreen(3);
		sv27.setDate(tomorrow1);
		sv27.setTime("17:00");
		
		tb_showing.add(sv27);
	}
	
	
	public ArrayList<SeatVO> tb_seat= new ArrayList<SeatVO>();
	{
		
		//1관 상영관
		SeatVO sv1 = new SeatVO();
		sv1.setSeatCode(1);
		sv1.setSeatNum("H1");
		sv1.setScreen(1);
		tb_seat.add(sv1);
		
		SeatVO sv2 = new SeatVO();
		sv2.setSeatCode(2);
		sv2.setSeatNum("H2");
		sv2.setScreen(1);
		tb_seat.add(sv2);
		
		SeatVO sv3 = new SeatVO();
		sv3.setSeatCode(3);
		sv3.setSeatNum("H3");
		sv3.setScreen(1);
		tb_seat.add(sv3);
		
		SeatVO sv4 = new SeatVO();
		sv4.setSeatCode(4);
		sv4.setSeatNum("H4");
		sv4.setScreen(1);
		tb_seat.add(sv4);
		
		SeatVO sv5 = new SeatVO();
		sv5.setSeatCode(5);
		sv5.setSeatNum("H5");
		sv5.setScreen(1);
		tb_seat.add(sv5);
		
		SeatVO sv6 = new SeatVO();
		sv6.setSeatCode(6);
		sv6.setSeatNum("H6");
		sv6.setScreen(1);
		tb_seat.add(sv6);
		
		SeatVO sv7 = new SeatVO();
		sv7.setSeatCode(7);
		sv7.setSeatNum("H7");
		sv7.setScreen(1);
		tb_seat.add(sv7);
		
		SeatVO sv8 = new SeatVO();
		sv8.setSeatCode(8);
		sv8.setSeatNum("H8");
		sv8.setScreen(1);
		tb_seat.add(sv8);
		
		SeatVO sv9 = new SeatVO();
		sv9.setSeatCode(9);
		sv9.setSeatNum("H9");
		sv9.setScreen(1);
		tb_seat.add(sv9);
		
		SeatVO sv10 = new SeatVO();
		sv10.setSeatCode(10);
		sv10.setSeatNum("I1");
		sv10.setScreen(1);
		tb_seat.add(sv10);
		
		SeatVO sv11 = new SeatVO();
		sv11.setSeatCode(11);
		sv11.setSeatNum("I2");
		sv11.setScreen(1);
		tb_seat.add(sv11);
		
		SeatVO sv12 = new SeatVO();
		sv12.setSeatCode(12);
		sv12.setSeatNum("I3");
		sv12.setScreen(1);
		tb_seat.add(sv12);
		
		SeatVO sv13 = new SeatVO();
		sv13.setSeatCode(13);
		sv13.setSeatNum("I4");
		sv13.setScreen(1);
		tb_seat.add(sv13);
		
		SeatVO sv14 = new SeatVO();
		sv14.setSeatCode(14);
		sv14.setSeatNum("I5");
		sv14.setScreen(1);
		tb_seat.add(sv14);
		
		SeatVO sv15 = new SeatVO();
		sv15.setSeatCode(15);
		sv15.setSeatNum("I6");
		sv15.setScreen(1);
		tb_seat.add(sv15);
		
		SeatVO sv16 = new SeatVO();
		sv16.setSeatCode(16);
		sv16.setSeatNum("I7");
		sv16.setScreen(1);
		tb_seat.add(sv16);
		
		SeatVO sv17 = new SeatVO();
		sv17.setSeatCode(17);
		sv17.setSeatNum("I8");
		sv17.setScreen(1);
		tb_seat.add(sv17);
		
		SeatVO sv18 = new SeatVO();
		sv18.setSeatCode(18);
		sv18.setSeatNum("I9");
		sv18.setScreen(1);
		tb_seat.add(sv18);
		
		SeatVO sv19 = new SeatVO();
		sv19.setSeatCode(19);
		sv19.setSeatNum("J1");
		sv19.setScreen(1);
		tb_seat.add(sv19);
		
		SeatVO sv20 = new SeatVO();
		sv20.setSeatCode(20);
		sv20.setSeatNum("J2");
		sv20.setScreen(1);
		tb_seat.add(sv20);
		
		SeatVO sv21 = new SeatVO();
		sv21.setSeatCode(21);
		sv21.setSeatNum("J3");
		sv21.setScreen(1);
		tb_seat.add(sv21);
		
		SeatVO sv22 = new SeatVO();
		sv22.setSeatCode(22);
		sv22.setSeatNum("J4");
		sv22.setScreen(1);
		tb_seat.add(sv22);
		
		SeatVO sv23 = new SeatVO();
		sv23.setSeatCode(23);
		sv23.setSeatNum("J5");
		sv23.setScreen(1);
		tb_seat.add(sv23);
		
		SeatVO sv24 = new SeatVO();
		sv24.setSeatCode(24);
		sv24.setSeatNum("J6");
		sv24.setScreen(1);
		tb_seat.add(sv24);
		
		SeatVO sv25 = new SeatVO();
		sv25.setSeatCode(25);
		sv25.setSeatNum("J7");
		sv25.setScreen(1);
		tb_seat.add(sv25);
		
		SeatVO sv26 = new SeatVO();
		sv26.setSeatCode(26);
		sv26.setSeatNum("J8");
		sv26.setScreen(1);
		tb_seat.add(sv26);
		
		SeatVO sv27 = new SeatVO();
		sv27.setSeatCode(27);
		sv27.setSeatNum("J9");
		sv27.setScreen(1);
		tb_seat.add(sv27);
		
		SeatVO sv28 = new SeatVO();
		sv28.setSeatCode(28);
		sv28.setSeatNum("K1");
		sv28.setScreen(1);
		tb_seat.add(sv28);
		
		SeatVO sv29 = new SeatVO();
		sv29.setSeatCode(29);
		sv29.setSeatNum("K2");
		sv29.setScreen(1);
		tb_seat.add(sv29);
		
		SeatVO sv30 = new SeatVO();
		sv30.setSeatCode(30);
		sv30.setSeatNum("K3");
		sv30.setScreen(1);
		tb_seat.add(sv30);
		
		SeatVO sv31 = new SeatVO();
		sv31.setSeatCode(31);
		sv31.setSeatNum("K4");
		sv31.setScreen(1);
		tb_seat.add(sv31);
		
		SeatVO sv32 = new SeatVO();
		sv32.setSeatCode(32);
		sv32.setSeatNum("K5");
		sv32.setScreen(1);
		tb_seat.add(sv32);
		
		SeatVO sv33 = new SeatVO();
		sv33.setSeatCode(33);
		sv33.setSeatNum("K6");
		sv33.setScreen(1);
		tb_seat.add(sv33);
		
		SeatVO sv34 = new SeatVO();
		sv34.setSeatCode(34);
		sv34.setSeatNum("K7");
		sv34.setScreen(1);
		tb_seat.add(sv34);
		
		SeatVO sv35 = new SeatVO();
		sv35.setSeatCode(35);
		sv35.setSeatNum("K8");
		sv35.setScreen(1);
		tb_seat.add(sv35);
		
		SeatVO sv36 = new SeatVO();
		sv36.setSeatCode(36);
		sv36.setSeatNum("K9");
		sv36.setScreen(1);
		tb_seat.add(sv36);
		
		SeatVO sv37 = new SeatVO();
		sv37.setSeatCode(37);
		sv37.setSeatNum("L1");
		sv37.setScreen(1);
		tb_seat.add(sv37);
		
		SeatVO sv38 = new SeatVO();
		sv38.setSeatCode(38);
		sv38.setSeatNum("L2");
		sv38.setScreen(1);
		tb_seat.add(sv38);
		
		SeatVO sv39 = new SeatVO();
		sv39.setSeatCode(39);
		sv39.setSeatNum("L3");
		sv39.setScreen(1);
		tb_seat.add(sv39);
		
		SeatVO sv40 = new SeatVO();
		sv40.setSeatCode(40);
		sv40.setSeatNum("L4");
		sv40.setScreen(1);
		tb_seat.add(sv40);
		
		SeatVO sv41 = new SeatVO();
		sv41.setSeatCode(41);
		sv41.setSeatNum("L5");
		sv41.setScreen(1);
		tb_seat.add(sv41);
		
		SeatVO sv42 = new SeatVO();
		sv42.setSeatCode(42);
		sv42.setSeatNum("L6");
		sv42.setScreen(1);
		tb_seat.add(sv42);
		
		SeatVO sv43 = new SeatVO();
		sv43.setSeatCode(43);
		sv43.setSeatNum("L7");
		sv43.setScreen(1);
		tb_seat.add(sv43);
		
		SeatVO sv44 = new SeatVO();
		sv44.setSeatCode(44);
		sv44.setSeatNum("L8");
		sv44.setScreen(1);
		tb_seat.add(sv44);
		
		SeatVO sv45 = new SeatVO();
		sv45.setSeatCode(45);
		sv45.setSeatNum("L9");
		sv45.setScreen(1);
		tb_seat.add(sv45);
		
		
		//2관 상영관
		
		SeatVO sv46 = new SeatVO();
		sv46.setSeatCode(46);
		sv46.setSeatNum("H1");
		sv46.setScreen(2);
		tb_seat.add(sv46);
		
		SeatVO sv47 = new SeatVO();
		sv47.setSeatCode(47);
		sv47.setSeatNum("H2");
		sv47.setScreen(2);
		tb_seat.add(sv47);
		
		SeatVO sv48 = new SeatVO();
		sv48.setSeatCode(48);
		sv48.setSeatNum("H3");
		sv48.setScreen(2);
		tb_seat.add(sv48);
		
		SeatVO sv49 = new SeatVO();
		sv49.setSeatCode(49);
		sv49.setSeatNum("H4");
		sv49.setScreen(2);
		tb_seat.add(sv49);
		
		SeatVO sv50 = new SeatVO();
		sv50.setSeatCode(50);
		sv50.setSeatNum("H5");
		sv50.setScreen(2);
		tb_seat.add(sv50);
		
		SeatVO sv51 = new SeatVO();
		sv51.setSeatCode(51);
		sv51.setSeatNum("H6");
		sv51.setScreen(2);
		tb_seat.add(sv51);
		
		SeatVO sv52 = new SeatVO();
		sv52.setSeatCode(52);
		sv52.setSeatNum("H7");
		sv52.setScreen(2);
		tb_seat.add(sv52);
		
		SeatVO sv53 = new SeatVO();
		sv53.setSeatCode(53);
		sv53.setSeatNum("H8");
		sv53.setScreen(2);
		tb_seat.add(sv53);
		
		SeatVO sv54 = new SeatVO();
		sv54.setSeatCode(54);
		sv54.setSeatNum("H9");
		sv54.setScreen(2);
		tb_seat.add(sv54);
		
		SeatVO sv55 = new SeatVO();
		sv55.setSeatCode(55);
		sv55.setSeatNum("I1");
		sv55.setScreen(2);
		tb_seat.add(sv55);
		
		SeatVO sv56 = new SeatVO();
		sv56.setSeatCode(56);
		sv56.setSeatNum("I2");
		sv56.setScreen(2);
		tb_seat.add(sv56);
		
		SeatVO sv57 = new SeatVO();
		sv57.setSeatCode(57);
		sv57.setSeatNum("I3");
		sv57.setScreen(2);
		tb_seat.add(sv57);
		
		SeatVO sv58 = new SeatVO();
		sv58.setSeatCode(58);
		sv58.setSeatNum("I4");
		sv58.setScreen(2);
		tb_seat.add(sv58);
		
		SeatVO sv59 = new SeatVO();
		sv59.setSeatCode(59);
		sv59.setSeatNum("I5");
		sv59.setScreen(2);
		tb_seat.add(sv59);
		
		SeatVO sv60 = new SeatVO();
		sv60.setSeatCode(60);
		sv60.setSeatNum("I6");
		sv60.setScreen(2);
		tb_seat.add(sv60);
		
		SeatVO sv61 = new SeatVO();
		sv61.setSeatCode(61);
		sv61.setSeatNum("I7");
		sv61.setScreen(1);
		tb_seat.add(sv61);
		
		SeatVO sv62 = new SeatVO();
		sv62.setSeatCode(62);
		sv62.setSeatNum("I8");
		sv62.setScreen(2);
		tb_seat.add(sv62);
		
		SeatVO sv63 = new SeatVO();
		sv63.setSeatCode(63);
		sv63.setSeatNum("I9");
		sv63.setScreen(1);
		tb_seat.add(sv63);
		
		SeatVO sv64 = new SeatVO();
		sv64.setSeatCode(64);
		sv64.setSeatNum("J1");
		sv64.setScreen(2);
		tb_seat.add(sv64);
		
		SeatVO sv65 = new SeatVO();
		sv65.setSeatCode(65);
		sv65.setSeatNum("J2");
		sv65.setScreen(2);
		tb_seat.add(sv65);
		
		SeatVO sv66 = new SeatVO();
		sv66.setSeatCode(66);
		sv66.setSeatNum("J3");
		sv66.setScreen(2);
		tb_seat.add(sv66);
		
		SeatVO sv67 = new SeatVO();
		sv67.setSeatCode(67);
		sv67.setSeatNum("J4");
		sv67.setScreen(2);
		tb_seat.add(sv67);
		
		SeatVO sv68 = new SeatVO();
		sv68.setSeatCode(68);
		sv68.setSeatNum("J5");
		sv68.setScreen(2);
		tb_seat.add(sv68);
		
		SeatVO sv69 = new SeatVO();
		sv69.setSeatCode(69);
		sv69.setSeatNum("J6");
		sv69.setScreen(2);
		tb_seat.add(sv69);
		
		SeatVO sv70 = new SeatVO();
		sv70.setSeatCode(70);
		sv70.setSeatNum("J7");
		sv70.setScreen(2);
		tb_seat.add(sv70);
		
		SeatVO sv71 = new SeatVO();
		sv71.setSeatCode(71);
		sv71.setSeatNum("J8");
		sv71.setScreen(2);
		tb_seat.add(sv71);
		
		SeatVO sv72 = new SeatVO();
		sv72.setSeatCode(72);
		sv72.setSeatNum("J9");
		sv72.setScreen(2);
		tb_seat.add(sv72);
		
		SeatVO sv73 = new SeatVO();
		sv73.setSeatCode(73);
		sv73.setSeatNum("K1");
		sv73.setScreen(2);
		tb_seat.add(sv73);
		
		SeatVO sv74 = new SeatVO();
		sv74.setSeatCode(74);
		sv74.setSeatNum("K2");
		sv74.setScreen(2);
		tb_seat.add(sv74);
		
		SeatVO sv75 = new SeatVO();
		sv75.setSeatCode(75);
		sv75.setSeatNum("K3");
		sv75.setScreen(2);
		tb_seat.add(sv75);
		
		SeatVO sv76 = new SeatVO();
		sv76.setSeatCode(76);
		sv76.setSeatNum("K4");
		sv76.setScreen(2);
		tb_seat.add(sv76);
		
		SeatVO sv77 = new SeatVO();
		sv77.setSeatCode(77);
		sv77.setSeatNum("K5");
		sv77.setScreen(2);
		tb_seat.add(sv77);
		
		SeatVO sv78 = new SeatVO();
		sv78.setSeatCode(78);
		sv78.setSeatNum("K6");
		sv78.setScreen(2);
		tb_seat.add(sv78);
		
		SeatVO sv79= new SeatVO();
		sv79.setSeatCode(79);
		sv79.setSeatNum("K7");
		sv79.setScreen(2);
		tb_seat.add(sv79);
		
		SeatVO sv80 = new SeatVO();
		sv80.setSeatCode(80);
		sv80.setSeatNum("K8");
		sv80.setScreen(2);
		tb_seat.add(sv80);
		
		SeatVO sv81 = new SeatVO();
		sv81.setSeatCode(81);
		sv81.setSeatNum("K9");
		sv81.setScreen(2);
		tb_seat.add(sv81);
		
		SeatVO sv82 = new SeatVO();
		sv82.setSeatCode(82);
		sv82.setSeatNum("L1");
		sv82.setScreen(2);
		tb_seat.add(sv82);
		
		SeatVO sv83 = new SeatVO();
		sv83.setSeatCode(83);
		sv83.setSeatNum("L2");
		sv83.setScreen(2);
		tb_seat.add(sv83);
		
		SeatVO sv84 = new SeatVO();
		sv84.setSeatCode(84);
		sv84.setSeatNum("L3");
		sv84.setScreen(2);
		tb_seat.add(sv84);
		
		SeatVO sv85 = new SeatVO();
		sv85.setSeatCode(85);
		sv85.setSeatNum("L4");
		sv85.setScreen(2);
		tb_seat.add(sv85);
		
		SeatVO sv86 = new SeatVO();
		sv86.setSeatCode(86);
		sv86.setSeatNum("L5");
		sv86.setScreen(2);
		tb_seat.add(sv86);
		
		SeatVO sv87 = new SeatVO();
		sv87.setSeatCode(87);
		sv87.setSeatNum("L6");
		sv87.setScreen(2);
		tb_seat.add(sv87);
		
		SeatVO sv88 = new SeatVO();
		sv88.setSeatCode(88);
		sv88.setSeatNum("L7");
		sv88.setScreen(2);
		tb_seat.add(sv88);
		
		SeatVO sv89 = new SeatVO();
		sv89.setSeatCode(89);
		sv89.setSeatNum("L8");
		sv89.setScreen(2);
		tb_seat.add(sv89);
		
		SeatVO sv90 = new SeatVO();
		sv90.setSeatCode(90);
		sv90.setSeatNum("L9");
		sv90.setScreen(2);
		tb_seat.add(sv90);
		
		//3관 상영관
		
		SeatVO sv91 = new SeatVO();
		sv91.setSeatCode(91);
		sv91.setSeatNum("H1");
		sv91.setScreen(3);
		tb_seat.add(sv91);
		
		SeatVO sv92 = new SeatVO();
		sv92.setSeatCode(92);
		sv92.setSeatNum("H2");
		sv92.setScreen(3);
		tb_seat.add(sv92);
		
		SeatVO sv93 = new SeatVO();
		sv93.setSeatCode(93);
		sv93.setSeatNum("H3");
		sv93.setScreen(3);
		tb_seat.add(sv93);
		
		SeatVO sv94 = new SeatVO();
		sv94.setSeatCode(94);
		sv94.setSeatNum("H4");
		sv94.setScreen(3);
		tb_seat.add(sv94);
		
		SeatVO sv95 = new SeatVO();
		sv95.setSeatCode(95);
		sv95.setSeatNum("H5");
		sv95.setScreen(3);
		tb_seat.add(sv95);
		
		SeatVO sv96 = new SeatVO();
		sv96.setSeatCode(96);
		sv96.setSeatNum("H6");
		sv96.setScreen(3);
		tb_seat.add(sv96);
		
		SeatVO sv97 = new SeatVO();
		sv97.setSeatCode(97);
		sv97.setSeatNum("H7");
		sv97.setScreen(2);
		tb_seat.add(sv97);
		
		SeatVO sv98 = new SeatVO();
		sv98.setSeatCode(98);
		sv98.setSeatNum("H8");
		sv98.setScreen(3);
		tb_seat.add(sv98);
		
		SeatVO sv99 = new SeatVO();
		sv99.setSeatCode(99);
		sv99.setSeatNum("H9");
		sv99.setScreen(3);
		tb_seat.add(sv99);
		
		SeatVO sv100 = new SeatVO();
		sv100.setSeatCode(99);
		sv100.setSeatNum("I1");
		sv100.setScreen(3);
		tb_seat.add(sv100);
		
		SeatVO sv101 = new SeatVO();
		sv101.setSeatCode(101);
		sv101.setSeatNum("I2");
		sv101.setScreen(3);
		tb_seat.add(sv101);
		
		SeatVO sv102 = new SeatVO();
		sv102.setSeatCode(102);
		sv102.setSeatNum("I3");
		sv102.setScreen(3);
		tb_seat.add(sv102);
		
		SeatVO sv103 = new SeatVO();
		sv103.setSeatCode(103);
		sv103.setSeatNum("I4");
		sv103.setScreen(3);
		tb_seat.add(sv103);
		
		SeatVO sv104 = new SeatVO();
		sv104.setSeatCode(104);
		sv104.setSeatNum("I5");
		sv104.setScreen(3);
		tb_seat.add(sv104);
		
		SeatVO sv105 = new SeatVO();
		sv105.setSeatCode(105);
		sv105.setSeatNum("I6");
		sv105.setScreen(3);
		tb_seat.add(sv105);
		
		SeatVO sv106 = new SeatVO();
		sv106.setSeatCode(106);
		sv106.setSeatNum("I7");
		sv106.setScreen(3);
		tb_seat.add(sv106);
		
		SeatVO sv107 = new SeatVO();
		sv107.setSeatCode(107);
		sv107.setSeatNum("I8");
		sv107.setScreen(3);
		tb_seat.add(sv107);
		
		SeatVO sv108 = new SeatVO();
		sv108.setSeatCode(108);
		sv108.setSeatNum("I9");
		sv108.setScreen(3);
		tb_seat.add(sv108);
		
		SeatVO sv109 = new SeatVO();
		sv109.setSeatCode(109);
		sv109.setSeatNum("J1");
		sv109.setScreen(3);
		tb_seat.add(sv109);
		
		SeatVO sv110 = new SeatVO();
		sv110.setSeatCode(110);
		sv110.setSeatNum("J2");
		sv110.setScreen(3);
		tb_seat.add(sv110);
		
		SeatVO sv111 = new SeatVO();
		sv111.setSeatCode(111);
		sv111.setSeatNum("J3");
		sv111.setScreen(3);
		tb_seat.add(sv111);
		
		SeatVO sv112 = new SeatVO();
		sv112.setSeatCode(112);
		sv112.setSeatNum("J4");
		sv112.setScreen(3);
		tb_seat.add(sv112);
		
		SeatVO sv113 = new SeatVO();
		sv113.setSeatCode(113);
		sv113.setSeatNum("J5");
		sv113.setScreen(3);
		tb_seat.add(sv113);
		
		SeatVO sv114 = new SeatVO();
		sv114.setSeatCode(114);
		sv114.setSeatNum("J6");
		sv114.setScreen(3);
		tb_seat.add(sv114);
		
		SeatVO sv115 = new SeatVO();
		sv115.setSeatCode(115);
		sv115.setSeatNum("J7");
		sv115.setScreen(3);
		tb_seat.add(sv115);
		
		SeatVO sv116 = new SeatVO();
		sv116.setSeatCode(116);
		sv116.setSeatNum("J8");
		sv116.setScreen(3);
		tb_seat.add(sv116);
		
		SeatVO sv117 = new SeatVO();
		sv117.setSeatCode(117);
		sv117.setSeatNum("J9");
		sv117.setScreen(3);
		tb_seat.add(sv117);
		
		SeatVO sv118 = new SeatVO();
		sv118.setSeatCode(118);
		sv118.setSeatNum("K1");
		sv118.setScreen(3);
		tb_seat.add(sv118);
		
		SeatVO sv119 = new SeatVO();
		sv119.setSeatCode(119);
		sv119.setSeatNum("K2");
		sv119.setScreen(3);
		tb_seat.add(sv119);
		
		SeatVO sv120 = new SeatVO();
		sv120.setSeatCode(120);
		sv120.setSeatNum("K3");
		sv120.setScreen(3);
		tb_seat.add(sv120);
		
		SeatVO sv121 = new SeatVO();
		sv121.setSeatCode(121);
		sv121.setSeatNum("K4");
		sv121.setScreen(3);
		tb_seat.add(sv121);
		
		SeatVO sv122 = new SeatVO();
		sv122.setSeatCode(122);
		sv122.setSeatNum("K5");
		sv122.setScreen(3);
		tb_seat.add(sv122);
		
		SeatVO sv123 = new SeatVO();
		sv123.setSeatCode(123);
		sv123.setSeatNum("K6");
		sv123.setScreen(3);
		tb_seat.add(sv123);
		
		SeatVO sv124= new SeatVO();
		sv124.setSeatCode(124);
		sv124.setSeatNum("K7");
		sv124.setScreen(3);
		tb_seat.add(sv124);
		
		SeatVO sv125 = new SeatVO();
		sv125.setSeatCode(125);
		sv125.setSeatNum("K8");
		sv125.setScreen(3);
		tb_seat.add(sv125);
		
		SeatVO sv126 = new SeatVO();
		sv126.setSeatCode(126);
		sv126.setSeatNum("K9");
		sv126.setScreen(3);
		tb_seat.add(sv126);
		
		SeatVO sv127 = new SeatVO();
		sv127.setSeatCode(127);
		sv127.setSeatNum("L1");
		sv127.setScreen(3);
		tb_seat.add(sv127);
		
		SeatVO sv128 = new SeatVO();
		sv128.setSeatCode(128);
		sv128.setSeatNum("L2");
		sv128.setScreen(3);
		tb_seat.add(sv128);
		
		SeatVO sv129 = new SeatVO();
		sv129.setSeatCode(129);
		sv129.setSeatNum("L3");
		sv129.setScreen(3);
		tb_seat.add(sv129);
		
		SeatVO sv130 = new SeatVO();
		sv130.setSeatCode(130);
		sv130.setSeatNum("L4");
		sv130.setScreen(3);
		tb_seat.add(sv130);
		
		SeatVO sv131 = new SeatVO();
		sv131.setSeatCode(131);
		sv131.setSeatNum("L5");
		sv131.setScreen(3);
		tb_seat.add(sv131);
		
		SeatVO sv132 = new SeatVO();
		sv132.setSeatCode(132);
		sv132.setSeatNum("L6");
		sv132.setScreen(3);
		tb_seat.add(sv132);
		
		SeatVO sv133 = new SeatVO();
		sv133.setSeatCode(133);
		sv133.setSeatNum("L7");
		sv133.setScreen(3);
		tb_seat.add(sv133);
		
		SeatVO sv134 = new SeatVO();
		sv134.setSeatCode(134);
		sv134.setSeatNum("L8");
		sv134.setScreen(3);
		tb_seat.add(sv134);
		
		SeatVO sv135 = new SeatVO();
		sv135.setSeatCode(135);
		sv135.setSeatNum("L9");
		sv135.setScreen(3);
		tb_seat.add(sv135);
		
	}
}
























