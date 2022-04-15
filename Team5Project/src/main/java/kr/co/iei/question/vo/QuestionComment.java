package kr.co.iei.question.vo;

public class QuestionComment {
	private int qcNo;
	private int qcRef;
	private String qcWriter;
	private String qcComment;
	private String qcDate;
	private int qcCommentRef;
	public QuestionComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuestionComment(int qcNo, int qcRef, String qcWriter, String qcComment, String qcDate, int qcCommentRef) {
		super();
		this.qcNo = qcNo;
		this.qcRef = qcRef;
		this.qcWriter = qcWriter;
		this.qcComment = qcComment;
		this.qcDate = qcDate;
		this.qcCommentRef = qcCommentRef;
	}
	public int getQcNo() {
		return qcNo;
	}
	public void setQcNo(int qcNo) {
		this.qcNo = qcNo;
	}
	public int getQcRef() {
		return qcRef;
	}
	public void setQcRef(int qcRef) {
		this.qcRef = qcRef;
	}
	public String getQcWriter() {
		return qcWriter;
	}
	public void setQcWriter(String qcWriter) {
		this.qcWriter = qcWriter;
	}
	public String getQcComment() {
		return qcComment;
	}
	public void setQcComment(String qcComment) {
		this.qcComment = qcComment;
	}
	public String getQcDate() {
		return qcDate;
	}
	public void setQcDate(String qcDate) {
		this.qcDate = qcDate;
	}
	public int getQcCommentRef() {
		return qcCommentRef;
	}
	public void setQcCommentRef(int qcCommentRef) {
		this.qcCommentRef = qcCommentRef;
	}
}