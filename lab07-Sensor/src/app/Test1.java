package app;

import model.Sensed;
import model.impl.SensedRandom;
import model.impl.SensorThread;

public class Test1 {
	public static void main(String[] args) {
		
		SensorThread sensorThread = new SensorThread(1000,"localhost",1003);
		Sensed sensedObject = new SensedRandom();
		
		sensorThread.setSensedObject(sensedObject);
		
		sensorThread.start();
		
	}
}
