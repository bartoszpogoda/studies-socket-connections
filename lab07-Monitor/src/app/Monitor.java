package app;

import controller.MainViewController;
import controller.SensorListener;
import controller.impl.MainViewControllerImpl;
import controller.impl.SensorListenerImpl;
import model.DataRepository;
import model.impl.DataRepositoryImpl;
import view.MainView;
import view.impl.MainViewImpl;

public class Monitor {
	public static void main(String[] args) {
		
		if(args.length != 1){
			System.out.println("Wymagane argumenty: {port Monitora} ");
			return;
		}
		
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
		
		SensorListener sensorListener = new SensorListenerImpl();
		sensorListener.setPort(port);
		sensorListener.setDataRepository(dataRepository);
		sensorListener.setMainView(mainView);
		
		mainView.setSensorListener(sensorListener);
		
		mainView.setPort(port);
		mainView.setVisible(true);
		
		sensorListener.start();
		
	}

}
