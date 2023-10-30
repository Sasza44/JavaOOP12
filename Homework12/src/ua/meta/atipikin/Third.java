package ua.meta.atipikin;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Third {

	public static void main(String[] args) {
		String s1 = "https://bayraksad.com/"; // адреса сайту
		List<String> l1 = listOfLinks(getHtml(s1)); // список посилань, узятий з сайту
//		l1.forEach(e -> System.out.println(e));
		String s2 = "";
		for(int i = 0; i < l1.size(); i++) { // перетворення списку на текст
			s2 += l1.get(i);
			s2 += "\n";
		}
		System.out.println(s2); // виведення тексту в консоль
		writeToFile(s2, "a2.txt"); // запис тексту у файл
	}
	
	public static String getHtml(String address) { // отримання html коду сторінки
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(address);
			HttpURLConnection c1 = (HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(c1.getInputStream()));
			String text = "";
			for(; (text = br.readLine()) != null;) {
				sb.append(text).append(System.lineSeparator());
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static List<String> listOfLinks(String s1) { // вибір посилань та вкидання їх в список
		List<String> l1 = new ArrayList<>();
		int ind = 0;
		int ind2 = 0;
		String s2 = "";
		for(; ind != -1;) {
			if(ind == 0) {
				ind = s1.indexOf("<a");
				ind2 = s1.indexOf("</a>");
				s2 = s1.substring(ind, ind2 + 4);
			}
			else if(ind > 0) {
				ind = s1.indexOf("<a", ind2 + 1);
				ind2 = s1.indexOf("</a>", ind + 1);
				if(ind != -1) {
					s2 = s1.substring(ind, ind2 + 4);
				}
			}
			l1.add(s2);
		}
		return l1;
	}
	
	public static void writeToFile(String text, String fileName) { // запис тексту в файл
		File f = new File(fileName);
		try(FileWriter fw = new FileWriter(f, false)) {
			fw.write(text);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}