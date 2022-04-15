package kr.co.iei.free.vo;

import java.sql.Date;

public class FreeView {
	private int no;
	private String title;
	private String memberId;
	private String writer;
	private String contents;
	private int like;
	private int view;
	private Date date;
	public FreeView() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FreeView(int no, String title, String memberId, String writer, String contents, int like, int view,
			Date date) {
		super();
		this.no = no;
		this.title = title;
		this.memberId = memberId;
		this.writer = writer;
		this.contents = contents;
		this.like = like;
		this.view = view;
		this.date = date;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
