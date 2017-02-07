package fr.isima.ejb.service;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.annotation.Log;
import fr.isima.ejb.annotation.Prefered;
import fr.isima.ejb.annotation.Singleton;

@Singleton
@Prefered
public class GoodService implements IService {
	
	@Inject
	IService joker;
	
	@Override
	public String giveMeAHand() {
		return "GOOD";
	}

	@Override
	public IService getJokerService() {
		return joker;
	}

	@Log
	public void loggedMethod() {
		// TODO Auto-generated method stub
	}
	
}
