package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import vo.MovieVO;
import vo.NoticeVO;
import vo.ReserveVO;
import vo.ReviewVO;
import vo.SeatVO;
import vo.ShowingVO;
import vo.UserVO;
import controller.Controller;
import dao.UserDao;
import dao.UserDaoImpl;
import data.Database;
import data.Session;

public class UserServiceImpl implements UserService {

	private static UserServiceImpl instance;



	private UserServiceImpl(){}

	public static UserServiceImpl getInstance(){           
		if(instance == null){
			instance = new UserServiceImpl();
		}
		return instance;
	}

	UserDao userDao = UserDaoImpl.getInstance();

	private void showBar(String str) {
		System.out.println();
		System.out.println("┌─────────────────────────────────────┐");
		System.out.println("\t\t" + str);
		System.out.println("└─────────────────────────────────────┘");
	}
	private void showBar(String str, int showBarSize, int showBarTab) {
		String up = "┌";
		for(int i=0; i<showBarSize; i++) {
			up += "─";
		}
		up += "┐";
		
		String title = "";
		for(int i=0; i<showBarTab; i++) {
			title += "\t";
		}
		title += str;
		
		String down = "└";
		for(int i=0; i<showBarSize; i++) {
			down += "─";
		}
		down += "┘";
		
		System.out.println();
		System.out.println(up);
		System.out.println(title);
		System.out.println(down);
		
		
//		System.out.println();
//		System.out.println("┌─────────────────────────────────────┐");
//		System.out.println("\t\t" + str);
//		System.out.println("└─────────────────────────────────────┘");
	}

	@Override
	public void join() {
		// 회원가입
		// 사용자 정보 입력
		Scanner s = new Scanner(System.in);

		//아이디 정규식 검사
		boolean isform = true;
		String id = null;
		while(isform){
			System.out.println("[아이디는 영문자로 시작하며 4자리에서 11자리까지 입력]");
			System.out.print("아이디 : ");
			id = s.nextLine();
			String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
			if(!(Pattern.matches(regex, id))){
				System.out.println("형식이 맞지않습니다.");

				isform = true;
			}else{

				isform = false;
			}
		}
		//비밀번호 정규식 검사
		isform = true;
		String password = null;
		while(isform){
			System.out.println("[비밀번호는 최소 8 ~ 12자 최소 하나의 문자, 하나의 숫자 및 하나의 특수 문자]");

			System.out.print("비밀번호 : ");
			password = s.nextLine();
			String regex = "[a-zA-Z0-9!@#$%^&*()_+]{8,12}";

			if(!(Pattern.matches(regex, password))){
				System.out.println("형식이 맞지않습니다.");

				isform = true;
			}else{

				isform = false;
			}
		}

		//이름 정규식 검사
		isform = true;
		String name = null;
		while(isform){
			System.out.println("[이름은 한글로 2~5글자로 입력해주세요]");
			System.out.print("이름 : ");
			name = s.nextLine();
			String regex = "^[가-힣]{2,5}$";
			if(!(Pattern.matches(regex, name))){
				System.out.println("형식이 맞지않습니다.");

				isform = true;
			}else{

				isform = false;
			}
		}

		//생년월일 정규식 검사
		isform = true;
		String birth = null;
		while(isform){
			System.out.println("[생년월일은 숫자 8자리를 입력해주세요]");
			System.out.print("생년월일 : ");
			birth = s.nextLine();
			String regex = "^[0-9]{8}";
			if(!(Pattern.matches(regex, birth))){
				System.out.println("형식이 맞지않습니다.");
				isform = true;
			}else{

				isform = false;
			}
		}


		// 아이디 중복 검사(아이디가 일치하는 회원 검색)
		UserVO user = new UserVO();
		user.setId(id);
		user.setPassword(password);
		user.setName(name);
		user.setBirthdate(birth);

		UserVO userCheck = userDao.selectUser("ID", user.getId());

		// 사용자 정보 저장
		if(userCheck == null){
			userDao.insertUser(user);
			System.out.println(user.getName() + "님의 가입을 환영합니다.");
			new Controller().start();
		}else{
			System.out.println("이미 존재하는 아이디입니다.");
			join();
		}
	}

	//아이디 정규식
	@Override
	public void id(String id) {
		String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
		if(Pattern.matches(regex, "id")){
			System.out.println("아이디 가입 축하");
		}


	}



	@Override
	public void login() {
		// 로그인
		// 아이디, 비밀번호 입력
		Scanner s = new Scanner(System.in);
		showBar("로그인");
		System.out.print("아이디 : ");
		String id = s.nextLine();
		System.out.print("비밀번호 : ");
		String password = s.nextLine();

		// 아이디와 비밀번호가 일치하는 회원 검색
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("ID", id);
		param.put("PASSWORD", password);

		UserVO user = userDao.selectUser(param);

		// 있으면 로그인
		// 없으면 로그인 실패
		if(user == null){
			System.out.println("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
			login();
		}else{
			Session.loginUser = user;
			System.out.println("로그인 성공");

			if(user.isManager()){
				System.out.println("관리자님 환영합니다.");
				managerLogin();
			}else{
				System.out.println(user.getName() + "님 환영합니다.");
				userLogin();
			}
		}
	}

	@Override
	public void logout() {
		// 로그아웃
		Session ses = new Session();
		ses.loginUser = null;
		System.out.println("로그아웃 되었습니다.");
		new Controller().start();
	} 

	@Override
	public void managerLogin(){
		Scanner s = new Scanner(System.in);

		int menu;
		/*		1.영화등록 및 삭제
		2.회원정보조회
		3.공지사항등록*/
		showBar("관리자 페이지");
		System.out.println("----------------- 메뉴 ------------------");
		System.out.println("1. 회원정보조회");
		System.out.println("2. 영화 관리");
		System.out.println("3. 공지사항 관리");
		System.out.println("4. 로그아웃");
		System.out.println("0. 프로그램 종료");
		System.out.println("---------------------------------------");
		System.out.print("메뉴에 해당하는 번호 입력 > ");

		menu = Integer.parseInt(s.nextLine());  

		switch(menu){
		case 1:	// 회원정보조회
			userList();
			break;

		case 2:	// 영화 관리
			manageMovie();
			break;

		case 3: // 공지사항 관리
			manageNotice();
			break;

		case 4: // 로그아웃
			logout();
			break;

		case 0: // 프로그램 종료
			System.out.println("프로그램 종료");
			break;

		default : 
			System.out.println("잘못 입력하셨습니다.");
			managerLogin();

		}
	}

	@Override
	public void userLogin() {
		// 사용자 로그인
		Scanner s = new Scanner(System.in);

		int menu;
		/*		1.영화등록 및 삭제
		2.회원정보조회
		3.공지사항등록*/
		showBar("사용자 페이지",40,2);
		System.out.println("------------------ 메뉴 -------------------");
		System.out.println("1. 영화예매");
		System.out.println("2. 예매내역");
		System.out.println("3. 마이페이지");
		System.out.println("4. 공지사항");
		System.out.println("5. 로그아웃");
		System.out.println("------------------------------------------");
		System.out.println("메뉴에 해당하는 번호 입력 > ");

		menu = Integer.parseInt(s.nextLine());  

		switch(menu){
		case 1:	// 영화예매
			reserveMovie();
			break;

		case 2:	// 예매내역
			reserveList();
			break;

		case 3: // 마이페이지
			myPage();
			break;

		case 4: // 공지사항
			userNotice();
			break;

		case 5: // 로그아웃
			logout();
			break;

		default : 
			System.out.println("잘못 입력하셨습니다.");
			userLogin();
		}	
	}

	@Override
	public void userList() {
		// 회원 전체 목록 출력
		// 회원 전체 검색
		ArrayList<UserVO> userList = userDao.selectUserList();
		showBar("회원목록");
		// 출력
		System.out.println("---------------------------------------");
		System.out.println("번호\t아이디\t이름");
		System.out.println("---------------------------------------");
		for(int i = 0; i < userList.size(); i++){
			UserVO user = userList.get(i);
			System.out.println(i + 1 + "\t" + user.getId() + "\t" + user.getName());
		}
		System.out.println("---------------------------------------");

		managerLogin();
	}

	@Override
	public void manageMovie() {
		// 영화 관리
		int menu;
		Scanner s = new Scanner(System.in);

		showBar("영화 관리");
		System.out.println("----------------- 메뉴 ------------------");
		System.out.println("1. 영화 조회");
		System.out.println("2. 영화 등록");
		System.out.println("3. 영화 삭제");
		System.out.println("4. 뒤로 가기");
		System.out.println("---------------------------------------");
		System.out.println("메뉴에 해당하는 번호 입력 > ");
		menu = Integer.parseInt(s.nextLine());

		switch(menu){
		case 1:	// 영화 조회
			selectMovie();
			break;

		case 2:	// 영화 등록
			insertMovie();
			break;

		case 3:	// 영화 삭제
			deleteMovie();
			break;

		case 4: // 뒤로 가기
			managerLogin();
			break;

		default : 
			System.out.println("잘못 입력하셨습니다.");
			manageMovie();
		}

	}

	@Override
	public void insertMovie() {
		// 영화 등록
		Scanner s = new Scanner(System.in);
		showBar("영화 등록");
		System.out.print("영화이름 입력 : ");
		String movieName = s.nextLine();
		System.out.print("러닝타임 입력 : ");
		int runTime = Integer.parseInt(s.nextLine());
		System.out.print("감독 입력 : ");
		String director = s.nextLine();
		System.out.print("평점 입력 : ");
		int score = Integer.parseInt(s.nextLine());
		System.out.print("출연진 입력 : ");
		String actors = s.nextLine();
		System.out.print("연령제한 입력 : ");
		int ageLimit = Integer.parseInt(s.nextLine());
		System.out.print("줄거리 입력 : ");
		String story = s.nextLine();

		//데이터베이스에 저장하는 코드 들어갈 자리
		//		private int movieCode;
		//		private String movieName;
		//		private int runTime;
		//		private String director;
		//		private String story;
		//		private double score;
		//		private String actors;
		//		private int ageLimit;

		MovieVO movie = new MovieVO();
		movie.setMovieName(movieName); //String
		movie.setRunTime(runTime); //int
		movie.setDirector(director); //String
		movie.setScore(score); //double
		movie.setActors(actors); //String
		movie.setAgeLimit(ageLimit); //int
		movie.setStory(story); //String
		userDao.insertMovie(movie); 


		System.out.println("----------------- 메뉴 ------------------");
		System.out.println("영화이름 : " + movieName); 
		System.out.println("러닝타임 : " + runTime); 
		System.out.println("감독 : " + director); 
		System.out.println("평점 : " + score); 
		System.out.println("출연진 : " + actors); 
		System.out.println("연령제한 : " + ageLimit); 
		System.out.println("줄거리 : " + story);
		System.out.println("---------------------------------------");
		System.out.println("저장되었습니다.");

		managerLogin();
	}



	@Override
	public void deleteMovie() {
		// 영화 삭제
		ArrayList<MovieVO> movieList = userDao.selectMovieList();
		if(movieList.isEmpty()){
			System.out.println("삭제할 영화가 없습니다.");
			managerLogin();
		} else {
			Scanner s = new Scanner(System.in);
			printMovie();
			showBar("영화 삭제");

			System.out.print("삭제할 영화 번호를 입력하세요: "); 
			int number = Integer.parseInt(s.nextLine());

			System.out.println(number + "번을 삭제하시겠습니까? (Y / N)");
			String delete = s.nextLine();

			if(delete.equals("Y")||delete.equals("y")){
				System.out.println("삭제되었습니다.");
				movieList.remove(number - 1);
				managerLogin();
			}else if(delete.equals("N")||delete.equals("n")){
				managerLogin();
			}else{
				System.out.println("(Y/N)만 입력해주세요.");
				deleteMovie();
			}
		}
	}

	@Override
	public void printMovie() {
		Scanner s = new Scanner(System.in);
		// 영화 목록
		// 영화 목록 예시 뿌려줌. 원래 데이터베이스로 가져와야함
		ArrayList<MovieVO> movieList = userDao.selectMovieList();
		showBar("    영화  목록",124,7);
		System.out.println("==============================================================================================================================");
		System.out.println("No" + "\t" + "제목" + "\t\t" + "감독" + "\t\t" + "배우\t\t평점"+"\t\t\t" + "줄거리" ); 
		System.out.println("==============================================================================================================================");
		for(int i = 0; i < movieList.size(); i++){
			MovieVO movie = movieList.get(i);
			System.out.println(i + 1 + "\t" + movie.getMovieName() + "\t\t"  + movie.getDirector() + "\t\t" + movie.getActors() + "\t" + movie.getScore() + "\t" + movie.getStory());
		}

		System.out.println("==============================================================================================================================");
	}

	@Override
	public void selectMovie() {
		printMovie();
		manageMovie();
	}

	/**/
	public void userNotice(){
		// 사용자 공지사항
		selectNotice();

		Scanner s = new Scanner(System.in);
		System.out.print("게시글 번호를 입력해주세요 (뒤로가기:9) > ");
		int noticeNo = Integer.parseInt(s.nextLine());
		if(noticeNo == 9){
			userLogin();
		}
		ArrayList<NoticeVO> noticeList = userDao.selectNoticeList();
		System.out.println("------------------------------------------");
	//	System.out.println("No" + "\t" + "내용"); 
		System.out.println("No" + "\t\t        " + "내용"); 
		System.out.println("------------------------------------------");

		NoticeVO notice = noticeList.get(noticeNo-1);
		System.out.println(noticeNo + "\t" + notice.getContents());

		System.out.println("------------------------------------------");
		System.out.print( "(뒤로가기 : 9) > ");
		int back = Integer.parseInt(s.nextLine());
		if(back == 9){
			userLogin();
		}

	}

	@Override
	public void manageNotice() {
		// 공지사항 관리
		int menu;
		Scanner s = new Scanner(System.in);

		showBar("공지사항 관리");

		selectNotice();

		System.out.println("----------------- 메뉴 ------------------");
		System.out.println("1. 공지사항 등록");
		System.out.println("2. 공지사항 삭제");
		System.out.println("3. 뒤로 가기");
		System.out.println("---------------------------------------");
		System.out.println("메뉴에 해당하는 번호 입력 > ");
		menu = Integer.parseInt(s.nextLine());

		switch(menu){
		case 1:	// 공지사항 등록
			insertNotice();
			break;

		case 2:	// 공지사항 삭제
			deleteNotice();
			break;

		case 3: // 뒤로 가기
			managerLogin();
			break;

		default : 
			System.out.println("잘못 입력하셨습니다.");
			manageNotice();
		}
	}

	@Override
	public void insertNotice() {
		// 공지사항 등록
		Scanner s = new Scanner(System.in);
		showBar("공지사항 등록");
		System.out.print("제목 입력 : ");
		String title = s.nextLine();
		System.out.print("내용 입력 : ");
		String contents = s.nextLine();
		System.out.println("저장되었습니다.");

		//데이터베이스에 저장하는 코드 들어갈 자리
		NoticeVO nv = new NoticeVO();
		Session ses = new Session();
		nv.setTitle(title);
		nv.setContents(contents);
		nv.setUserID(ses.loginUser.getId());


		userDao.insertNotice(nv);

		managerLogin();
	}

	@Override
	public void deleteNotice() {

		// 공지사항 삭제

		ArrayList<NoticeVO> noticeList = userDao.selectNoticeList();
		if(noticeList.isEmpty()){
			System.out.println("삭제할 영화가 없습니다.");
			managerLogin();
		} else {
			Scanner s = new Scanner(System.in);
			showBar("공지사항 삭제");
			System.out.print("삭제할 공지사항 번호를 입력하세요: "); 
			int number = Integer.parseInt(s.nextLine());
			System.out.println(number + "번을 삭제하시겠습니까? (Y/N)");
			String delete = s.nextLine();
			if(delete.equals("Y") || delete.equals("y")){
				noticeList.remove(number - 1);
				System.out.println("삭제되었습니다.");
			}else if(delete.equals("N") || delete.equals("n")){
				managerLogin();
			}else{
				System.out.println("(Y/N)만 입력해주세요.");
				deleteNotice();
			}
			managerLogin();
		}
	}

	@Override
	public void selectNotice() {
		// 공지사항 목록
		Scanner s = new Scanner(System.in);
		// 공지사항으로 변경해준다.

		ArrayList<NoticeVO> noticeList = userDao.selectNoticeList();
		showBar("   공지사항",40,2);
		System.out.println("------------------------------------------");
		System.out.println("No" + "\t\t        " + "제목"); 
		System.out.println("------------------------------------------");
		for(int i = 0; i < noticeList.size(); i++){
			NoticeVO notice = noticeList.get(i);
			System.out.println(i + 1 + "\t" + notice.getTitle());
		}
		System.out.println("------------------------------------------");
		System.out.println();

	}


	@Override
	public void reserveMovie() {
		// 영화예매
		printMovie();

		Scanner s = new Scanner(System.in);

		System.out.print("선택할 영화 번호를 입력하세요 (9:뒤로가기) > ");
		String choiceNumber = s.nextLine();
		if(choiceNumber.equals("9")){
			userLogin();
		}else if(!(choiceNumber.equals("1") || choiceNumber.equals("2") || choiceNumber.equals("3"))){
			System.out.println("잘못 입력하셨습니다.");
			reserveMovie();
		}
		MovieVO mv = new MovieVO();
		Database db = Database.getInstance();
		mv = db.tb_movie.get(Integer.parseInt(choiceNumber) - 1);
		System.out.println(mv.getMovieName() + "(이)가 선택되었습니다.");
	//	System.out.println("-------------------------------------------");
		choiceDate(mv);
		//로직 필요함

	}

	@Override
	public void reserveList() {
		// 예매 내역
		Scanner s = new Scanner(System.in);
		int menu;

		showBar("               예매 내역",53,2);
		System.out.println("-------------------------------------------------------");






		Database db = Database.getInstance();
		ArrayList<ReserveVO> reserveDB = db.tb_reserve;
		ArrayList<ShowingVO> showingDB = db.tb_showing;
		ArrayList<MovieVO> movieDB = db.tb_movie;
		ArrayList<SeatVO> seatDB = db.tb_seat;


		int count = 0;

		// 사용자 아이디가 현재 로그인한 아이디인 객체 추출
		ArrayList<ReserveVO> userReserve = new ArrayList<ReserveVO>();
		UserVO loginUser = Session.loginUser;
		for(int i = 0; i < reserveDB.size(); i++){
			if(reserveDB.get(i).getUserID().equals(loginUser.getId())){
				userReserve.add(reserveDB.get(i));
			}
		}

		ArrayList<ArrayList<String>> printReserve = new ArrayList<ArrayList<String>>();
		ArrayList<String> movieList = new ArrayList<>();

		if(userReserve.size() != 0){
			// 영화 제목 추가
			for(int i = 0; i < userReserve.size(); i++){
				for(int j = 0; j < showingDB.size(); j++){
					if(userReserve.get(i).getShowingCode() == showingDB.get(j).getShowingCode()){
						for(int k = 0; k < movieDB.size(); k++){
							if(showingDB.get(j).getMovieCode() == movieDB.get(k).getMovieCode()){
								movieList.add(movieDB.get(k).getMovieName());
							}
						}
					}
				}
			}
			//System.out.println(movieList);
			printReserve.add(movieList);
			count = 0;

			ArrayList<String> screenList = new ArrayList<>();
			ArrayList<String> seatList = new ArrayList<>();
			// 상영관 및 좌석 추가
			for(int i = 0; i < userReserve.size(); i++){
				for(int j = 0; j < seatDB.size(); j++){
					if(userReserve.get(i).getSeatCode() == seatDB.get(j).getSeatCode()){
						screenList.add(Integer.toString(seatDB.get(j).getScreen()));
						seatList.add(seatDB.get(j).getSeatNum());
					}
				}
			}
			printReserve.add(screenList);
			printReserve.add(seatList);

			count = 0;

			ArrayList<String> dateList = new ArrayList<>();
			ArrayList<String> timeList = new ArrayList<>();
			// 날짜 및 시간 추가
			for(int i = 0; i < userReserve.size(); i++){
				for(int j = 0; j < showingDB.size(); j++){
					if(userReserve.get(i).getShowingCode() == showingDB.get(j).getShowingCode()){
						dateList.add(showingDB.get(j).getDate());
						timeList.add(showingDB.get(j).getTime());
					}
				}
			}
			printReserve.add(dateList);
			printReserve.add(timeList);
			count = 0;
			// 출력
			System.out.println("순번 \t 영화 제목 \t\t 상영관 \t 좌석 \t  날짜 \t  시간");
			System.out.println("-------------------------------------------------------");
			for(int i = 0; i < printReserve.get(0).size(); i++){
				System.out.print(++count +".");
				for(int j = 0; j < printReserve.size(); j++){
					System.out.print("\t "+printReserve.get(j).get(i));
				}
				System.out.println();
			}
		} else {
			System.out.println("예매한 영화가 없습니다.");
		}



		System.out.println("------------------------- 메뉴 -------------------------");
		System.out.println("1. 영화 취소");
		System.out.println("2. 뒤로 가기");
		System.out.println("-------------------------------------------------------");
		System.out.print("메뉴에 해당하는 번호 입력 > ");
		menu = Integer.parseInt(s.nextLine());

		switch(menu){

		case 1:	// 영화 취소
			reserveDelete();
			break;

		case 2: // 뒤로 가기
			userLogin();
			break;

		default : 
			System.out.println("잘못 입력하셨습니다.");
			reserveList();
		}
	}


	@Override
	public void reserveDelete() {
		// 예매 취소
		Scanner s = new Scanner(System.in);
		// 로직 필요
		ArrayList<ReserveVO> reserveList = userDao.selectReserveList();
		if(reserveList.isEmpty()){
			System.out.println("삭제할 예매내역이 없습니다.");
			userLogin();
		} else {

			System.out.println("취소하시겠습니까? (Y/N)");
			String delete = s.nextLine();

			if(delete.equals("Y") || delete.equals("y")){
				//	noticeList.remove(number - 1);
				System.out.println("취소되었습니다.");
				reserveList.remove(reserveList.removeAll(reserveList));
				//reserveList.remove(Integer.parseInt(delete));
			}else if(delete.equals("N") || delete.equals("n")){
				reserveList();
			}else{
				System.out.println("(Y/N)만 입력해주세요.");
				reserveDelete();
			}
			reserveList();
		}



		/*		// 영화 삭제
		ArrayList<MovieVO> movieList = userDao.selectMovieList();
		if(movieList.isEmpty()){
			System.out.println("삭제할 영화가 없습니다.");
			managerLogin();
		} else {
			Scanner s = new Scanner(System.in);
			printMovie();
			showBar("영화 삭제");

			System.out.print("삭제할 영화 번호를 입력하세요: "); 
			int number = Integer.parseInt(s.nextLine());

			System.out.println(number + "번을 삭제하시겠습니까? (Y / N)");
			String delete = s.nextLine();

			if(delete.equals("Y")||delete.equals("y")){
				System.out.println("삭제되었습니다.");
				movieList.remove(number - 1);
				managerLogin();
			}else if(delete.equals("N")||delete.equals("n")){
				managerLogin();
			}else{
				System.out.println("(Y/N)만 입력해주세요.");
				deleteMovie();
			}
		}*/





	}

	@Override
	public void myPage() {
		// 마이페이지
		int menu;

		Scanner s = new Scanner(System.in);

		showBar("마이페이지");

		System.out.println("---------------------------------------");
		System.out.println("1. 내정보");
		System.out.println("2. 내가 작성한 리뷰 및 평점");
		System.out.println("3. 내가 본 영화");
		System.out.println("4. 뒤로가기");
		System.out.println("---------------------------------------");
		System.out.println("메뉴에 해당하는 번호 입력 >");
		menu = Integer.parseInt(s.nextLine());

		switch (menu) {
		case 1:
			myInfo();
			break;
		case 2:
			selecetReview();
			break;
		case 3:
			myMovieList();
			break;
		case 4:
			userLogin();
			break;

		default : 
			System.out.println("잘못 입력하셨습니다.");
			myPage();
		}

	}


	@Override
	public void myInfo() {
		// 내정보
		Scanner s = new Scanner(System.in);

		Session ses = new Session();
		int menu;

		showBar("내정보");
		System.out.println("---------------------------------------");
		System.out.println("아이디 : " + ses.loginUser.getId());
		System.out.println("비밀번호 : " + ses.loginUser.getPassword());
		System.out.println("이름 : " + ses.loginUser.getName());
		System.out.println("생년월일 : " + ses.loginUser.getBirthdate());
		System.out.println("---------------------------------------");
		System.out.println("9. 뒤로가기");
		menu = Integer.parseInt(s.nextLine());

		if(menu == 9){
			myPage();
		}

	}

	@Override
	public void printReview(){

		ReviewVO review = new ReviewVO();

		showBar("                리뷰 및 평점", 75, 3);

		ArrayList<ReviewVO> reviewList = userDao.selectReviewList();
		ArrayList<ReserveVO> reserveList = userDao.selectReserveList(); 
		ArrayList<MovieVO> mvList = userDao.selectMovieList();
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("No" + "\t영화 제목 " + "\t\t작성자 " + "\t\t리뷰 내용 "+ "\t\t\t" +"평점 ");
		System.out.println("----------------------------------------------------------------------------");
		
		String movieName = null;
		
		for(int i = 0; i < reviewList.size(); i++){
			review = reviewList.get(i);
			for(int j = 0; j < mvList.size(); j++){
				if(review.getMovieCode() == mvList.get(j).getMovieCode()){
					movieName = mvList.get(j).getMovieName();
					break;
				}
			}
			//System.out.println("\r" + (i + 1) +" 영화 제목 : " + movieName + "\t작성자 : " + review.getUserID() + "\t리뷰 내용 : "+ review.getContents() + "\r\t" +"평점 : " + review.getScore() + "\r");
			System.out.println("\r" + (i + 1) + "\t" + movieName + "\t\t" + review.getUserID() + "\t" + review.getContents() + "\t" + review.getScore() + "\r");
		}
		System.out.println("----------------------------------------------------------------------------");
	}
	
	
	@Override
	public void selecetReview() {
		Scanner s = new Scanner(System.in);

		ReviewVO review = new ReviewVO();

		printReview();

		System.out.println("9. 뒤로가기");

		int menu = Integer.parseInt(s.nextLine());

		while (menu != 9){
			System.out.println("잘못 입력하셨습니다.");
			menu = Integer.parseInt(s.nextLine());
		}
		myPage();
	}
	@Override
	public void selecetReview1() {
		Scanner s = new Scanner(System.in);

		ReviewVO review = new ReviewVO();

		printReview();

		System.out.println("9. 뒤로가기");

		int menu = Integer.parseInt(s.nextLine());

		while (menu != 9){
			System.out.println("잘못 입력하셨습니다.");
			menu = Integer.parseInt(s.nextLine());
		}
		
		userLogin();
		
	}

	@Override
	public void myMovieList() {
		// 내가 본 영화

		Scanner s = new Scanner(System.in);

		Session ses = new Session();
		int menu;

		showBar("내가 본 영화");
		System.out.println("---------------------------------------");

		//-----------------------------------------------------------------------------

		Database db = Database.getInstance();
		ArrayList<ReserveVO> reserveDB = db.tb_reserve;
		ArrayList<ShowingVO> showingDB = db.tb_showing;
		ArrayList<MovieVO> movieDB = db.tb_movie;
		ArrayList<SeatVO> seatDB = db.tb_seat;


		int count = 0;

		// 사용자 아이디가 현재 로그인한 아이디인 객체 추출
		ArrayList<ReserveVO> userReserve = new ArrayList<ReserveVO>();
		UserVO loginUser = Session.loginUser;
		for(int i = 0; i < reserveDB.size(); i++){
			if(reserveDB.get(i).getUserID().equals(loginUser.getId())){
				userReserve.add(reserveDB.get(i));
			}
		}

		ArrayList<ArrayList<String>> printReserve = new ArrayList<ArrayList<String>>();
		ArrayList<String> movieList = new ArrayList<>();

		if(userReserve.size() != 0){
			// 영화 제목 추가
			for(int i = 0; i < userReserve.size(); i++){
				for(int j = 0; j < showingDB.size(); j++){
					if(userReserve.get(i).getShowingCode() == showingDB.get(j).getShowingCode()){
						for(int k = 0; k < movieDB.size(); k++){
							if(showingDB.get(j).getMovieCode() == movieDB.get(k).getMovieCode()){
								movieList.add(movieDB.get(k).getMovieName());
							}
						}
					}
				}
			}

			printReserve.add(movieList);
			count = 0;

			ArrayList<String> screenList = new ArrayList<>();
			ArrayList<String> seatList = new ArrayList<>();
			// 상영관 및 좌석 추가
			for(int i = 0; i < userReserve.size(); i++){
				for(int j = 0; j < seatDB.size(); j++){
					if(userReserve.get(i).getSeatCode() == seatDB.get(j).getSeatCode()){
						screenList.add(Integer.toString(seatDB.get(j).getScreen()));
						seatList.add(seatDB.get(j).getSeatNum());
					}
				}
			}
			printReserve.add(screenList);
			printReserve.add(seatList);

			count = 0;

			ArrayList<String> dateList = new ArrayList<>();
			ArrayList<String> timeList = new ArrayList<>();
			// 날짜 및 시간 추가
			for(int i = 0; i < userReserve.size(); i++){
				for(int j = 0; j < showingDB.size(); j++){
					if(userReserve.get(i).getShowingCode() == showingDB.get(j).getShowingCode()){
						dateList.add(showingDB.get(j).getDate());
						timeList.add(showingDB.get(j).getTime());
					}
				}
			}
			printReserve.add(dateList);
			printReserve.add(timeList);
			count = 0;
			// 출력
			System.out.println("순번 \t\t 영화 제목 ");    // \t\t 상영관 \t 좌석 \t  날짜 \t\t  시간
			System.out.println("---------------------------------------");
			for(int i = 0; i < printReserve.get(i).size(); i++){
				System.out.print(++count +".\t\t");
				/*for(int j = 0; j < printReserve.size(); j++){
					System.out.print("\t "+printReserve.get(j).get(i));
				}*/
				System.out.println(printReserve.get(0).get(i));

			}
		} else {
			System.out.println("내가 본 영화가 없습니다.");
		}
		/*///
		System.out.println();
		System.out.println("리뷰를 작성 및 삭제 할 영화를 선택하세요");
		int cm = Integer.parseInt(s.nextLine());*/


		//-----------------------------------------------------------------------------

		System.out.println("----------------- 메뉴 ------------------");
		System.out.println("1. 리뷰 및 평점 작성");
		System.out.println("2. 리뷰 및 평점 삭제");
		System.out.println("3. 뒤로가기");
		System.out.println("---------------------------------------");
		System.out.print("메뉴에 해당하는 번호 입력 >");
		menu = Integer.parseInt(s.nextLine());

		switch (menu) {
		case 1:
			insertReview();
			break;
		case 2:
			deleteReview();
			break;
		case 3:
			myPage();
			break;

		default : 
			System.out.println("잘못 입력하셨습니다.");
			myMovieList();
		}

	}




	@Override
	public void insertReview() {
		// 평점 및 리뷰 등록
		Scanner s = new Scanner(System.in);
		int score;
		int choiceMv;
		ArrayList<MovieVO> mvList = userDao.selectMovieList();
		ArrayList<ReserveVO> reserveList = userDao.selectReserveList();
		ArrayList<ShowingVO> showingList = userDao.selectShowingList();
		System.out.println();
	//	System.out.println("작성할 영화 : ");
		
		for(int i = 0; i < mvList.size(); i++){
		//	System.out.print("\t" + (i + 1));
			System.out.print(i + 1);
			System.out.println("\t" + mvList.get(i).getMovieName());
		}
		
		System.out.println();
		System.out.print("리뷰를 작성할 영화를 선택해주세요 > ");
		
		ArrayList<MovieVO> tempArrList = new ArrayList<>();
		for(int i = 0; i < reserveList.size(); i++){
			for(int j = 0; j < showingList.size(); j++){
				if(reserveList.get(i).getShowingCode() == showingList.get(j).getShowingCode()){
					for(int k = 0; k < mvList.size(); k++){
						if(showingList.get(j).getMovieCode() == mvList.get(k).getMovieCode()){
							tempArrList.add(mvList.get(k));
						}
					}
				}
			}
		}
		
		do{
			choiceMv = Integer.parseInt(s.nextLine());
			if(0 < choiceMv && choiceMv < mvList.size() + 1){
				boolean checkSeen = false;
				for(int i = 0; i < tempArrList.size(); i++){
					if(tempArrList.get(i).getMovieCode() == mvList.get(choiceMv - 1).getMovieCode()){
						checkSeen = true;
						break;
					}
				}
				if(checkSeen){
					break;
				} else {
					System.out.println("해당 영화의 시청 내역이 없습니다. 다시 입력해주세요.");
				}
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}while(true);
		
		System.out.print("리뷰 내용 입력 : ");
		String contents = s.nextLine();
		
		while(true){
			System.out.print("평점 입력 : ");
			score = Integer.parseInt(s.nextLine());

			if(score > 10 || score < 1){
				System.out.println("1부터 10까지만 입력해주세요");
				continue;
			}
			break;
		}

		
		//작성한 리뷰 및 평점이 데이터베이스로 들어가는 코드
		ReviewVO review = new ReviewVO();
		review.setMovieCode(mvList.get(choiceMv - 1).getMovieCode());
		review.setContents(contents);
		review.setScore(score);
		review.setUserID(Session.loginUser.getId());
		userDao.insertReview(review);

		mvList.get(choiceMv).setScore(mvList.get(choiceMv - 1).getMovieCode());
		
		
		System.out.println("---------------------------------------");
		System.out.println("리뷰 및 평점이 등록되었습니다.");
		System.out.println("---------------------------------------");
		System.out.println("9. 뒤로가기");
		int menu = Integer.parseInt(s.nextLine());

		if(menu == 9){
			myMovieList();
		}


	}

	@Override
	public void deleteReview() {
		// 폄정 및 리뷰 삭제

		ArrayList<ReviewVO> reviewList = userDao.selectReviewList();
		if(reviewList.isEmpty()){
			System.out.println("삭제할 리뷰가 없습니다.");
			userLogin();
		} else {
			Scanner s = new Scanner(System.in);
			printReview();
			showBar("리뷰 및 평점 삭제",40,2);

			System.out.print("삭제할 리뷰 번호를 입력하세요: "); 
			int number = Integer.parseInt(s.nextLine());

			System.out.println(number + "번을 삭제하시겠습니까? (Y / N)");
			String delete = s.nextLine();

			if(delete.equals("Y")||delete.equals("y")){
				System.out.println("삭제되었습니다.");
				reviewList.remove(number - 1);
				myMovieList();
			}else if(delete.equals("N")||delete.equals("n")){
				myMovieList();
			}else{
				System.out.println("(Y/N)만 입력해주세요.");
				deleteReview();
			}
		}

	}

	public void choiceDate(MovieVO mv) {
		Scanner s = new Scanner(System.in);

		int movieCode = mv.getMovieCode();
		ShowingVO sv = new ShowingVO();

		Date day = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM월dd일");
		String today = format.format(day);
		String tomorrow = format.format(day.getTime() + (long)( 1000 * 60 * 60 * 24 ));
		String tomorrow1 = format.format(day.getTime() + (long)( 1000 * 60 * 60 * 48));

		String date;

		while(true){
			showBar("  날짜 선택",40,2);
			System.out.println("1. " + today + "\t" +  "2. " + tomorrow + "\t" + " 3. " + tomorrow1);
			System.out.println();
			System.out.print("날짜를 선택해주세요 - 최대 3일 이전 (9:뒤로가기) > ");
			date = s.nextLine();

			if(date.equals("1")){
				date = today;
			}else if(date.equals("2")){
				date = tomorrow;
			}else if(date.equals("3")){
				date = tomorrow1;
			}else if(date.equals("9")){
				reserveMovie();
			}else{
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				continue;
			}
			break;
		}

		// 로직 2000-00-00
		System.out.println("선택된 날짜는 " + date +"입니다."); //확인용
	//	System.out.println("---------------------------------------");
		choiceTime(mv, date , movieCode);
	}


	public void choiceTime(MovieVO mv ,String date , int movieCode) {

		showBar("  시간 선택",40,2);
		Scanner s = new Scanner(System.in);
		int countt = 0;
		ArrayList<ShowingVO> showDB = Database.getInstance().tb_showing;
		int showDBLength = showDB.size();
		for(int i = 0; i < showDBLength; i++){
			if(mv.getMovieCode() == showDB.get(i).getMovieCode() && date.equals(showDB.get(i).getDate())){
				System.out.print("시간 : " + showDB.get(i).getTime() + "\t");
			}
		}

		//동규꺼
		System.out.println();
		System.out.println();
		System.out.print("원하는 시간대를 선택해주세요 (9:뒤로가기) > "); //No 입력
		String time = s.nextLine();
		if(time.equals("9")){
			choiceDate(mv);
		}
		if(time.equals("12:40") || time.equals("14:50") || time.equals("17:00")){


			int showingCode = 0;
			int screenNum = 0;

			for(int i = 0; i < showDBLength; i++){

				if(movieCode == showDB.get(i).getMovieCode() && date.equals(showDB.get(i).getDate())&& time.equals(showDB.get(i).getTime())){
					showingCode = showDB.get(i).getShowingCode();
					screenNum = showDB.get(i).getScreen();
					/*				System.out.println(movieCode +"=="+ showDB.get(i).getMovieCode() );
				System.out.println(date +"=="+ showDB.get(i).getDate() );
				System.out.println(time +"=="+ showDB.get(i).getTime() );*/
					break;
				}
			}

			//System.out.println(showingCode);
			System.out.println("선택된 시간은 " + time +"입니다."); //확인용
			choiceSeat(showingCode, mv, date, movieCode, screenNum);

		}else{
			System.out.println();
			System.out.println("ERROR : 잘못 입력하셨습니다");
		//	System.out.println("-------------------------------------------");
			choiceTime(mv ,date , movieCode);
		}
	}
	
	/*		for(int i = 0; i < showDBLength; i++){
			if(mv.getMovieCode() == showDB.get(i).getMovieCode() && date.equals(showDB.get(i).getDate())){
				System.out.println("상영관 : " + showDB.get(i).getScreen() + "\t 시간 : " + showDB.get(i).getTime());
			}
		}*/
	
	
	// 좌석 선택
	public void choiceSeat(int showingCode, MovieVO mv ,String date , int movieCode, int screenNum){
		Scanner s = new Scanner(System.in);

		ArrayList<ReserveVO> reserveDB = Database.getInstance().tb_reserve;
		ArrayList<SeatVO> seatDB = Database.getInstance().tb_seat;
		boolean[] seat = new boolean[45];
		String[] seatTemp = new String[45];
		int count = 0;
		String seatStr = null;
		int seatCode = 0;

		


		// 4. 좌석 선택 □ ■
		// 2 5 2
		//		int[] reservedSeat = {10, 15, 16};
		//		int[][] seat = new int[5][9];
		//		for() {
		//			for() {
		//				
		//			}
		//		}
		showBar("            인원 및 좌석 선택",40,1);
		int HowManyReserve;		// 인원 수
		
		while(true){	// 인원 수 체크
			System.out.print("인원을 입력해 주세요.(최대 5명까지) > (9.뒤로가기) ");
			HowManyReserve = Integer.parseInt(s.nextLine());
			if(HowManyReserve == 9){
				choiceTime(mv ,date , movieCode);
			}
			if(0 < HowManyReserve || HowManyReserve <= 5){
				break;
			}else{
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		
		String reserveList[] = new String[HowManyReserve]; // 예매 할 좌석 저장용 배열
		String regexSeat = "^[H-L]{1}[1-9]{1}$";			// 좌석 선택용 정규식
		
		String reserveSeat = null;
		for(int i = 0; i < reserveDB.size(); i++){
			if(showingCode == reserveDB.get(i).getShowingCode()){
				for(int j = 0; j < seatDB.size(); j++){
					if(reserveDB.get(i).getSeatCode() == seatDB.get(j).getSeatCode()){
						seatStr = seatDB.get(j).getSeatNum();
						seatTemp[count++] = seatStr;
						break;
					}
				}
			}
		}

		for(int i = 0; i < seatTemp.length; i++){
			if(seatTemp[i] != null){
				int row = seatTemp[i].charAt(0) - 72;
				int col = seatTemp[i].charAt(1) - '0';
				seat[row * 9 + col - 1] = true;
			}
		}
		System.out.println();
		//	위에 좌석 번호 출력
		System.out.print("   ");
		for(int i = 0; i < 9; i++){
			if(i == 2 || i == 7){
				System.out.print("   ");
			}
			System.out.print(i+1 + " ");
		}
		for(int i = 0; i < seat.length; i++){
			if(i % 9 == 0){
				System.out.println();
				char tmp = (char)((i / 9) + 72);
				System.out.print(tmp + "  ");
			}
			if(i % 9 == 2){
				System.out.print("   ");
			}else if(i % 9 == 7){
				System.out.print("   ");
			}

			if(seat[i]){
				System.out.print("■ ");
			} else{
				System.out.print("□ ");
			}
		}
		System.out.println();
		
		for(int i = 0; i < reserveList.length; i++){
			while(true){
				System.out.println();
				System.out.print("좌석을 입력해 주세요 > (9.뒤로가기) ");
				reserveSeat = s.nextLine();
				
				if(reserveSeat.equals("9")){
					choiceTime(mv ,date ,movieCode);
				} else if(!Pattern.matches(regexSeat, reserveSeat)){
					System.out.println("잘못 입력하셨습니다.");
				} else{
					reserveList[i] = reserveSeat;
					break;
				}
			}
		}


		// 로직
		System.out.println("예매하시겠습니까? (Y/N) > ");
		String isReserved = s.nextLine();

		if(isReserved.equals("Y") || isReserved.equals("y")) {
			for(int i = 0; i < reserveList.length; i++){
				SeatVO seatvo = new SeatVO();
				seatvo.setSeatNum(reserveList[i]);
				System.out.println("예약 좌석: " + reserveList[i]);
				int j = 0;
				
				for(j = 0; j < reserveDB.size(); j++){	//  예매 코드 저장용
					boolean a = false;
					for(int k = 0; k < reserveDB.size(); k++){
						if(j + 1 == reserveDB.get(k).getReserveCode()){
							a = true;
							break;
						}
					}
					if(!a){
						break;
					}
				}
				
				ReserveVO rv = new ReserveVO();
				rv.setReserveCode(j + 1);
				rv.setUserID(Session.loginUser.getId());
				int tempRow = reserveList[i].charAt(0) - 72;
				int tempCol = reserveList[i].charAt(1) - '0';
				seatCode = (45* (screenNum - 1)) + tempRow * 9 + tempCol;
				rv.setSeatCode(seatCode);
				
				rv.setShowingCode(showingCode);
				userDao.reserveMovie(rv);
				
				System.out.println("------------- 예매 내역 ---------------");
				System.out.println("\r영화 제목 : " + mv.getMovieName() + "\r상영관 번호 : " + screenNum + "\r좌석 번호 : " + reserveList[i] + "\r사용자 아이디 : " + Session.loginUser.getId());
				System.out.println("예매되었습니다.");
				System.out.println("---------------------------------------");
			}
			
			for(int i = 0; i < reserveDB.size(); i++){
				if(showingCode == reserveDB.get(i).getShowingCode()){
					for(int j = 0; j < seatDB.size(); j++){
						if(reserveDB.get(i).getSeatCode() == seatDB.get(j).getSeatCode()){
							seatStr = seatDB.get(j).getSeatNum();
							seatTemp[count++] = seatStr;
							break;
						}
					}
				}
			}

			for(int i = 0; i < seatTemp.length; i++){
				if(seatTemp[i] != null){
					int row = seatTemp[i].charAt(0) - 72;
					int col = seatTemp[i].charAt(1) - '0';
					seat[row * 9 + col - 1] = true;
				}
			}
			//	위에 좌석 번호 출력
			System.out.print("   ");
			for(int i = 0; i < 9; i++){
				if(i == 2 || i == 7){
					System.out.print("   ");
				}
				System.out.print(i+1 + " ");
			}
			for(int i = 0; i < seat.length; i++){
				if(i % 9 == 0){
					System.out.println();
					char tmp = (char)((i / 9) + 72);
					System.out.print(tmp + "  ");
				}
				if(i % 9 == 2){
					System.out.print("   ");
				}else if(i % 9 == 7){
					System.out.print("   ");
				}

				if(seat[i]){
					System.out.print("■ ");
				} else{
					System.out.print("□ ");
				}
			}
			System.out.println();
			
			userLogin();
			
		} else if(isReserved.equals("N") || isReserved.equals("n")) {
			choiceSeat(showingCode, mv, date, movieCode, screenNum);
		}

	}
	
	public double getScoreAverage(int movieCode){
		double avg = 0;
		int count = 0;
		
		ArrayList<ReviewVO> reviewList = userDao.selectReviewList();
		
		
		for(int i = 0; i < reviewList.size(); i++){
			if(movieCode == reviewList.get(i).getMovieCode()){
				
				avg += reviewList.get(i).getScore();
				System.out.println(avg);
				count++;
			}
		}
		avg = (int)(avg / (double)count * 10 + 0.5) / 10.0;
		
		return avg;
	}


	/*	@Override
	public void choiceMovie(MovieVO mv) {
		Scanner s = new Scanner(System.in);

		System.out.println(mv.getMovieName() + "(이)가 선택되었습니다.");
		int movieCode = mv.getMovieCode();
		ShowingVO sv = new ShowingVO();

		Date day = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM월dd일");
		String today = format.format(day);
		String tomorrow = format.format(day.getTime() + (long)( 1000 * 60 * 60 * 24 ));
		String tomorrow1 = format.format(day.getTime() + (long)( 1000 * 60 * 60 * 48));

		String date;

		while(true){

		System.out.print("날짜를 선택해주세요 (최대 3일 이전) > "
				+ "1. " + today + " 2. " + tomorrow + " 3. " + tomorrow1);
		date = s.nextLine();

			if(date.equals("1")){
				date = today;
			}else if(date.equals("2")){
				date = tomorrow;
			}else if(date.equals("3")){
				date = tomorrow1;
			}else{
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				continue;
			}
			break;
		}

		// 로직 2000-00-00
		System.out.println("선택된 날짜는 " + date +"입니다."); //확인용



		int countt = 0;
		ArrayList<ShowingVO> showDB = Database.getInstance().tb_showing;
		int showDBLength = showDB.size();
		for(int i = 0; i < showDBLength; i++){
			if(mv.getMovieCode() == showDB.get(i).getMovieCode() && date.equals(showDB.get(i).getDate())){
				System.out.println(++countt + "\t 시간 : " + showDB.get(i).getTime());
			}
		}
		//동규꺼
		System.out.print("원하는 시간대를 선택해주세요 > "); //No 입력
		String time = s.nextLine();

		// 3. 인원 선택
		System.out.print("인원을 입력해주세요 > "); 
		String member = s.nextLine();




		// 2. 상영시간 선택
//		System.out.println("---------------------------------------");
//		System.out.println("No" + "\t관" + "\t상영시간" + "\t잔여좌석/전체좌석"); 
//		System.out.println("---------------------------------------");
//		System.out.println(" 1" + "\t1관" + "\t09:40" + "\t01/45");
//		System.out.println(" 2" + "\t1관" + "\t10:20" + "\t10/45");
//		System.out.println(" 3" + "\t2관" + "\t12:40" + "\t14/45");
//		System.out.println(" 4" + "\t2관" + "\t14:10" + "\t20/45");
//		System.out.println(" 5" + "\t5관" + "\t15:40" + "\t22/45");
//		System.out.println("---------------------------------------");
//		System.out.println();
//		// 로직
//		System.out.print("원하는 시간대를 선택해주세요 > "); //No 입력
//		String time = s.nextLine();
//		System.out.println("예매내역 : " + "5" + "관 - " + "15:40");
//	
//		// 3. 인원 선택
//		System.out.print("인원을 입력해주세요 > "); 
//		String member = s.nextLine();
//		// 로직
//		System.out.println("예약인원 : " + "2" + "명");


		int showingCode = 0;
		for(int i = 0; i < showDBLength; i++){
			System.out.println(movieCode +"=="+ showDB.get(i).getMovieCode() );
			System.out.println(date +"=="+ showDB.get(i).getDate() );
			System.out.println(time +"=="+ showDB.get(i).getTime() );
			if(movieCode == showDB.get(i).getMovieCode() && date.equals(showDB.get(i).getDate())&& time.equals(showDB.get(i).getTime())){
				showingCode = showDB.get(i).getShowingCode();
				break;
			}
		}

		for(int i = 0; i < showDBLength; i++){
			if(mv.getMovieCode() == showDB.get(i).getMovieCode() && date.equals(showDB.get(i).getDate())){
				System.out.println("상영관 : " + showDB.get(i).getScreen() + "\t 시간 : " + showDB.get(i).getTime());
				int screen = 
			}
		}


		ReserveVO rv = new ReserveVO();
		ArrayList<ReserveVO> reserveDB = Database.getInstance().tb_reserve;
		ArrayList<SeatVO> seatDB = Database.getInstance().tb_seat;
		boolean[] seat = new boolean[45];
		String[] seatTemp = new String[45];
		int count = 0;
		String seatStr = null;
		SeatVO seatvo = new SeatVO();

		for(int i = 0; i < reserveDB.size(); i++){
			System.out.println(showingCode + " == " + reserveDB.get(i).getShowingCode());
			if(showingCode == reserveDB.get(i).getShowingCode()){
				for(int j = 0; j < seatDB.size(); j++){
					if(reserveDB.get(i).getSeatCode() == seatDB.get(j).getSeatCode()){
						seatStr = seatDB.get(j).getSeatNum();
						System.out.println("디비 좌석 : " + seatStr);
						seatTemp[count++] = seatStr;
						break;
					}
				}
			}
		}

		for(int i = 0; i < seatTemp.length; i++){
			if(seatTemp[i] != null){
				int row = seatTemp[i].charAt(0) - 72;
				int col = seatTemp[i].charAt(1) - '0';
				seat[row * 9 + col - 1] = true;
			}
		}
			//	위에 좌석 번호 출력
		for(int i = 0; i < seat.length; i++){
			if(i % 9 == 0){
				System.out.println();
				char tmp = (char)((i / 9) + 72);
				System.out.print(tmp);
			}
			if(i % 9 == 2 || i % 9 == 7){
				System.out.print('\t');
			}
			if(seat[i]){
				System.out.print("■");
			} else{
				System.out.print("□");
			}
		}
		System.out.println();


		// 4. 좌석 선택 □ ■
		// 2 5 2
//		int[] reservedSeat = {10, 15, 16};
//		int[][] seat = new int[5][9];
//		for() {
//			for() {
//				
//			}
//		}
		System.out.print("좌석을 입력해 주세요 > ");
		String reserveSeat = s.nextLine();
		seatvo.setSeatNum(reserveSeat);

		ReserveVO rv1 = new ReserveVO();
		Session ses = new Session();
		rv1.setReserveCode(1);
		rv1.setSeatCode(1);
		rv1.setShowingCode(2);
		rv1.setUserID(ses.loginUser.getId()); 
		userDao.reserveMovie(rv1);

		//데이터베이스에 저장하는 코드 들어갈 자리
		NoticeVO nv = new NoticeVO();
	//	Session ses = new Session();
	//	nv.setTitle(title);
	//	nv.setContents(contents);
		//nv.setUserID(ses.loginUser.getId());
		userDao.insertNotice(nv);



		System.out.println("입력한 좌석: " + reserveSeat);


		// 로직
	//	System.out.println("예약좌석 : " + row1 + col1 + "," + row2 + col2);
		System.out.println("예매하시겠습니까? (Y/N) > ");
		String isReserved = s.nextLine();

		if(isReserved.equals("Y") || isReserved.equals("y")) {
			System.out.println("예매되었습니다.");
			userLogin();
		}else if(isReserved.equals("N") || isReserved.equals("n")) {
			//N이나n 입력시 (4. 좌석선택)으로 돌아가기
		}

	}*/





}
