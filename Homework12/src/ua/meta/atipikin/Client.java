package ua.meta.atipikin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 8000)) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.println("Connecting to server");
			String response = reader.readLine();
			System.out.println(response);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}