package kr.or.iei.notice.vo;

import java.util.ArrayList;

public class NoticePageData {
	private ArrayList<Notice> list;
	private String pageNav;
	public NoticePageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticePageData(ArrayList<Notice> list, String pageNav) {
		super();
		this.list = list;
		this.pageNav = pageNav;
	}
	public ArrayList<Notice> getList() {
		return list;
	}
	public void setList(ArrayList<Notice> list) {
		this.list = list;
	}
	public String getPageNav() {
		return pageNav;
	}
	public void setPageNav(String pageNav) {
		this.pageNav = pageNav;
	}
}
