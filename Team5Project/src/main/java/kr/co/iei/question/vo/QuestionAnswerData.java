package kr.co.iei.question.vo;

import java.util.ArrayList;

public class QuestionAnswerData {
	private ArrayList<Question> questionList; 
	private ArrayList<Question> requestionList;
	private ArrayList<Question> list;
	private String pageNavi;
	public QuestionAnswerData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuestionAnswerData(ArrayList<Question> questionList, ArrayList<Question> requestionList,
			ArrayList<Question> list, String pageNavi) {
		super();
		this.questionList = questionList;
		this.requestionList = requestionList;
		this.list = list;
		this.pageNavi = pageNavi;
	}
	public ArrayList<Question> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(ArrayList<Question> questionList) {
		this.questionList = questionList;
	}
	public ArrayList<Question> getRequestionList() {
		return requestionList;
	}
	public void setRequestionList(ArrayList<Question> requestionList) {
		this.requestionList = requestionList;
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
