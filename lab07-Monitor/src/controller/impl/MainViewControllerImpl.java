package controller.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import controller.MainViewController;
import view.MainView;

public class MainViewControllerImpl implements MainViewController{

	private MainView mainView;
	
	@Override
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	@Override
	public void sendMessage(String host, String strPort, String message) {
		int port = -1;
		
		try{
			port = Integer.parseInt(strPort);
		} catch (NumberFormatException e) {
			mainView.reportError("Port musi byæ liczb¹");
			return;
		}
		
		if(port < 0 || port > 65535){
			mainView.reportError("Port musi byæ liczb¹ z zakresu ...");
			return;
		}
		
		Socket socket = null;
		
		try {
			socket = new Socket(host,port);
		} catch (IOException e) {
			mainView.reportError(e.getMessage());
			return;
		}
		
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(socket.getOutputStream());
			printWriter.println(message);
			printWriter.flush();
		} catch (IOException e) {
			mainView.reportError(e.getMessage());
		}
		
		try {
			socket.close();
		} catch (IOException e) {
			mainView.reportError(e.getMessage());
			return;
		}
	}
	
}
