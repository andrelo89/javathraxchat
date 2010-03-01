<jsp:useBean id="user" class="com.dconsult.model.ContactFormBean" scope="request"/>
<jsp:setProperty name="user" property="*"/><%

RequestDispatcher rd = request.getRequestDispatcher("/donsultsite");
request.setAttribute("contact_form", user);
rd.forward(request, response);

%>