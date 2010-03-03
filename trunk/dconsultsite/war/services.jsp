<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

            <jsp:include page="/header.jsp" flush="false" />
            
            <div id="middle">
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
                      	<strong>Ако не сте уверени, че Вашата фирма може да кандидатства за финансиране, моля попълнете <a id="contact_form_show" href="#">формуляра за връзка</a> с някой от нашите консултанти - те ще се свържат с Вас и ще проведат напълно безплатна проектна консултация с Вас!</strong>	
                      	</p>
                      	<p>
                      	<strong>Ако желаете да кандидатствате за финансиране с наша помощ, <a id="consultation_show" href="#">тук</a> може да изчислите и нашата такса в замисимост от желаната от Вас сума на финансиране.</strong>	
                      	</p>
												
                    </div>
                    
                </div>                
                <div id="new_right">
					<div class="box">
                        <h2>ЕвроПроект+</h2>
                        <h3>...софтуер за подготовка и управление на Вашите проекти</h3>
                        <p>ЕвроПроект+ Ви помага да подготвяте и следите развитието на няколко проекта едновременно, никога да не пропускате краен срок или дата за отчет, както и да поддържате така важните за всеки консултантски бизнес взаимоотношения с клиенти и финансиращи организации.		
                        </p>
						<p>Този изключителен продукт Ви дава възможност да се фокусирате върху намирането на нови източници за финансиране на проектите на клиентите си, а не да губите времето си в безкрайно организиране на документи, контакти и календари.		
						</p>
						<p>ЕвроПроект+ е създаден, за да Ви помага не само в процеса на организиране и подготвяне на проектни предложения, а и в усъвършенстването на Вашата работа. Една от функционалностите на този уникален софтуер е генерирането на широка гама от документи в .pdf, .doc или .xls формат с цел анализ на дейността на Вашия бизнес за определен период от време.		
						</p>
						<p>Кратка презентация на ЕвроПроект+ - <a href="#">натиснете тук</a>		
						</p>					
						<p>Демо на ЕвроПроект+ - <a href="#">натиснете тук</a>		
						</p>					
						<p><a href="#">Как да си купя ЕвроПроект+?</a>
						</p>					
                    </div>					
                </div>
                <div id="contact_form" style="display:none">
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
                <div id="consultation_form" style="display:none">
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
                <div class="clearing">&nbsp;</div>       
            </div>
            
            <script src="http://code.jquery.com/jquery-latest.js"></script>
  
			<script>
			$(document).ready(function(){
			    
			  $("#contact_form_show").click(function () {
			    $("#new_right").hide("fast");
			    $("#consultation_form").hide("fast");
			    $("#contact_form").show("fast");
			  });    
			  $("#consultation_show").click(function () {
			    $("#new_right").hide("fast");
			    $("#contact_form").hide("fast");
			    $("#consultation_form").show("fast");
			  });    
			
			});
			</script>
			  
            <jsp:include page="/footer.jsp" flush="false" />
            