package kr.co.iei.free.vo;

import java.sql.Date;

public class FreeComment {
	private int commentNo;
	private int freeNo;
	private String memberId;
	private String content;
	private Date date;
	private int recomment;
	private String writer;
	public FreeComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FreeComment(int commentNo, int freeNo, String memberId, String content, Date date, int recomment,
			String writer) {
		super();
		this.commentNo = commentNo;
		this.freeNo = freeNo;
		this.memberId = memberId;
		this.content = content;
		this.date = date;
		this.recomment = recomment;
		this.writer = writer;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getFreeNo() {
		return freeNo;
	}
	public void setFreeNo(int freeNo) {
		this.freeNo = freeNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getRecomment() {
		return recomment;
	}
	public void setRecomment(int recomment) {
		this.recomment = recomment;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
}
