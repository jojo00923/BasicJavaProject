package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import data.Database;
import service.UserServiceImpl;
import vo.MovieVO;
import vo.NoticeVO;
import vo.ReserveVO;
import vo.ReviewVO;
import vo.ShowingVO;
import vo.UserVO;

public class UserDaoImpl implements UserDao {

	private static UserDaoImpl instance;
	
	private UserDaoImpl(){}
	
	public static UserDaoImpl getInstance(){
		if(instance == null){
			instance = new UserDaoImpl();
		}
		return instance;
	}
	
	Database database = Database.getInstance();
	
	@Override
	public UserVO selectUser(String key, String value) {
		for(int i = 0; i < database.tb_user.size(); i++){
			UserVO user = database.tb_user.get(i);
			
			if(key.equals("ID")){
				if(user.getId().equals(value)){
					return user;
				}
			}
		}
		return null;
	}

	@Override
	public void insertUser(UserVO user) {
		database.tb_user.add(user);
	}

	@Override
	public UserVO selectUser(HashMap<String, String> param) {
		UserVO rtnUser = null;
		
		for(int i = 0; i < database.tb_user.size(); i++){
			UserVO user = database.tb_user.get(i);
			boolean flag = true;
			
			Set<String> keys = param.keySet();
			for(String key : keys){
				String value = param.get(key);
				if(key.equals("ID")){
					if(!user.getId().equals(value)){
						flag = false;
					}
				} else if(key.equals("PASSWORD")){
					if(!user.getPassword().equals(value)){
						flag = false;
					}
				} else if(key.equals("NAME")){
					if(!user.getName().equals(value)){
						flag = false;
					}
				}
				
			}
			if(flag){
				rtnUser = user;
			}
		}
		return rtnUser;
	}

	@Override
	public ArrayList<UserVO> selectUserList() {
		return database.tb_user;
	}
	
	@Override
	public void insertMovie(MovieVO movie) {
		database.tb_movie.add(movie);
	}
	
	@Override
	public ArrayList<MovieVO> selectMovieList() {
		return database.tb_movie;
	}
	
	@Override
	public void insertNotice(NoticeVO notice) {
		database.tb_notice.add(notice);
	}
	
	@Override
	public ArrayList<NoticeVO> selectNoticeList() {
		return database.tb_notice;
	}

	@Override
	public void reserveMovie(ReserveVO reserve) {
		database.tb_reserve.add(reserve);
	}

	@Override
	public ArrayList<ReserveVO> selectReserveList() {
		return database.tb_reserve;
	}

	@Override
	public void insertReview(ReviewVO review) {
		database.tb_review.add(review);
	}

	@Override
	public ArrayList<ReviewVO> selectReviewList() {
		return database.tb_review;
	}

	@Override
	public ArrayList<ShowingVO> selectShowingList() {
		return database.tb_showing;
	}

}
