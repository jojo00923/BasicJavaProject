package vo;

public class ReserveVO {
	private int reserveCode;
	private String userID;
	private int showingCode;
	private int seatCode;
	
	
	public int getReserveCode() {
		return reserveCode;
	}
	public void setReserveCode(int reserveCode) {
		this.reserveCode = reserveCode;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getShowingCode() {
		return showingCode;
	}
	public void setShowingCode(int showingCode) {
		this.showingCode = showingCode;
	}
	public int getSeatCode() {
		return seatCode;
	}
	public void setSeatCode(int seatCode) {
		this.seatCode = seatCode;
	}
}
