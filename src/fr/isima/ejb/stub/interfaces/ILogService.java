package fr.isima.ejb.stub.interfaces;

import fr.isima.ejb.log.Log;

public interface ILogService {
	@Log
	void loggedMethod();
}
