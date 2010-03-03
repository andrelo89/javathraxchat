
<%@page import="java.util.Date"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="com.dconsult.jdo.PMF"%>
<jsp:useBean id="consultation" class="com.dconsult.model.ConsultationFormBean" scope="request"/>
<jsp:setProperty name="consultation" property="*"/>
<jsp:useBean id="user" class="com.dconsult.model.ContactFormBean" scope="request"/>
<jsp:setProperty name="user" property="*"/>
<%

		String form = request.getParameter("form_name");
		if (form != null) {
			if (form.equals("contact_form")) 
			{
				user.setCreationTime(new Date());
				PersistenceManager pm = PMF.get().getPersistenceManager();
				try {
					pm.makePersistent(user);
				} finally {
					pm.close();
				}
				RequestDispatcher rd = request.getRequestDispatcher("/services.jsp");
				rd.forward(request, response);
			}
			else if (form.equals("consultation_form"))
			{
				consultation.setCreationTime(new Date());
				PersistenceManager pm = PMF.get().getPersistenceManager();
				try {
					pm.makePersistent(consultation);
				} finally {
					pm.close();
				}
				RequestDispatcher rd = request.getRequestDispatcher("/services.jsp");
				rd.forward(request, response);
			}
				
		}
%>