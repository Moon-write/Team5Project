package kr.co.iei.mapApi.vo;

public class Clinic {
	private String clinicName;
	private String sidoNm;
	private String sgguNm;
	private String clinicCode;
	private String clinicAddr;
	private String ratAble;
	private String pcrAble;
	private String xPos;
	private String yPos;
	private String telNo;
	
	public Clinic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Clinic(String clinicName, String sidoNm, String sgguNm, String clinicCode, String clinicAddr, String ratAble,
			String pcrAble, String xPos, String yPos, String telNo) {
		super();
		this.clinicName = clinicName;
		this.sidoNm = sidoNm;
		this.sgguNm = sgguNm;
		this.clinicCode = clinicCode;
		this.clinicAddr = clinicAddr;
		this.ratAble = ratAble;
		this.pcrAble = pcrAble;
		this.xPos = xPos;
		this.yPos = yPos;
		this.telNo = telNo;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getSidoNm() {
		return sidoNm;
	}
	public void setSidoNm(String sidoNm) {
		this.sidoNm = sidoNm;
	}
	public String getSgguNm() {
		return sgguNm;
	}
	public void setSgguNm(String sgguNm) {
		this.sgguNm = sgguNm;
	}
	public String getClinicCode() {
		return clinicCode;
	}
	public void setClinicCode(String clinicCode) {
		this.clinicCode = clinicCode;
	}
	public String getClinicAddr() {
		return clinicAddr;
	}
	public void setClinicAddr(String clinicAddr) {
		this.clinicAddr = clinicAddr;
	}
	public String getRatAble() {
		return ratAble;
	}
	public void setRatAble(String ratAble) {
		this.ratAble = ratAble;
	}
	public String getPcrAble() {
		return pcrAble;
	}
	public void setPcrAble(String pcrAble) {
		this.pcrAble = pcrAble;
	}
	public String getxPos() {
		return xPos;
	}
	public void setxPos(String xPos) {
		this.xPos = xPos;
	}
	public String getyPos() {
		return yPos;
	}
	public void setyPos(String yPos) {
		this.yPos = yPos;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	
	
}
