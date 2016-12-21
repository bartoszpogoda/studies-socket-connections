package app;

import java.io.IOException;
import java.net.ServerSocket;

public class Sensor {
	// TODO mozna by zrobic klase implementujaca Sensed ktora pobiera jakies realne dane ze strony
	public static void main(String[] args) {
		
		int port;
		
		try{
			port = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			ServerSocket serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
