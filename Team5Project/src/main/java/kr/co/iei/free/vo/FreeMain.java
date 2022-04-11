package kr.co.iei.free.vo;

public class FreeMain {
	private int sort;
	private int count;
	private String keyword;
	public FreeMain() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FreeMain(int sort, int count, String keyword) {
		super();
		this.sort = sort;
		this.count = count;
		this.keyword = keyword;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}	
