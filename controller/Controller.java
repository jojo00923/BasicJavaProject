package controller;

import java.util.HashMap;
import java.util.Scanner;

import dao.UserDao;
import dao.UserDaoImpl;
import data.Session;
import service.UserService;
import service.UserServiceImpl;
import vo.UserVO;

public class Controller {
	
	
	UserService userService = UserServiceImpl.getInstance();

	
	public static void main(String[] args) {
		/*
		 * 조 소개 > 주제 소개 > 주제 선정 배경 > 프로그램 구조 > 시연
		 * 발표자 1명, 도우미 1명
		 * 
		 * Controller : 메뉴 선택
		 * Service : 메뉴 기능 수행
		 * Dao : 데이터베이스 접속
		 * VO : 데이터를 담는 클래스
		 * 
		 * VO의 장점 : 무엇이 저장되어 있는지 쉽게 알 수 있다.
		 * HashMap의 장점 : 클래스를 많이 만들 필요가 없다.
		 */
		
		new Controller().start();									
	}

	public void start() {
		Scanner s = new Scanner(System.in);
		
		String menu;
		
			System.out.println("----------------- 메뉴 ------------------");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("0. 프로그램 종료");
			System.out.println("---------------------------------------");
			System.out.print("메뉴에 해당하는 번호 입력 > ");
			
			menu = s.nextLine();
//			s.nextInt();// 버그가 있으므로 사용하지 말자.
			
			switch(menu){
			case "1":	// 회원가입
				userService.join();
				break;
				
			case "2":	// 로그인
				userService.login();
				
				break;
				
			case "0": // 프로그램 종료
				System.out.println("프로그램 종료");
				break;
			
			default : 
				System.out.println("잘못 입력하셨습니다.");
				start();
			}
	}


	

}
