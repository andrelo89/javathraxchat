package com.dconsult;

import java.io.IOException;

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
				ContactFormBean saveMe = (ContactFormBean) req.getAttribute("contact");
				PersistenceManager pm = PMF.get().getPersistenceManager();
				try {
					pm.makePersistent(saveMe);
				} finally {
					pm.close();
				}
			}
//			resp.sendRedirect("/guestbook.jsp");
		}
	}
}
