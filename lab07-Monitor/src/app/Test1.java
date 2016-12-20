package app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) throws IOException {
		int port = 1003;
		
		ServerSocket ss = new ServerSocket(port);
		
		while(true){
			Socket socket = ss.accept();
			Scanner scanner = new Scanner(socket.getInputStream());
			if(scanner.hasNext())
				System.out.println("Read: " + scanner.nextLine());
		}

	}

}
