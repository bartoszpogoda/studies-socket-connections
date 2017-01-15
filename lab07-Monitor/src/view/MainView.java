package view;

import controller.MainViewController;
import controller.SensorListener;

public interface MainView {
	void setMainViewController(MainViewController mainViewController);
	void setSensorOutputController(SensorListener sensorOutputController);
	
	
	void reportError(String message);
	
	void setOutput(String[] data);
	void setVisible(boolean b);
	void setPort(int port);
}
