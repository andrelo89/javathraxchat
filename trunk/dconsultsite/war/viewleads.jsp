<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.dconsult.model.SalesLeads" %>
<%@page import="com.dconsult.jdo.PMF"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 
<jsp:include page="/header.jsp" flush="false" />


<div id="middle">
	<div id="salesleads">
	<ol>
		
<%
    PersistenceManager pm = PMF.get().getPersistenceManager();
    String query = "select from " + SalesLeads.class.getName();
    List<SalesLeads> leads = (List<SalesLeads>) pm.newQuery(query).execute();
    List<String> urls;
    if(leads.size() < 1)
    {
	   	SalesLeads mock =  new SalesLeads();
	   	ArrayList<String> newUrls = new ArrayList<String>();
	   	ArrayList<SalesLeads.Status> newStatus = new ArrayList<SalesLeads.Status>();
	   	newUrls.add("http://www.google.com");
	   	newUrls.add("http://www.yahoo.com");
	   	newUrls.add("http://www.bing.com");
	   	newUrls.add("http://www.thing.com");
	   	newUrls.add("http://www.ding.com");
	   	
	   	newStatus.add(SalesLeads.Status.fresh);
	   	newStatus.add(SalesLeads.Status.fresh);
	   	newStatus.add(SalesLeads.Status.fresh);
	   	newStatus.add(SalesLeads.Status.fresh);
	   	newStatus.add(SalesLeads.Status.fresh);
	   	
	   	mock.setUrls(newUrls);
		mock.setStatuses(newStatus);
		pm.makePersistent(mock);
		urls = newUrls;
    }
    else
    {	 urls = leads.get(0).getUrls();}
    
    if (urls.isEmpty()) {
%>
No new leads
<%
    } else {
        for (String s : urls) {
		%>
		<li>
			 <input id="glog" type="image" src="images/button-ok.png" name="approved<%=s%>" alt="approve" />
			 <input id="glog" onclick="" type="image" src="images/button-cancel.png" name="rejected<%=s%>" alt="reject" />
			 <a href="<%=s%>"><%=s%></a> 
			 <span></span>
		</li>
		<%
        }
    }
    pm.close();
%>
	</ol>
	</div>
</div>
			<script>
			$(function() {
				function runEffect( o ){
					var options = {};
					if(o.name.startsWith('approve'))
						$("input[name=" + o.name + "] ~ a").animate({ color:"white",  backgroundColor: "#00d61d" }, 500);
					else if (o.name.startsWith('reject'))
						$("input[name=" + o.name + "] ~ a").animate({ color:"white", backgroundColor: "#990003" }, 500);
					
					//$("input[name=" + o.name + "]").toggle('fade',options,1000);
					//setTimeout(function(){
					//	$("input[name=" + o.name + "]").stop();						
					//}, 500);
					
				};
				
				//set effect from select menu value
				$("input").click(function() {
					$.get('/crm?' + 'leadkey=' + $.URLEncode(this.name), function(data) {
						//alert(data);
						$("input[name=" + this.name + "] ~ span").html("OK");
						});
					runEffect(this);
					//$("input[name=" + this.name + "]").unbind('click');
					return false;
				});

			});
			</script>
			
  <jsp:include page="/footer.jsp" flush="false" />
