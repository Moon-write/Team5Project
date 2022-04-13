package kr.co.iei.main.vo;

public class Survey {
	private String surveyId;
	private int symptom1;
	private int symptom2;
	private int symptom3;
	private int symptom4;
	private int symptom5;
	private int symptom6;
	private int symptom7;
	private String painDate;
	private String decideDate;
	private int vaccinate;
	private String story;
	public Survey() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Survey(String surveyId, int symptom1, int symptom2, int symptom3, int symptom4, int symptom5, int symptom6,
			int symptom7, String painDate, String decideDate, int vaccinate, String story) {
		super();
		this.surveyId = surveyId;
		this.symptom1 = symptom1;
		this.symptom2 = symptom2;
		this.symptom3 = symptom3;
		this.symptom4 = symptom4;
		this.symptom5 = symptom5;
		this.symptom6 = symptom6;
		this.symptom7 = symptom7;
		this.painDate = painDate;
		this.decideDate = decideDate;
		this.vaccinate = vaccinate;
		this.story = story;
	}


	public Survey(String surveyId, String painDate, String decideDate, int vaccinate, String story) {
		super();
		this.surveyId = surveyId;
		symptom1 = 0;
		symptom2 = 0;
		symptom3 = 0;
		symptom4 = 0;
		symptom5 = 0;
		symptom6 = 0;
		symptom7 = 0;
		this.painDate = painDate;
		this.decideDate = decideDate;
		this.vaccinate = vaccinate;
		this.story = story;
	}
	
	
	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public int getSymptom1() {
		return symptom1;
	}


	public void setSymptom1(int symptom1) {
		this.symptom1 = symptom1;
	}


	public int getSymptom2() {
		return symptom2;
	}


	public void setSymptom2(int symptom2) {
		this.symptom2 = symptom2;
	}


	public int getSymptom3() {
		return symptom3;
	}


	public void setSymptom3(int symptom3) {
		this.symptom3 = symptom3;
	}


	public int getSymptom4() {
		return symptom4;
	}


	public void setSymptom4(int symptom4) {
		this.symptom4 = symptom4;
	}


	public int getSymptom5() {
		return symptom5;
	}


	public void setSymptom5(int symptom5) {
		this.symptom5 = symptom5;
	}


	public int getSymptom6() {
		return symptom6;
	}


	public void setSymptom6(int symptom6) {
		this.symptom6 = symptom6;
	}


	public int getSymptom7() {
		return symptom7;
	}


	public void setSymptom7(int symptom7) {
		this.symptom7 = symptom7;
	}


	public void setVaccinate(int vaccinate) {
		this.vaccinate = vaccinate;
	}
	


	public int getVaccinate() {
		return vaccinate;
	}


	public String getPainDate() {
		return painDate;
	}
	public void setPainDate(String painDate) {
		this.painDate = painDate;
	}
	public String getDecideDate() {
		return decideDate;
	}
	public void setDecideDate(String decideDate) {
		this.decideDate = decideDate;
	}
	public String getStory() {
		return story;
	}
	public String getStoryBr() {
		return story.replaceAll("\r\n", "<br>");
	}
	public void setStory(String story) {
		this.story = story;
	}
	
}
