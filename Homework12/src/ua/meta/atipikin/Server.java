package ua.meta.atipikin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(8000);) {
			System.out.println("Server started...");
			int n1 = 0; // порядковий номер з'єднання
			while(n1 <= 4) { // обмежимо кількість запитів до 5
				try(Socket socket = serverSocket.accept();
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
					System.out.println("Client connected");
					n1 += 1;
					writer.write("Windows 10 Pro" + " request number " + n1);
					writer.newLine();
					writer.flush();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}