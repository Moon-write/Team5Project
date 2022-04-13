package kr.co.iei.main.vo;

public class SurveyResult {
	private int totalCount;
	private int spt1Count;
	private int spt2Count;
	private int spt3Count;
	private int spt4Count;
	private int spt5Count;
	private int spt6Count;
	private int spt7Count;
	private String dateGap;
	private int vaccine0;
	private int vaccine1;
	private int vaccine2;
	private int vaccine3;
	public SurveyResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SurveyResult(int totalCount, int spt1Count, int spt2Count, int spt3Count, int spt4Count, int spt5Count,
			int spt6Count, int spt7Count, String dateGap, int vaccine0, int vaccine1, int vaccine2, int vaccine3) {
		super();
		this.totalCount = totalCount;
		this.spt1Count = spt1Count;
		this.spt2Count = spt2Count;
		this.spt3Count = spt3Count;
		this.spt4Count = spt4Count;
		this.spt5Count = spt5Count;
		this.spt6Count = spt6Count;
		this.spt7Count = spt7Count;
		this.dateGap = dateGap;
		this.vaccine0 = vaccine0;
		this.vaccine1 = vaccine1;
		this.vaccine2 = vaccine2;
		this.vaccine3 = vaccine3;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getSpt1Count() {
		return spt1Count;
	}
	public void setSpt1Count(int spt1Count) {
		this.spt1Count = spt1Count;
	}
	public int getSpt2Count() {
		return spt2Count;
	}
	public void setSpt2Count(int spt2Count) {
		this.spt2Count = spt2Count;
	}
	public int getSpt3Count() {
		return spt3Count;
	}
	public void setSpt3Count(int spt3Count) {
		this.spt3Count = spt3Count;
	}
	public int getSpt4Count() {
		return spt4Count;
	}
	public void setSpt4Count(int spt4Count) {
		this.spt4Count = spt4Count;
	}
	public int getSpt5Count() {
		return spt5Count;
	}
	public void setSpt5Count(int spt5Count) {
		this.spt5Count = spt5Count;
	}
	public int getSpt6Count() {
		return spt6Count;
	}
	public void setSpt6Count(int spt6Count) {
		this.spt6Count = spt6Count;
	}
	public int getSpt7Count() {
		return spt7Count;
	}
	public void setSpt7Count(int spt7Count) {
		this.spt7Count = spt7Count;
	}
	public String getDateGap() {
		return dateGap;
	}
	public void setDateGap(String dateGap) {
		this.dateGap = dateGap;
	}
	public int getVaccine0() {
		return vaccine0;
	}
	public void setVaccine0(int vaccine0) {
		this.vaccine0 = vaccine0;
	}
	public int getVaccine1() {
		return vaccine1;
	}
	public void setVaccine1(int vaccine1) {
		this.vaccine1 = vaccine1;
	}
	public int getVaccine2() {
		return vaccine2;
	}
	public void setVaccine2(int vaccine2) {
		this.vaccine2 = vaccine2;
	}
	public int getVaccine3() {
		return vaccine3;
	}
	public void setVaccine3(int vaccine3) {
		this.vaccine3 = vaccine3;
	}
}
