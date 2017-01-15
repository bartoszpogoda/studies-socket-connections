package controller;

import model.DataRepository;
import view.MainView;

public interface SensorListener {
	void setDataRepository(DataRepository dataRepository);
	void setMainView(MainView mainView);
	void setPort(int port);
	
	void start();
	void stop();
}
