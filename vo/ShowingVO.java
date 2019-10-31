package vo;

import java.util.Date;

public class ShowingVO {
	private int showingCode;
	private int movieCode;
	private String date;
	private String time;
	private int screen;
	
	
	public int getShowingCode() {
		return showingCode;
	}
	public void setShowingCode(int showingCode) {
		this.showingCode = showingCode;
	}
	public int getMovieCode() {
		return movieCode;
	}
	public void setMovieCode(int movieCode) {
		this.movieCode = movieCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getScreen() {
		return screen;
	}
	public void setScreen(int screen) {
		this.screen = screen;
	}
}
