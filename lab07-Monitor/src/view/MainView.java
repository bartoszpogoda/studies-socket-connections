package view;

import controller.MainViewController;

public interface MainView {
	void setMainViewController(MainViewController mainViewController);
	String getHostPort();
	String getMessage();
	
	void reportError(String message);
	
	void setOutput(double[] data);
	void setVisible(boolean b);
}
