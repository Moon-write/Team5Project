package kr.co.iei.dataApi.vo;

public class LiveData {
	private String checkDate; // 날짜
	private String checkCount; // 확진자수 
	private String checkDeath; // 사망자수
	
	public LiveData() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	public LiveData(String checkDate, String checkCount, String checkDeath) {
		super();
		this.checkDate = checkDate;
		this.checkCount = checkCount;
		this.checkDeath = checkDeath;
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
	public String getCheckDeath() {
		return checkDeath;
	}
	public void setCheckDeath(String checkDeath) {
		this.checkDeath = checkDeath;
	}	
}
