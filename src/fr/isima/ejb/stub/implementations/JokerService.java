package fr.isima.ejb.stub.implementations;

import fr.isima.ejb.annotation.Prefered;
import fr.isima.ejb.stub.interfaces.IService2;

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
