package kr.co.iei.main.vo;

public class LiveData {
	private String checkDate; // 날짜
	private String checkCount; // 확진자수, 사망자수 어떤걸 담을지는 안정해짐
	
	public LiveData() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	public LiveData(String checkDate, String checkCount) {
		super();
		this.checkDate = checkDate;
		this.checkCount = checkCount;
	}

	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckCount() {
		return checkCount;
	}
	public void setCheckCount(String checkCount) {
		this.checkCount = checkCount;
	}
}
