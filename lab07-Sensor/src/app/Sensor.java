package app;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import helper.Action;
import helper.MessageDecoder;
import helper.impl.DecodedMessage;
import helper.impl.MessageDecoderImpl;
import model.Engine;
import model.Sensed;
import model.impl.EngineImpl;
import model.impl.SensedRandom;

public class Sensor {
	// TODO mozna by zrobic klase implementujaca Sensed ktora pobiera jakies realne dane ze strony
	public static void main(String[] args) {
		
		int port;
		ServerSocket serverSocket;
		
		Sensed sensed = new SensedRandom();
		
		Engine engine = new EngineImpl();
		engine.setSensedObject(sensed);
		
		MessageDecoder messageDecoder = new MessageDecoderImpl();
		
		try{
			port = Integer.parseInt(args[0]);
			serverSocket = new ServerSocket(port);
			
			while(true){
				Socket socket = serverSocket.accept();
				
				System.out.println("Nawi¹zano po³aczenie");
				
				String message = (new Scanner(socket.getInputStream())).nextLine();
				
				System.out.println("Otrzymana wiadomosc: " + message);
				
				DecodedMessage decodedMessage = messageDecoder.decode(message);
				
				if(decodedMessage == null){
					System.out.println("wtf"); return;
				}
				if(decodedMessage.getAction() == Action.Set){
					engine.setHost(decodedMessage.getHost());
					engine.setInterval(decodedMessage.getInterval());
					engine.setPort(decodedMessage.getPort());
					
					System.out.println("Parametery zosta³y ustawione");
				} else if(decodedMessage.getAction() == Action.Run){
					engine.start();
					System.out.println("Sensor wystartowa³");
				} else if(decodedMessage.getAction() == Action.End){
					engine.stop();
					System.out.println("Sensor zatrzymany");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		

	}

}
