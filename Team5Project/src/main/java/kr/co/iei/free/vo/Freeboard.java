package kr.co.iei.free.vo;

import java.util.ArrayList;

public class Freeboard {
	private ArrayList<Free> flist;
	private String pageNavi;
	public Freeboard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Freeboard(ArrayList<Free> flist, String pageNavi) {
		super();
		this.flist = flist;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Free> getFlist() {
		return flist;
	}
	public void setFlist(ArrayList<Free> flist) {
		this.flist = flist;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
}
