package fr.isima.ejb.transaction;

import fr.isima.ejb.annotation.Prefered;

@Prefered
public class Transaction implements ITransaction {

	public static long numberOfBegin = 0;
	public static long numberOfCommit = 0;
	public static long numberOfRollback = 0;

	@Override
	public void begin() {
		numberOfBegin++;
	}

	@Override
	public void commit() {
		numberOfCommit++;
	}

	@Override
	public void rollback() {
		numberOfCommit++;
	}

}
