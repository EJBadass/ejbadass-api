package fr.isima.ejb.stub.implementations;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.annotation.Prefered;
import fr.isima.ejb.annotation.Singleton;
import fr.isima.ejb.log.Log;
import fr.isima.ejb.stub.interfaces.ILogService;
import fr.isima.ejb.stub.interfaces.IService;
import fr.isima.ejb.stub.interfaces.IService2;

@Singleton
@Prefered
public class GoodService implements IService, ILogService {
	
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
		
	}
	
}
