package fr.isima.ejb.log;

import fr.isima.ejb.annotation.Singleton;

@Singleton
public interface ILogger {
	
	public boolean contains(String string);
	
	public void log(String string);

}
