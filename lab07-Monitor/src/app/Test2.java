package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Test2 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost",2001);
		
		Scanner sc = new Scanner(System.in);
		
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
		printWriter.println(sc.nextLine());
		printWriter.flush();
	}

}
