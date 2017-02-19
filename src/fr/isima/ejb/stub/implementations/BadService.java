package fr.isima.ejb.stub.implementations;

import fr.isima.ejb.stub.interfaces.IService;
import fr.isima.ejb.stub.interfaces.IService2;

public class BadService implements IService {
	@Override
	public String giveMeAHand() {
		return "BAD";
	}

	@Override
	public IService2 getJokerService() {
		return null;
	}
}
