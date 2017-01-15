package controller;

import view.MainView;

public interface MainViewController {
	void setMainView(MainView mainView);
	void sendMessage(String host, String port, String message); // data validation and parsing
}
