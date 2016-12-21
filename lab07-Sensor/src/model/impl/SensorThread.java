package model.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import model.Sensed;

public class SensorThread extends Thread {
	
	private int interval;
	private String host;
	private int port;
	
	private Sensed sensedObject;
	
	private volatile boolean alive;
	
	public SensorThread(int interval, String host, int port) {
		this.interval = interval;
		this.host = host;
		this.port = port;
		
		alive = true;
	}
	
	public void setSensedObject(Sensed sensedObject){
		this.sensedObject = sensedObject;
	}
	
	public void kill(){
		alive = false;
	}
	
	@Override
	public void run() {
		
		Socket socket;
		
		while(alive){

			if(sensedObject == null){
				System.out.println("Ustaw obiekt do badania..");
				return;
			}
				
			try {
				socket = new Socket(host,port);

				PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
				printWriter.println(sensedObject.getValue());
				printWriter.flush();
				
				socket.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
