package com.dconsult.ecscan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("serial")
public class ECScan extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		// This example request includes an optional API key which you will need to
		// remove or replace with your own key.
		// Read more about why it's useful to have an API key.
		// The request also includes the userip parameter which provides the end
		// user's IP address. Doing so will help distinguish this legitimate
		// server-side traffic from traffic which doesn't come from an end-user.
		URL url = new URL(
		    "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
		    + "q=Paris%20Hilton");
		URLConnection connection = url.openConnection();
		connection.addRequestProperty("Referer", "http://www.d-consult.net");

		String line;
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while((line = reader.readLine()) != null) {
		 builder.append(line);
		}

		try {
			JSONObject json = new JSONObject(builder.toString());
			System.out.println(json.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// now have some fun with the results...
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
}
