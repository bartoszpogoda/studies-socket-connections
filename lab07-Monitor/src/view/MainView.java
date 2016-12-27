package view;

import controller.MainViewController;
import controller.SensorOutputController;

public interface MainView {
	void setMainViewController(MainViewController mainViewController);
	void setSensorOutputController(SensorOutputController sensorOutputController);
	
	
	void reportError(String message);
	
	void setOutput(String[] data);
	void setVisible(boolean b);
	void setPort(int port);
}
