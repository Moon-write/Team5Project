package kr.co.iei.question.vo;

import java.util.ArrayList;

public class QuestionViewData {
	private Question q;
	private ArrayList<QuestionComment> commentList;
	private ArrayList<QuestionComment> recommentList;
	public Question getQ() {
		return q;
	}
	public void setQ(Question q) {
		this.q = q;
	}
	public ArrayList<QuestionComment> getCommentList() {
		return commentList;
	}
	public void setCommentList(ArrayList<QuestionComment> commentList) {
		this.commentList = commentList;
	}
	public ArrayList<QuestionComment> getRecommentList() {
		return recommentList;
	}
	public void setRecommentList(ArrayList<QuestionComment> recommentList) {
		this.recommentList = recommentList;
	}
	public QuestionViewData(Question q, ArrayList<QuestionComment> commentList,
			ArrayList<QuestionComment> recommentList) {
		super();
		this.q = q;
		this.commentList = commentList;
		this.recommentList = recommentList;
	}
	public QuestionViewData() {
		super();
		// TODO Auto-generated constructor stub
	}
}
