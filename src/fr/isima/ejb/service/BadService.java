package fr.isima.ejb.service;


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