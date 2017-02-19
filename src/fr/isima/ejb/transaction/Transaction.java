package fr.isima.ejb.transaction;

import fr.isima.ejb.annotation.Prefered;
import fr.isima.ejb.injection.IInterceptor;

@Prefered
public class Transaction implements ITransaction {

	public static long numberOfBegin = 0;
	public static long numberOfCommit = 0;
	public static long numberOfRollback = 0;
	private boolean active = true;
	private IInterceptor caller = null;

	@Override
	public void begin() {
		if(this.active)
			numberOfBegin++;
	}

	@Override
	public void commit() {
		if(this.active)
			numberOfCommit++;
	}

	@Override
	public void rollback() {
		if(this.active)
			numberOfRollback++;
	}

	@Override
	public void awake() {
		this.active = true;
	}

	@Override
	public void sleep() {
		this.active = false;
	}

	@Override
	public void setCaller(IInterceptor t) {
		this.caller = t;
	}

	@Override
	public boolean isCaller(IInterceptor t) {
		return caller==null?false:caller.equals(t)?true:false;
	}

}
