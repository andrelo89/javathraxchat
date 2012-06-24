<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<jsp:include page="/header.jsp" flush="false" />



<div id="tab_collection">
	<ul>
		<li><a href="#tab_id1"><span>Software Development</span></a></li>
		<li><a href="#tab_id3"><span>Business Development</span></a></li>
	</ul>
	<div id="tab_id1">
		<div id="new_left">
			<div class="box">
				<h2>Software Consulting Services</h2>
				<p>
					Our services team here at <span>DConsult</span> is deeply
					committed to the rapid success of our client's projects. We can
					begin a client engagement at any stage of the Software Development
					Cycle. We offer you:
				</p>
			</div>
			<div class="box">
				<ul>
					<li>Absolutely Free Project Analysis and Level of Effort (LOE)
						estimates</li>
					<li>Architectural Analysis of an already existing codebase</li>
					<li>Good old fashioned software development, but done faster
						than you expect</li>
					<li>Stress Test Plans development for servers that will
						experience future heavy load</li>
					<li>Comprehensive Unit Test coverage of all deliverables</li>
					<li>Live training and concepts overview at project handover</li>
				</ul>
			</div>
			<div class="box">
				<p>
					<strong>We did not want to visually pollute this page with
						even more bullet points, so we have intentionally left out some
						things. Please, do contact us with any technical inquiries, so we
						can analyze what we can do for your organization. You can also use
						the Live Chat on the right to get questions answered right away,
						unless we are asleep (rare).</strong>
				</p>
			</div>

		</div>
		<div id="new_right">
			<div id="innertabs">
				<ul>
					<li><a href="#inner_tab_id2"><span>Live Chat</span></a></li>
					<li><a href="#inner_tab_id1"><span>Contact Form</span></a></li>
				</ul>
				<div id="inner_tab_id1">
					<form action="/submitter.jsp" method="post">
						<input type="hidden" name="form_name" value="contact_form" />
						<div class="box">
							<h2>Technical Question Form:</h2>
							<table>
								<tr>
									<td>Company Name</td>
									<td><input name="companyName" type="text" /></td>
								</tr>
								<tr>
									<td>Contact</td>
									<td><input name="contactName" type="text" /></td>
								</tr>
								<tr>
									<td>E-mail</td>
									<td><input name="email" type="text" /></td>
								</tr>
								<tr>
									<td>Question</td>
									<td><textarea cols="21" rows="5" name="question"></textarea></td>
								</tr>
								<tr>
									<td align="right" colspan="2"><input type="submit"
										value="Send" /><input type="reset" value="Clear" /></td>
								</tr>
							</table>
						</div>
					</form>
				</div>
				<div id="inner_tab_id2">
					<form action="/submitter.jsp" method="post">
						<input type="hidden" name="form_name" value="consultation_form" />
						<div class="box">
							<object id="pingboxdkyulpylji000"
								type="application/x-shockwave-flash"
								data="http://wgweb.msg.yahoo.com/badge/Pingbox.swf" width="240"
								height="420">
								<param name="movie"
									value="http://wgweb.msg.yahoo.com/badge/Pingbox.swf" />
								<param name="allowScriptAccess" value="always" />
								<param name="flashvars"
									value="wid=wLR_yXyyVWNHm2oaFMEvag9iJjMdDNFCsdc-" />
							</object>
						</div>
					</form>
				</div>
			</div>
		</div>

	</div>


	<div id="tab_id3">
		<div id="new_left">
			<div class="box">
			</div>
		</div>
		<div id="new_right">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>Under Construction</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
		</div>
	</div>


	<div class="clearing">&nbsp;</div>
</div>

<script>
	$(document).ready(function() {
		$("#tab_collection").tabs();
		$("#innertabs").tabs();
		//$("#innertabs2").tabs();
	});
</script>

<jsp:include page="/footer.jsp" flush="false" />
