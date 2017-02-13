package fr.isima.ejb.log;

import java.util.ArrayList;
import java.util.List;

public class Logger implements ILogger {

	private List<String> logs = new ArrayList<>();
	
	public boolean contains(String string) {
		return logs.contains(string);
	}
	
	public void log(String string) {
		logs.add(string);
	}

}
