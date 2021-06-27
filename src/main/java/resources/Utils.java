package resources;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Properties;

public class Utils {

	public static String getKeyValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public static String getRandomText(String numberOfParagraphs) throws IOException {
		String randomText = "default text from script";

		URL link = new URL(
				"https://baconipsum.com/api/?type=meat-and-filler&paras=" + numberOfParagraphs + "&format=text");
		HttpURLConnection conn = (HttpURLConnection) link.openConnection();
		conn.setConnectTimeout(3000);
		conn.connect();
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			randomText = response.toString();
			return randomText;
		} else {
			return randomText;
		}
	}

	public static boolean isArticleDeleted(String url) {
		boolean deleted = false;
		try {
			URL link = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			String encoded = Base64.getEncoder()
					.encodeToString(("candidatex:qa-is-cool")
							.getBytes(StandardCharsets.UTF_8));
			connection.setRequestProperty("Authorization", "Basic " + encoded);

			connection.setConnectTimeout(1000);
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				System.out.println(link + " heyy " + connection.getResponseMessage());
				deleted = false;
				return deleted;
			} else if (responseCode == 404) {
				System.out.println(link + " heeee " + connection.getResponseMessage());
				deleted = true;
				return deleted;
			} else {
				System.out.println(link + " ELSE " + connection.getResponseMessage());
			}
		} catch (Exception ex) {
		}
		return deleted;
	}
}
