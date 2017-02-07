package fr.isima.ejb.service;


public class MultiServiceA implements IMultiService {
	@Override
	public String giveMeAHand() {
		return "A";
	}
}
