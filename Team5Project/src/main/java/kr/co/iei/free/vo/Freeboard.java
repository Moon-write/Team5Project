package kr.co.iei.free.vo;

public class Freeboard {
	private Free f;
	private String writer;
	private String Page;
	public Freeboard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Freeboard(Free f, String writer, String page) {
		super();
		this.f = f;
		this.writer = writer;
		Page = page;
	}
	public Free getF() {
		return f;
	}
	public void setF(Free f) {
		this.f = f;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPage() {
		return Page;
	}
	public void setPage(String page) {
		Page = page;
	}
}
