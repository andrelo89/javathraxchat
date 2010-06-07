package com.dconsult.ecscan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dconsult.jdo.PMF;
import com.dconsult.model.SalesLeads;

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
		// Google// URL url = new
		// URL("http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=" +
		// encoded);
		URL url = new URL(
				"http://query.yahooapis.com/v1/public/yql?q=select%20url%20from%20search.web(0)%20where%20query%3D%22%D0%BA%D0%BE%D0%BD%D1%81%D1%83%D0%BB%D1%82%D0%B0%D0%BD%D1%82%20%D0%B5%D0%B2%D1%80%D0%BE%D1%84%D0%B8%D0%BD%D0%B0%D0%BD%D1%81%D0%B8%D1%80%D0%B0%D0%BD%D0%B5%20-dnevnik%2C%20-capital%2C%20-novinite%2C%20-btv%2C%20-dnes.bg%2C%20-tema%2C%20-start.bg%2C%20-need.bg%22&format=json");
		URLConnection connection = url.openConnection();
		// Google// connection.addRequestProperty("Referer",
		// "http://www.d-consult.net");
		String line;
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		logger.info(builder.toString());
		JSONObject json;
		JSONArray results = null;
		try {
			json = new JSONObject(builder.toString());
			results = json.getJSONObject("query").getJSONObject("results").getJSONArray("result");
			logger.info(results.length() + " results ");
		} catch (JSONException e) {
			logger.severe(e.getMessage());
		}
		resp.getOutputStream().println("results:");
		ArrayList<String> urls = new ArrayList<String>();
		ArrayList<SalesLeads.Status> statuses = new ArrayList<SalesLeads.Status>();
		try {
			for (int count = 0; count < results.length(); count++) {
				urls.add(results.getJSONObject(count).get("url").toString());
				statuses.add(SalesLeads.Status.fresh);
			}
		} catch (JSONException e) {
			logger.severe(e.getMessage());
		}
		SalesLeads leads = new SalesLeads();
		leads.setCreationTime(new Date());
		leads.setUrls(urls);
		leads.setStatuses(statuses);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm.makePersistent(leads);
		pm.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
