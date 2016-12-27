package app;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import exception.MessageNotCorrectException;
import exception.ParametersNotSetCorrectlyException;
import helper.Action;
import helper.MessageDecoder;
import helper.impl.DecodedMessage;
import helper.impl.MessageDecoderImpl;
import model.Engine;
import model.Sensed;
import model.impl.EngineImpl;
import model.impl.SensedAvaliableMemory;
import model.impl.SensedAvaliableSpace;

public class Sensor {
	// TODO mozna by zrobic klase implementujaca Sensed ktora pobiera jakies realne dane ze strony
	public static void main(String[] args) {
		
		if(args.length != 1){
			System.out.println("Wymagane argumenty: {port Sensora} ");
			return;
		}
		
		int port;
		ServerSocket serverSocket;
		
		Sensed sensed = new SensedAvaliableSpace();
		
		Engine engine = new EngineImpl();
		engine.setSensedObject(sensed);
		
		MessageDecoder messageDecoder = new MessageDecoderImpl();
		
		try{
			port = Integer.parseInt(args[0]);
		} catch(NumberFormatException e){
			System.out.println("Port musi by� liczb� ca�kowit�");
			return;
		}
		
		try{
			serverSocket = new ServerSocket(port);
		}catch (IOException e) {
			System.out.println("Nie uda�o si� stworzy� gniazdka serwerowego");
			return;
		}
		
		System.out.println("Gniazdko serwerowe utworzone na porcie " + port);
		
		while(true){
			Socket socket = null;
			
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("B��d po��czenia");
				return;
			}
			
			System.out.println("Nawi�zano po��czenie");
			
			Scanner scanner = null;
			try {
				scanner = new Scanner(socket.getInputStream());
				String message = scanner.nextLine();
				
				scanner.close();
				socket.close();
				
				System.out.println("Otrzymana wiadomosc: " + message);
				
				DecodedMessage decodedMessage = messageDecoder.decode(message);
				
				
				if(decodedMessage.getAction() == Action.Set){
					engine.setHost(decodedMessage.getHost());
					engine.setInterval(decodedMessage.getInterval());
					engine.setPort(decodedMessage.getPort());
					
					System.out.println("Parametery zosta�y ustawione");
				} else if(decodedMessage.getAction() == Action.Run){
					engine.start();
					System.out.println("Sensor wystartowa�");
				} else if(decodedMessage.getAction() == Action.End){
					engine.stop();
					System.out.println("Sensor zatrzymany");
				}
				
			} catch (IOException e) {
				System.out.println("B��d odczytu");
			} catch (MessageNotCorrectException e) {
				System.out.println("Wiadomo�� nie zosta�a zrozumiana");
			} catch (ParametersNotSetCorrectlyException e) {
				System.out.println("Parametry nie zosta�y ustawione poprawnie");
			}
			
			
			
		}
		
		
		

	}

}
