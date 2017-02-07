package fr.isima.ejb.service;

import fr.isima.ejb.annotation.Prefered;

@Prefered
public class JokerService implements IService2 {
	@Override
	public String giveMeAHand() {
		return "Joker";
	}

	@Override
	public IService2 getJokerService() {
		return null;
	}
}
