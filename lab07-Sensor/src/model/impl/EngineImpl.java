package model.impl;

import helper.Action;
import model.Engine;
import model.Sensed;

public class EngineImpl implements Engine {

	private int interval;
	private String host;
	private int port;
	
	private Sensed sensedObject;
	private SensorThread sensorThread;
	
	@Override
	public void setInterval(int interval) {
		this.interval = interval;
	}

	@Override
	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSensedObject(Sensed examinedObject) {
		// TODO Auto-generated method stub
		
	}

}
