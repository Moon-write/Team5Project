package kr.co.iei.question.vo;

public class Question {
	private int questionNo;
	private String questionWriter;
	private String questionTitle;
	private String questionContent;
	private int questionCount;
	private String questionDate;
	private int queRef;
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Question(int questionNo, String questionWriter, String questionTitle, String questionContent,
			int questionCount, String questionDate, int queRef) {
		super();
		this.questionNo = questionNo;
		this.questionWriter = questionWriter;
		this.questionTitle = questionTitle;
		this.questionContent = questionContent;
		this.questionCount = questionCount;
		this.questionDate = questionDate;
		this.queRef = queRef;
	}
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
	public String getQuestionWriter() {
		return questionWriter;
	}
	public void setQuestionWriter(String questionWriter) {
		this.questionWriter = questionWriter;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public int getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	public String getQuestionDate() {
		return questionDate;
	}
	public void setQuestionDate(String questionDate) {
		this.questionDate = questionDate;
	}
	public int getQueRef() {
		return queRef;
	}
	public void setQueRef(int queRef) {
		this.queRef = queRef;
	}
}
