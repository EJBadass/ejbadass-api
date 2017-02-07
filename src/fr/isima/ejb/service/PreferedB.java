package fr.isima.ejb.service;

import fr.isima.ejb.annotation.Prefered;

@Prefered
public class PreferedB implements IMultiPreferedService {

	@Override
	public String giveMeAHand() {
		return ".B.";
	}

}
