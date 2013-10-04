package br.com.sigen.werp.app.facebook;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class FacebookService {

	public static void main(String[] args) {

		String url = "https://graph.facebook.com/me/friends?method=GET&format=json&suppress_http_code=1&access_token=CAACEdEose0cBAD9Nh9AlqQvjcMxw8IhoCC4RwcQClDO31AA91kj03pCe51P3kS5Mo7LKUzaeqSc8OHfJuznJnhvCdqw18xTBaC05XHqTZAcLS4AS9E0p4oMOtlu8Uezezb8zAZA8fdrZCvdY5IFWYMVcTczXsRCCYDnJZBx6HyKHmUPHCgEBb1jObl4aBVuf3IvC3M8A7QZDZD";
		FacebookService.excuteGet(url);
		// System.out.println(FacebookService.excuteGet(url));

	}

	public static void excuteGet(String targetURL) {
		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Get Response
			InputStream is = connection.getInputStream();
			JsonReader rdr = Json.createReader(is);
			JsonObject obj = rdr.readObject();
			JsonArray results = obj.getJsonArray("data");
			int i = 0;
			for (JsonObject result : results.getValuesAs(JsonObject.class)) {
				System.out.println(result.getString("name"));
				System.out.println(++i);
			}
			if (obj.containsKey("paging")) {
				JsonObject paging = obj.getJsonObject("paging");
				if (paging.containsKey("paging"))
					excuteGet(paging.getString("next"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}
