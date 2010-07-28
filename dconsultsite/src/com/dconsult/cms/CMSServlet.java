package com.dconsult.cms;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dconsult.jdo.PMF;
import com.dconsult.model.CMSEntry;
import com.dconsult.model.SalesLeads;
import com.google.appengine.api.datastore.Text;

@SuppressWarnings("serial")
public class CMSServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger("com.dconsult.cms");

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			doPost(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		logger.info("Saving " + title);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		CMSEntry entry = new CMSEntry();
		entry.setBody(new Text(body));
		entry.setTitle(title);
		pm.makePersistent(entry);
		
		pm.close();
		getServletConfig().getServletContext().getRequestDispatcher("/cms.jsp").forward(req, resp);
	}
}
