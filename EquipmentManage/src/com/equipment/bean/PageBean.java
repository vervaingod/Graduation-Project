package com.equipment.bean;

public class PageBean {
	public static int ROWS_PRO_PAGE = 4;
	private int pageno;
	private int maxpage;

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}

}
