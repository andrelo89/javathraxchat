package com.dconsult.crm;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dconsult.jdo.PMF;
import com.dconsult.model.SalesLeads;

@SuppressWarnings("serial")
public class CRMServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger("com.dconsult.crm");

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String lead = req.getParameter("leadkey");
		logger.info(lead);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + SalesLeads.class.getName();
		List<SalesLeads> allLeads = (List<SalesLeads>) pm.newQuery(query).execute();
		SalesLeads leads = allLeads.get(0);
		List<SalesLeads.Status> updateMe = leads.getStatuses();
		if (lead.startsWith(SalesLeads.Status.approved.name())) {
			updateMe.set(
					leads.getUrls().indexOf(
							lead.substring(SalesLeads.Status.approved.name().length())),
					SalesLeads.Status.approved);
			logger.info("new status:" + updateMe.get(
					leads.getUrls().indexOf(
							lead.substring(SalesLeads.Status.approved.name()
											.length()))).name());
		} else if (lead.startsWith(SalesLeads.Status.rejected.name())) {
			updateMe.set(
					leads.getUrls().indexOf(
							lead.substring(SalesLeads.Status.rejected.name().length())),
					SalesLeads.Status.rejected);
			logger.info("new status:" + updateMe.get(
					leads.getUrls().indexOf(
							lead.substring(SalesLeads.Status.rejected.name()
											.length()))).name());
		}
		leads.setStatuses(updateMe);
		
		pm.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}
}
