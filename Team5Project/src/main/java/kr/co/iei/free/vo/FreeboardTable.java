package kr.co.iei.free.vo;

import java.sql.Date;

public class FreeboardTable {
	private int no;
	private String title;
	private String writer;
	private Date date;
	private int viewCount;
	private int likeCount;
	public FreeboardTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FreeboardTable(int no, String title, String writer, Date date, int viewCount, int likeCount) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.date = date;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
}
