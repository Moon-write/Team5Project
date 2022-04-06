package kr.co.iei.free.vo;

import java.sql.Date;

public class Free {
	private int Free_No;
	private String Free_Id;
	private String Free_Title;
	private String Free_Content;
	private Date Free_Date;
	private int Free_Count;
	
	public Free() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Free(int free_No, String free_Id, String free_Title, String free_Content, Date free_Date, int free_Count) {
		super();
		Free_No = free_No;
		Free_Id = free_Id;
		Free_Title = free_Title;
		Free_Content = free_Content;
		Free_Date = free_Date;
		Free_Count = free_Count;
	}

	public int getFree_No() {
		return Free_No;
	}
	public void setFree_No(int free_No) {
		Free_No = free_No;
	}
	public String getFree_Id() {
		return Free_Id;
	}
	public void setFree_Id(String free_Id) {
		Free_Id = free_Id;
	}
	public String getFree_Title() {
		return Free_Title;
	}
	public void setFree_Title(String free_Title) {
		Free_Title = free_Title;
	}
	public String getFree_Content() {
		return Free_Content;
	}
	public void setFree_Content(String free_Content) {
		Free_Content = free_Content;
	}
	public Date getFree_Date() {
		return Free_Date;
	}
	public void setFree_Date(Date free_Date) {
		Free_Date = free_Date;
	}
	public int getFree_Count() {
		return Free_Count;
	}
	public void setFree_Count(int free_Count) {
		Free_Count = free_Count;
	}
	
}
