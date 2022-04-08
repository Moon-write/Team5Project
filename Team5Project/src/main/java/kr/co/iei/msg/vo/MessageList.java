package kr.co.iei.msg.vo;

import java.util.ArrayList;

public class MessageList {
	private ArrayList<Message> list;	// 쪼갤리스트 
	private int totalPage;				// 전체 페이지수
	
	public MessageList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageList(ArrayList<Message> list, int totalPage) {
		super();
		this.list = list;
		this.totalPage = totalPage;
	}
	public ArrayList<Message> getList() {
		return list;
	}
	public void setList(ArrayList<Message> list) {
		this.list = list;
	}

	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
