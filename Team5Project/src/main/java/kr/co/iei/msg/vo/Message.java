package kr.co.iei.msg.vo;

public class Message {
	private int msgNo;
	private String msgSender;
	private String msgReceiver;
	private String msgContent;
	private String msgDate;
	private int msgRead;
	private int msgSenderDel;
	private int msgReceiverDel;
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Message(int msgNo, String msgSender, String msgReceiver, String msgContent, String msgDate, int msgRead,
			int msgSenderDel, int msgReceiverDel) {
		super();
		this.msgNo = msgNo;
		this.msgSender = msgSender;
		this.msgReceiver = msgReceiver;
		this.msgContent = msgContent;
		this.msgDate = msgDate;
		this.msgRead = msgRead;
		this.msgSenderDel = msgSenderDel;
		this.msgReceiverDel = msgReceiverDel;
	}
	
	public Message(String msgSender, String msgReceiver, String msgContent) {
		super();
		this.msgSender = msgSender;
		this.msgReceiver = msgReceiver;
		this.msgContent = msgContent;
	}	
	
	public int getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}
	public String getMsgSender() {
		return msgSender;
	}
	public void setMsgSender(String msgSender) {
		this.msgSender = msgSender;
	}
	public String getMsgReceiver() {
		return msgReceiver;
	}
	public void setMsgReceiver(String msgReceiver) {
		this.msgReceiver = msgReceiver;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(String msgDate) {
		this.msgDate = msgDate;
	}
	public int getMsgRead() {
		return msgRead;
	}
	public void setMsgRead(int msgRead) {
		this.msgRead = msgRead;
	}
	public int getMsgSenderDel() {
		return msgSenderDel;
	}
	public void setMsgSenderDel(int msgSenderDel) {
		this.msgSenderDel = msgSenderDel;
	}
	public int getMsgReceiverDel() {
		return msgReceiverDel;
	}
	public void setMsgReceiverDel(int msgReceiverDel) {
		this.msgReceiverDel = msgReceiverDel;
	}
}



