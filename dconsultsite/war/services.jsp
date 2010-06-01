<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

            <jsp:include page="/header.jsp" flush="false" />
            
            
		    
            <div id="tab_collection">
	            <ul>
			        <li><a href="#tab_id1"><span>Консултантски услуги</span></a></li>
			        <li><a href="#tab_id3"><span>Услуги за Общини</span></a></li>
			    </ul>
			    <div id="tab_id1"> 
                <div id="new_left">
                    <div class="box">
                        <h2>Консултантски услуги</h2>
                      	<p>
                      	Услугите на <span>Д Консулт</span> са фокусирани в подготовката, управлението и отчитането на проекти за получаване на безвъзмездна финансова помощ. Предлагаме Ви:	
                      	</p>
                    </div>
					<div class="box">
                        <ul>
                        	<li>Предпроектна консултация за възможностите за финансиране на Вашата фирма</li>
							<li>Подготовка на документи за кандидатстване по различни програми за финансиране</li>
							<li>Проучване на възможности на допълнително финансиране (при нужда)</li>
							<li>Управление на спечелени проекти</li>
							<li>Отчитане на приключени проекти</li>
							<li>Обучение за работа със специализиран софтуер за подготвяне и управление на проекти</li>
						</ul>
                    </div>
                    <div class="box">
                      	<p>
                      	<strong>Ако нямате конкретна идея, но желаете да кандидатствате за финансиране, може да попълните формуляра за връзка с наш консултант. Ако имате идея и искате да Ви помогнем с нейното изпълнение, моля попълнете формата за предпроектна консултация.</strong>	
                      	</p>
                      	<p>
                      	<strong>Ако желаете да кандидатствате за финансиране с наша помощ, <a id="consultation_show" href="#">тук</a> може да изчислите и нашата такса в замисимост от желаната от Вас сума на финансиране.</strong>	
                      	</p>
                    </div>
                    
                </div> 
                <div id="new_right">
                <div id="innertabs">
	                <ul>
				        <li><a href="#inner_tab_id1"><span>Връзка с Консултант</span></a></li>
				        <li><a href="#inner_tab_id2"><span>Предпроектна Консултация</span></a></li>
				    </ul>
				    <div id="inner_tab_id1">
                	<form action="/submitter.jsp" method="post">
                	<input type="hidden" name="form_name" value="contact_form"/>
					<div class="box">
                        <h2>Попълнете този формуляр за връзка с наш консултант:</h2>
                        <table>
                        	<tr><td>Име на компанията</td><td><input name="companyName" type="text"/></td></tr>
                        	<tr><td>Име на контакт</td><td><input name="contactName" type="text"/></td></tr>
                        	<tr><td>Адрес</td><td><input name="address" type="text"/></td></tr>
                        	<tr><td>Телефон</td><td><input name="telephone" type="text"/></td></tr>
                        	<tr><td>Мобилен телефон</td><td><input name="mobile" type="text"/></td></tr>
                        	<tr><td>E-mail</td><td><input name="email" type="text"/></td></tr>
                        	<tr><td>Уеб страница</td><td><input name="website" type="text"/></td></tr>
                        	<tr><td>Размер на желаното финансиране*</td><td><input name="desiredMoney" type="text"/></td></tr>
                        	<tr><td align="right" colspan="2"><input type="submit" value="Изпрати" /><input type="reset" value="Изчисти" /></td></tr>
                        </table>
                    </div>			
                    </form>		
                  </div> 
				    <div id="inner_tab_id2">
	                	<form action="/submitter.jsp" method="post">
	                	<input type="hidden" name="form_name" value="consultation_form"/>
						<div class="box">
	                        <h2>Попълнете този формуляр за безплатна предпроектна консултация:</h2>
	                        <table>
	                        	<tr><td colspan="2">Безвъзмездна помощ</td></tr>
	                        	<tr><td>Опишете накратко Вашите нужди за финансиране</td><td><input name="financingNeeds" type="text"/></td></tr>
	                        	<tr><td>До какъв % от проекта бихте могли да осигурите кофинансиране?</td><td><input name="percentageCoFund" type="text"/></td></tr>
	                        	<tr><td colspan="2">Информация за компанията</td></tr>
	                        	<tr><td>Основна дейност</td><td><input name="primaryCompanyActivity" type="text"/></td></tr>
	                        	<tr><td>Размер на годишния оборот за предходната година **</td><td><input name="companyEarnings" type="text"/></td></tr>
	                        	<tr><td>Брой служители (до 10, 10 - 50, 50 - 150, над 150)**</td><td><input name="numberOfEmployees" type="text"/></td></tr>
	                        	<tr><td>Получавали ли сте безвъзмездно финансиране до сега? Ако да, пояснете.</td><td><input name="priorFinancing" type="text"/></td></tr>
	                        	<tr><td>Име на компанията</td><td><input name="companyName" type="text"/></td></tr>
	                        	<tr><td>Име на контакт</td><td><input name="contactName" type="text"/></td></tr>
	                        	<tr><td>Адрес</td><td><input name="address" type="text"/></td></tr>
	                        	<tr><td>Телефон</td><td><input name="telephone" type="text"/></td></tr>
	                        	<tr><td>Мобилен Телефон</td><td><input name="mobile" type="text"/></td></tr>
	                        	<tr><td>E-mail</td><td><input name="email" type="text"/></td></tr>
	                        	<tr><td>Уеб Страница</td><td><input name="webAddress" type="text"/></td></tr>
	                        	<tr><td align="right" colspan="2"><input type="submit" value="Изпрати" /><input type="reset" value="Изчисти" /></td></tr>
	                        </table>
	                    </div>
	                    </form>					
	                </div>
				</div>
				</div>
                       
                </div>
                        
              
                <div id="tab_id3">
                <div id="new_left">
					<div class="box">
                        <h2>Консултантски услуги за общини</h2>
                        <p>Услугите на Д Консулт са фокусирани в подготовката, управлението и отчитането на проекти за получаване на безвъзмездна финансова помощ. Предлагаме Ви:		
                        </p>
						<ul>
							<li>Предпроектна консултация за възможностите за финансиране на
							Вашата община</li>
							<li>Изготвяне на стратегически план за развитие на общината</li>
							<li>Подготовка на документи за кандидатстване по различни програми
							за финансиране</li>
							<li>Проучване на възможности на допълнително финансиране (при
							нужда)</li>
							<li>Управление на спечелени проекти</li>
							<li>Отчитане на приключени проекти</li>
							<li>Обучение за работа със специализиран софтуер за подготвяне и
							управление на проекти</li>
						</ul>
						<p style="font-weight:bold">Ако желаете да кандидатствате за финансиране, но не сте сигурни дали можете, моля попълнете формуляра за връзка с някой от нашите консултанти - те ще се свържат с Вас за безплатна проектна консултация!
						</p>					
						<p style="font-weight:bold">Ако желаете да кандидатствате за финансиране с наша помощ, тук може да изчислите и нашата такса в замисимост от желаната от Вас сума на финансиране.
						</p>					
                    </div>
                </div>
                <div id="new_right">
                <div id="innertabs2">
	                <ul>
				        <li><a href="#inner2_tab_id1"><span>Връзка с Консултант</span></a></li>
				        <li><a href="#inner2_tab_id2"><span>Предпроектна Консултация</span></a></li>
				    </ul>
				    <div id="inner2_tab_id1">
                	<form action="/submitter.jsp" method="post">
                	<input type="hidden" name="form_name" value="contact_form"/>
					<div class="box">
                        <h2>Попълнете този формуляр за връзка с наш консултант:</h2>
                        <table>
                        	<tr><td>Име на общината</td><td><input name="companyName" type="text"/></td></tr>
                        	<tr><td>Име на контакт</td><td><input name="contactName" type="text"/></td></tr>
                        	<tr><td>Адрес</td><td><input name="address" type="text"/></td></tr>
                        	<tr><td>Телефон</td><td><input name="telephone" type="text"/></td></tr>
                        	<tr><td>Мобилен телефон</td><td><input name="mobile" type="text"/></td></tr>
                        	<tr><td>E-mail</td><td><input name="email" type="text"/></td></tr>
                        	<tr><td>Уеб страница</td><td><input name="website" type="text"/></td></tr>
                        	<tr><td>Размер на желаното финансиране*</td><td><input name="desiredMoney" type="text"/></td></tr>
                        	<tr><td align="right" colspan="2"><input type="submit" value="Изпрати" /><input type="reset" value="Изчисти" /></td></tr>
                        </table>
                    </div>			
                    </form>		
                  </div> 
				    <div id="inner2_tab_id2">
	                	<form action="/submitter.jsp" method="post">
	                	<input type="hidden" name="form_name" value="consultation_form"/>
						<div class="box">
	                        <h2>Попълнете този формуляр за безплатна предпроектна консултация:</h2>
	                        <table>
	                        	<tr><td colspan="2">Безвъзмездна помощ</td></tr>
	                        	<tr><td>Опишете накратко Вашите нужди за финансиране</td><td><input name="financingNeeds" type="text"/></td></tr>
	                        	<tr><td>Размер на желаното финансиране</td><td><input name="percentageCoFund" type="text"/></td></tr>
	                        	<tr><td colspan="2">Информация за общината</td></tr>
	                        	<tr><td>Получавали ли сте безвъзмездно финансиране до сега? Ако да, пояснете.</td><td><input name="priorFinancing" type="text"/></td></tr>
	                        	<tr><td>Име на общината</td><td><input name="companyName" type="text"/></td></tr>
	                        	<tr><td>Име на контакт</td><td><input name="contactName" type="text"/></td></tr>
	                        	<tr><td>Адрес</td><td><input name="address" type="text"/></td></tr>
	                        	<tr><td>Телефон</td><td><input name="telephone" type="text"/></td></tr>
	                        	<tr><td>Мобилен Телефон</td><td><input name="mobile" type="text"/></td></tr>
	                        	<tr><td>E-mail</td><td><input name="email" type="text"/></td></tr>
	                        	<tr><td>Уеб Страница</td><td><input name="webAddress" type="text"/></td></tr>
	                        	<tr><td align="right" colspan="2"><input type="submit" value="Изпрати" /><input type="reset" value="Изчисти" /></td></tr>
	                        </table>
	                    </div>
	                    </form>					
	                </div>
				</div>
				</div>
                </div>
              
                
                <div class="clearing">&nbsp;</div>       
            </div>
            
			<script>
			$(document).ready(function() {
			    $("#tab_collection").tabs();
			    $("#innertabs").tabs();
			    $("#innertabs2").tabs();
			  });
			</script>
			  
            <jsp:include page="/footer.jsp" flush="false" />
            