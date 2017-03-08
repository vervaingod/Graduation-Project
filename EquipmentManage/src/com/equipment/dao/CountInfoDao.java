package com.equipment.dao;

public interface CountInfoDao {
	public int countSum() throws Exception;

	public int countSentWorker() throws Exception;

	public int countCancellation() throws Exception;

	public int countWaitAccept() throws Exception;

	public int countAccepted() throws Exception;

	public int countWaitConfirm() throws Exception;

	public int countConfirmed() throws Exception;

}
