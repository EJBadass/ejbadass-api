package fr.isima.ejb.service;

import fr.isima.ejb.annotation.Prefered;

@Prefered
public class PreferedA implements IMultiPreferedService {

	@Override
	public String giveMeAHand() {
		return ".A.";
	}

}
