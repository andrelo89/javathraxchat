package com.dconsult.ecscan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class ECScan extends HttpServlet {
	private static Logger logger = Logger.getLogger("com.dconsult.ecscan");

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// This example request includes an optional API key which you will need
		// to
		// remove or replace with your own key.
		// Read more about why it's useful to have an API key.
		// The request also includes the userip parameter which provides the end
		// user's IP address. Doing so will help distinguish this legitimate
		// server-side traffic from traffic which doesn't come from an end-user.
		String query = new String("еврофинансиране".getBytes(Charset.forName("UTF-8")), Charset.forName("UTF-8"));
		String encoded = URLEncoder.encode(query, "UTF-8");
		URL url = new URL("http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=" + encoded);
		URLConnection connection = url.openConnection();
		connection.addRequestProperty("Referer", "http://www.d-consult.net");
		String line;
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		try {
			JSONObject json = new JSONObject(builder.toString());
			JSONArray results = (JSONArray) json.get("results");
			resp.getOutputStream().println("results:");
			for (int count = 0; count < results.length(); count++) {
				resp.getOutputStream().println(results.getJSONObject(count).toString());
			}
		} catch (JSONException e) {
			logger.severe(e.getMessage());
		}
		// now have some fun with the results...
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
