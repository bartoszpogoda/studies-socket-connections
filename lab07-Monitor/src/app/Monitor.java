package app;

import controller.MainViewController;
import controller.impl.MainViewControllerImpl;
import model.DataRepository;
import model.impl.DataRepositoryImpl;
import view.MainView;
import view.impl.MainViewImpl;

public class Monitor {

	public static void main(String[] args) {
		
		int port;
		
		try{
			port = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		
		DataRepository dataRepository = new DataRepositoryImpl(5);
		
		MainViewController mainViewController = new MainViewControllerImpl();
		
		MainView mainView = new MainViewImpl();
		
		mainViewController.setMainView(mainView);
		mainView.setMainViewController(mainViewController);
		
		
		mainView.setVisible(true);

	}

}
