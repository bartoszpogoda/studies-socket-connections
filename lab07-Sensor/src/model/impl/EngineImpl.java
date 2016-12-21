package model.impl;

import exception.ParametersNotSetCorrectlyException;
import helper.Action;
import model.Engine;
import model.Sensed;

public class EngineImpl implements Engine {

	private int interval;
	private String host = null;
	private int port = -1;
	
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
	public void start() throws ParametersNotSetCorrectlyException {
		
		if(interval <= 0) throw new ParametersNotSetCorrectlyException();
		if(host == null || host.isEmpty()) throw new ParametersNotSetCorrectlyException();
		if(port < 0 || port > 65535 ) throw new ParametersNotSetCorrectlyException();

		sensorThread = new SensorThread(interval, host, port);
		
		System.out.println("Starting sensor thread...");
		sensorThread.start();

	}

	@Override
	public void stop() {
		System.out.println("Killing sensor thread...");
		sensorThread.kill();

	}

	@Override
	public void setSensedObject(Sensed sensedObject) {
		this.sensedObject = sensedObject;
		
	}

}
