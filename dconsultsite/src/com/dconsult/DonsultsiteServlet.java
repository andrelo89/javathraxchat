package com.dconsult;

import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.dconsult.jdo.PMF;
import com.dconsult.model.ContactFormBean;

@SuppressWarnings("serial")
public class DonsultsiteServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String form = req.getParameter("form_name");
		if (form != null) {
			if (form.equals("contact_form")) {
//				ContactFormBean saveMe = new ContactFormBean();
//				saveMe.setCompanyName(req.getParameter("companyName"));
//				saveMe.setContactName(req.getParameter("contactName"));
//				saveMe.setAddress(req.getParameter("address"));
//				saveMe.setTelephone(req.getParameter("telephone"));
//				saveMe.setMobile(req.getParameter("mobile"));
//				saveMe.setEmail(req.getParameter("email"));
//				saveMe.setWebsite(req.getParameter("website"));
//				saveMe.setDesiredMoney(req.getParameter("desiredMoney"));
				
				ContactFormBean saveMe = (ContactFormBean) req.getAttribute("contact_form");
				saveMe.setCreationTime(new Date());
				PersistenceManager pm = PMF.get().getPersistenceManager();
				try {
					pm.makePersistent(saveMe);
				} finally {
					pm.close();
				}
			}
			resp.sendRedirect("/services.jsp");
		}
	}
}
