package fr.isima.ejb.service;

import fr.isima.ejb.annotation.Log;

public interface ILogService {
	@Log
	void loggedMethod();
}
