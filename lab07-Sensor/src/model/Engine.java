package model;

import helper.Action;

public interface Engine {
	void setInterval(int interval);
	void setHost(String host);
	void setPort(int port);
	
	void start();
	void stop();
	
	void setSensedObject(Sensed sensedObject);
}
