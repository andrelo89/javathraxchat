package com.dconsult.cms;

import java.io.IOException;
import java.util.Date;
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
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

@SuppressWarnings("serial")
public class CMSServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger("com.dconsult.cms");

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Key key = KeyFactory.stringToKey(req.getParameter("key"));
		PersistenceManager pm = PMF.get().getPersistenceManager();
		CMSEntry entry = pm.getObjectById(CMSEntry.class, key);
		
		if(req.getParameter("delete") != null)
		{
			logger.info("Deleting " + entry.getTitle());
			pm.deletePersistent(entry);
		}
		else if(req.getParameter("order") != null)
		{
			logger.info("Changing order " + entry.getTitle());
			if(entry.getDisplayOrder() == null)
				entry.setDisplayOrder(0);
			if( "up".equals(req.getParameter("order")))
			{
				entry.setDisplayOrder(entry.getDisplayOrder() + 1);
			}
			else if( "down".equals(req.getParameter("order")))
			{
				entry.setDisplayOrder(entry.getDisplayOrder() - 1);
			}
		}
		else
			logger.info("Got " + entry.getTitle());
		
		pm.close();
		try {
			getServletConfig().getServletContext().getRequestDispatcher("/cms.jsp").forward(req, resp);
		} catch (ServletException e) {
			logger.severe(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		logger.info("Saving " + title);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		CMSEntry entry;
		if(req.getParameter("key") == null)
		{
			entry = new CMSEntry();
			entry.setCreationTime(new Date());
			entry.setDisplayOrder(0);
			pm.makePersistent(entry);
		}
		else
			entry = pm.getObjectById(CMSEntry.class, req.getParameter("key"));
		
		entry.setBody(new Text(body));
		entry.setTitle(title);
		entry.setModificationTime(new Date());
		
		pm.close();
		getServletConfig().getServletContext().getRequestDispatcher("/cms.jsp").forward(req, resp);
	}
}
