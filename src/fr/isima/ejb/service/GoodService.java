package fr.isima.ejb.service;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.annotation.Log;
import fr.isima.ejb.annotation.Prefered;
import fr.isima.ejb.annotation.Singleton;

@Singleton
@Prefered
public class GoodService implements IService {
	
	@Inject
	IService2 joker;
	
	@Override
	public String giveMeAHand() {
		return "GOOD";
	}

	@Override
	public IService2 getJokerService() {
		return joker;
	}

	@Log
	public void loggedMethod() {
		// TODO Auto-generated method stub
	}
	
}
