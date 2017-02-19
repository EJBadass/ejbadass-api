package fr.isima.ejb.stub.implementations;

import fr.isima.ejb.stub.interfaces.IMultiService;

public class MultiServiceB implements IMultiService {
	@Override
	public String giveMeAHand() {
		return "B";
	}
}
