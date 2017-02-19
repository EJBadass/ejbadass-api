package fr.isima.ejb.stub.implementations;

import fr.isima.ejb.stub.interfaces.IMultiService;

public class MultiServiceA implements IMultiService {
	@Override
	public String giveMeAHand() {
		return "A";
	}
}
