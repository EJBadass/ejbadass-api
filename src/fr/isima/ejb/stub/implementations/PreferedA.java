package fr.isima.ejb.stub.implementations;

import fr.isima.ejb.annotation.Prefered;
import fr.isima.ejb.stub.interfaces.IMultiPreferedService;

@Prefered
public class PreferedA implements IMultiPreferedService {

	@Override
	public String giveMeAHand() {
		return ".A.";
	}

}
