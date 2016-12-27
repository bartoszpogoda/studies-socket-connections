package app;

import javax.net.ssl.SNIHostName;

import controller.MainViewController;
import controller.SensorOutputController;
import controller.impl.MainViewControllerImpl;
import controller.impl.SensorOutputControllerImpl;
import model.DataRepository;
import model.impl.DataRepositoryImpl;
import view.MainView;
import view.impl.MainViewImpl;

public class Monitor {
	//TODO: czy sensor te¿ po wy³aczeniu wy³acza serwer socket?
	public static void main(String[] args) {
		
		int port;
		
		try{
			port = Integer.parseInt(args[0]);
		} catch (Exception e) {
			port = 1234;
		}
		
		DataRepository dataRepository = new DataRepositoryImpl(5);
		
		MainViewController mainViewController = new MainViewControllerImpl();
		
		MainView mainView = new MainViewImpl();
		
		mainViewController.setMainView(mainView);
		mainView.setMainViewController(mainViewController);
		
		SensorOutputController sensorOutputController = new SensorOutputControllerImpl();
		sensorOutputController.setPort(port);
		sensorOutputController.setDataRepository(dataRepository);
		sensorOutputController.setMainView(mainView);
		
		mainView.setSensorOutputController(sensorOutputController);
		
		mainView.setVisible(true);
		
		sensorOutputController.start();
		
	}

}
