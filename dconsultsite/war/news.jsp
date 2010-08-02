<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

            
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="com.dconsult.jdo.PMF"%>
<%@page import="com.dconsult.model.CMSEntry"%>
<%@page import="java.util.List"%><jsp:include page="/header.jsp" flush="false" />
   
            <div id="middle">
                <div id="sidebar">
                    <h2 class="hblue">Финансиране от ЕС</h2>
					<ul>
							<li> <a href="services.jsp">По каква схема да кандидатствам?</a> </li>
							<li> <a href="services.jsp">Какво трябва да направя?</a> </li>
							<li> <a href="services.jsp">Какви са цените на D Consulting?</a> </li>
							<li>&nbsp;</li>
							<li class="button" onclick="parent.location='services.jsp'">Поискайте предпроектна консултация!</li>
					</ul>                     
                    <h2 class="hblue">Европейски проекти</h2>
					<div class="box">
						<ul>
							<li>ОП "Конкурентноспособност"</li>
							<li>ОП "Човешки ресурси"</li>
							<li>ОП "Административен капацитет"</li>
							<li>ОП "Регионално развитие"</li>
							<li>ОП "Селски райони"</li>
							<li>ОП "Околна среда"</li>
							<li>ОП " Развитие на рибарство"</li>
							<li><a href="news.jsp">Повече информация</a></li>
							<li>&nbsp;</li>
							<li class="button" onclick="parent.location='services.jsp'">Кои предложения са подходящи за моя бизнес?</li>							
						</ul>						
					</div>
					<h2 class="hblue">ЕвроПроект+</h2>
					<div class="box">
						<ul>
							<li>Софтуерно решение на Вашите организационни и административни проблеми</li>
							<li><a href="services.jsp">Кликнете тук за демо на продукта</a></li>							 
						</ul>
					</div>
                </div>                
                <div id="rightnews">
                   <%
				    PersistenceManager pm = PMF.get().getPersistenceManager();
				    String query = "select from " + CMSEntry.class.getName() + " order by displayOrder";
				    List<CMSEntry> entries = (List<CMSEntry>) pm.newQuery(query).execute();
				    for (CMSEntry s : entries) {
					%>
					<div class="box">
                        <h3><%=s.getTitle() %></h3>
                        <%=s.getBody().getValue()%>
                    </div>
					<%} %>
                    
                                      
                </div>
                <div class="clearing">&nbsp;</div>       
            </div>
            
            <jsp:include page="/footer.jsp" flush="false" />
            