package controller.impl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import controller.SensorOutputController;
import model.DataRepository;
import view.MainView;

public class SensorOutputControllerImpl implements SensorOutputController {

	private DataRepository dataRepository;
	private MainView mainView;
	private int port;
	private ServerSocket serverSocket = null;
	
	@Override
	public void setDataRepository(DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	@Override
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	@Override
	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public void start() {
		
		try{
			serverSocket = new ServerSocket(port);
		}catch (IOException e) {
			System.out.println("Nie uda³o siê stworzyæ gniazdka serwerowego");
			return;
		}
		
		System.out.println("Gniazdko serwerowe utworzone na porcie " + port);
		
		while(true){
			Socket socket = null;
			
			try {
				socket = serverSocket.accept();
				
				Scanner scanner = new Scanner(socket.getInputStream());
				
				dataRepository.addData(scanner.nextLine());
				
				scanner.close();
				socket.close();
				
				mainView.setOutput(dataRepository.getValues());
				
			} catch (IOException e) {
				// zatrzymanie dzialania gniazdka serwerowego
				return;
			}
		}
			
	}

	@Override
	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
