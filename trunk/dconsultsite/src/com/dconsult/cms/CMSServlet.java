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

@SuppressWarnings("serial")
public class CMSServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger("com.dconsult.cms");

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String entryId = req.getParameter("entryId");
		logger.info(entryId);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		if(entryId == null)
		{
			String query = "select from " + CMSEntry.class.getName() + "where " + "";
			List<CMSEntry> allLeads = (List<CMSEntry>) pm.newQuery(query).execute();
			
		}
		
		pm.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}
}
