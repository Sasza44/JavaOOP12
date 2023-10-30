package ua.meta.atipikin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class First {

	public static void main(String[] args) {
		File f1 = new File("a1.txt"); // текстовий файл з адресами сайтів
		String[] arr1 = readFromFile(f1); // зчитування з файлу масиву адрес
		for(int i = 0; i < arr1.length; i++) {
			System.out.println(arr1[i] + " - " + checkAccessibility(arr1[i])); // перевірка доступності кожної адреси масиву
		}
	}
	
	public static String[] readFromFile(File f) { // зчитування рядків з файлу та вкидання їх у масив
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader(f))) {
			String text = "";
			for(; (text = br.readLine()) != null;) {
				sb.append(text);
				sb.append(System.lineSeparator());
			}
		} catch(IOException e) {
			System.out.println(e);
		}
		String[] array = sb.toString().split("\n");
		for(int i = 0; i < array.length; i++) {
			array[i] = array[i].substring(0, array[i].length() - 1);
		}
		return array;
	}
	
	public static boolean checkAccessibility(String s) { // перевірка доступності адреси
		boolean b = false;
		try {
			URL url = new URL(s);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			if(con.getResponseCode() >= 200 && con.getResponseCode() <= 299) {b = true;}
		} catch (IOException e) {
			System.out.println(e);
		}
		return b;
	}
}