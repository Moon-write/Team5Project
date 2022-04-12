package kr.co.iei.question.vo;

import java.util.ArrayList;

public class QuestionPageData {
	private ArrayList<Question> list;
	private String pageNavi;
	public QuestionPageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuestionPageData(ArrayList<Question> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Question> getList() {
		return list;
	}
	public void setList(ArrayList<Question> list) {
		this.list = list;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

}
