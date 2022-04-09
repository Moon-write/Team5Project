package kr.co.iei.msg.vo;

public class MessageName {
	private String memberId;
	private String memberName;
	public MessageName() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageName(String memberId, String memberName) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	

}
