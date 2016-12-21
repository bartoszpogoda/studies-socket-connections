package model;

import exception.ParametersNotSetCorrectlyException;
import helper.Action;

public interface Engine {
	void setInterval(int interval);
	void setHost(String host);
	void setPort(int port);
	
	void start() throws ParametersNotSetCorrectlyException;
	void stop();
	
	void setSensedObject(Sensed sensedObject);
}
