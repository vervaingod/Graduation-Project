package com.equipment.bean;

public class CountInfoBean {
	private int count_sum = 0;
	private int count_sent_worker = 0;
	private int count_cancellation = 0;// 已作废
	private int count_wait_accept = 0;
	private int count_accepted = 0;
	private int count_wait_confirm = 0;
	private int count_confirmed = 0;

	public int getCount_sum() {
		return count_sum;
	}

	public void setCount_sum(int count_sum) {
		this.count_sum = count_sum;
	}

	public int getCount_sent_worker() {
		return count_sent_worker;
	}

	public void setCount_sent_worker(int count_sent_worker) {
		this.count_sent_worker = count_sent_worker;
	}

	public int getCount_cancellation() {
		return count_cancellation;
	}

	public void setCount_cancellation(int count_cancellation) {
		this.count_cancellation = count_cancellation;
	}

	public int getCount_wait_accept() {
		return count_wait_accept;
	}

	public void setCount_wait_accept(int count_wait_accept) {
		this.count_wait_accept = count_wait_accept;
	}

	public int getCount_accepted() {
		return count_accepted;
	}

	public void setCount_accepted(int count_accepted) {
		this.count_accepted = count_accepted;
	}

	public int getCount_wait_confirm() {
		return count_wait_confirm;
	}

	public void setCount_wait_confirm(int count_wait_confirm) {
		this.count_wait_confirm = count_wait_confirm;
	}

	public int getCount_confirmed() {
		return count_confirmed;
	}

	public void setCount_confirmed(int count_confirmed) {
		this.count_confirmed = count_confirmed;
	}

}
