package app;

import model.DataRepository;
import model.impl.DataRepositoryImpl;

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

	}

}
