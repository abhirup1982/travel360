<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en" >
<head>
	<script src="${contextPath}/resources/js/jquery-1.12.4.js"></script>
	<script src="${contextPath}/resources/js/travel360.js"></script>
	<script src="${contextPath}/resources/js/jquery-ui.min.js"></script>
	<script src="${contextPath}/resources/js/moment.js"></script>
	<link href="${contextPath}/resources/css/style.css" rel="stylesheet">
	<link href="${contextPath}/resources/css/jquery-ui.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
	<input type="hidden" name="edit" id="edit" value="${edit}"/>
	<form:form id="sign-form" class="sign-form" modelAttribute="userViewForm">
		<input type="hidden" name="lang" id="lang" value="${userViewForm.lang}"/>
		<input type="hidden" name="jwt_token" id="jwt_token" value="${userViewForm.jwt_token}"/>
		<ol class="questions">
			<li>
				<span><label for="name"><spring:message code="user.name.ques"/></label></span>
				<input size="20" class="active" id="name" name="name" type="text" placeholder=<spring:message code="placeholder.fullname"/> value="${userViewForm.fullName}" autofocus/>
			</li>
			<li>
				<span><label for="uname"><spring:message code="user.username.ques"/></label></span>
				<input size="20" id="uname" name="username" type="text" placeholder=<spring:message code="placeholder.username"/> value="${userViewForm.userName}" autofocus/>
			</li>
			<li>
				<span><label for="password"><spring:message code="user.password.ques"/></label></span>
				<input id="viewpswd" name="password" type="text" placeholder=<spring:message code="placeholder.password.text"/> value="${userViewForm.userPassword}"/>
				<input id="password" name="password" type="password" placeholder=<spring:message code="placeholder.password"/>  value="${userViewForm.userPassword}" autofocus/>
				<span id="show-pwd" class="view"></span>
			</li>
			<li>
				<span><label for="email"><spring:message code="user.email.ques"/></label></span>
				<input id="email" name="email" type="text" placeholder=<spring:message code="placeholder.email"/> value="${userViewForm.userEmail}" autofocus/>
			</li>
			<li>
				<span><label for="notification"><spring:message code="user.notification.ques"/></label></span>
				<input id="notification" name="notification" type="text" placeholder=<spring:message code="placeholder.yesno"/> value="${userViewForm.notified}" autofocus/>
			</li>
			<li>
				<span><label for="gender"><spring:message code="user.gender.ques"/></label></span>
				<input id="gender" name="gender" type="text" placeholder=<spring:message code="placeholder.gender"/> value="${userViewForm.userGender}" autofocus/>
			</li>
			<li>
				<span><label for="phone"><spring:message code="user.phone.ques"/></label></span>
				<select name="countryCode" class="country"  >
					<option data-countryCode="GB" value="91">(+91) INDIA </option>
					<option data-countryCode="GB" value="44">(+44) UK </option>
					<option data-countryCode="US" value="1">(+1) USA </option>
					<optgroup label="Other countries">
						<option data-countryCode="DZ" value="213"> (+213)Algeria</option>
						<option data-countryCode="AD" value="376"> (+376) Andorra</option>
						<option data-countryCode="AO" value="244">(+244) Angola </option>
						<option data-countryCode="AI" value="1264"> (+1264) Anguilla</option>
						<option data-countryCode="AG" value="1268">(+1268) Antigua &amp; Barbuda </option>
						<option data-countryCode="AR" value="54">(+54) Argentina </option>
						<option data-countryCode="AM" value="374"> (+374) Armenia</option>
						<option data-countryCode="AW" value="297">(+297) Aruba </option>
						<option data-countryCode="AU" value="61"> (+61) Australia</option>
						<option data-countryCode="AT" value="43">(+43) Austria </option>
						<option data-countryCode="AZ" value="994"> (+994) Azerbaijan</option>
						<option data-countryCode="BS" value="1242">(+1242)Bahamas </option>
					</optgroup>
				</select>
				<input id="phone" name="phone" type="text" value="${userViewForm.userPhone}" autofocus/>
			</li>
			<li><p style="margin-top: 48px;font-size: 24pt;float: right;margin-left: 96px;">
				<a href="#" style="color:white;text-decoration:none" name="more" value="more"><spring:message code="user.moreaboutyou"/></a></p>
				<p style="margin-top: 45px;font-size: 28pt;float: right;">
					<a href="#" style="color:white;text-decoration:none" onclick="document.forms['registerForm'].submit()">sign up</a></p>
			</li>
			<li><span><label for="age"><spring:message code="user.birthdate.ques"/></label></span>
				<input id="age" name="age" type="text" placeholder=<spring:message code="placeholder.date"/> value="${userViewForm.userdob}" autofocus/>
			</li>
			<li><span><label for="married"><spring:message code="user.maritalstatus.ques"/></label></span>
				<input id="married" name="married" type="text" placeholder=<spring:message code="placeholder.yesno"/> value="${userViewForm.userMarried}" autofocus/>
			</li>
			<li><span><label for="married"><spring:message code="user.dateofmarriage.ques"/></label></span>
				<input id="marriageDt" name="marriageDt" type="text" placeholder=<spring:message code="placeholder.date"/> value="${userViewForm.userMarriedDt}" autofocus/>
			</li>
			<li><span><label for="kids"><spring:message code="user.travellingWithKids.ques"/></label></span>
				<input id="kids" name="kids" type="text" placeholder=<spring:message code="placeholder.kidsagegroup"/> value="${userViewForm.userKids}" autofocus/>
			</li>
			<li><span><label for="pets"><spring:message code="user.travellingWithPets.ques"/></label></span>
				<input id="pets" name="pets" type="text" placeholder=<spring:message code="placeholder.yesno"/> value="${userViewForm.userPets}" autofocus/>
			</li>
			<li><p style="margin-top: 48px;font-size: 24pt;float: right;margin-right: -91px;">
				<a href="#" style="color:white;text-decoration:none" name="more" value="more"><spring:message code="user.interests.text"/></a></p>
				<p style="margin-top: 45px;font-size: 28pt;float: left;">
					<a href="#" style="color:white;text-decoration:none" onclick="document.forms['registerForm'].submit()"><spring:message code="user.signup"/></a></p>
			</li>
			<li><span><label for="interests"><spring:message code="user.Interests.ques"/></label></span>
				<input id="interests" name="interests" type="text" placeholder=<spring:message code="placeholder.interests"/> value="${userViewForm.userInterests}" autofocus/>
			</li>
			<li><p style="margin-top: 48px;font-size: 24pt;float: right;margin-right: -91px;">
				<a href="#" style="color:white;text-decoration:none" name="more" value="more"><spring:message code="user.occupation.text"/></a></p>
				<p style="margin-top: 45px;font-size: 28pt;float: left;">
					<a href="#" style="color:white;text-decoration:none" onclick="document.forms['registerForm'].submit()"><spring:message code="user.signup"/></a></p>
			</li>
			<li><span><label for="occupation"><spring:message code="user.occupation.ques"/></label></span>
				<input id="occupation" name="occupation" type="text" placeholder=<spring:message code="placeholder.occupation"/> value="${userViewForm.userOccupation}" autofocus/>
			</li>
			<li><span><label for="salary"><spring:message code="user.salary.ques"/></label></span>
				<input id="salary" name="salary" size="10" type="text" placeholder=<spring:message code="placeholder.salary"/> value="${userViewForm.userSalary}" autofocus/>
			</li>
			<li>
				<p style="margin-top: 45px;font-size: 28pt;float: left;">
					<a href="#" style="color:white;text-decoration:none" onclick="document.forms['registerForm'].submit()"><spring:message code="user.signup"/></a></p>
			</li>

		</ol>
		<div id="next-page" alt="Kiwi standing on oval"></div>
		<div class="error-message"></div>
	</form:form>
	<form:form id="registerForm" method="POST" modelAttribute="userForm" class="form-inline">
	<input type="hidden" name="lang" id="lang" value="${userForm.lang}"/>
	<input type="hidden" name="jwt_token" id="jwt_token" value="${userForm.jwt_token}"/>
	<div class="others">
		<ul class="tabs">
			<li class="tab-link" data-tab="tab-1"><spring:message code="tab.account"/></li>
			<li class="tab-link" data-tab="tab-2"><spring:message code="tab.personal"/></li>
			<li class="tab-link" data-tab="tab-3"><spring:message code="tab.interests"/></li>
			<li class="tab-link" data-tab="tab-4"><spring:message code="tab.occupation"/></li>
		</ul>


		<div id="tab-1" class="navigation tab-content">
			<ol>
				<li><spring:message code="message.name"/> : <a href="#" data-ref="name" style="margin-top: -35%;margin-left: 68%;" class="done">${userForm.fullName}</a>
					<input type="hidden" name="fullName" id="fullName" hidden-ref="name" value="${userForm.fullName}"/></li>
				<li><spring:message code="message.username"/> : <a href="#"  data-ref="uname" style="margin-top: -35%;margin-left: 100%;" class="done">${userForm.userName}</a>
					<input type="hidden" name="userName" id="userName" hidden-ref="uname" value="${userForm.userName}"/></li>
				<li><spring:message code="message.email"/> : <a href="#"  data-ref="email" style="margin-top: -35%;margin-left: 70%;" class="done">${userForm.userEmail}</a>
					<input type="hidden" name="userEmail" id="userEmail" hidden-ref="email" value="${userForm.userEmail}"/></li>
				<li><spring:message code="message.password"/> : <a href="#"  data-ref="viewpswd" style="margin-top: -35%;margin-left: 96%;" class="done">${userForm.userPassword}</a>
					<input type="hidden" name="userPassword" id="userPassword" hidden-ref="viewpswd" value="${userForm.userPassword}"/></li>
				<li><spring:message code="message.subscribe"/> : <a href="#"  data-ref="notification" style="margin-top: -35%;margin-left: 227%;" class="done">${userForm.notified}</a>
					<input type="hidden" name="notified" id="notified" hidden-ref="notification" value="${userForm.notified}"/></li>
			</ol>
		</div>
		<div id="tab-2" class="navigation tab-content">
			<ol>
				<li><spring:message code="infoheader.phone"/> :<a href="#" data-ref="phone" style="margin-top: -35%;margin-left: 80%;" class="done">${userForm.userPhone}</a>
					<input type="hidden" name="userPhone" id="userPhone" hidden-ref="phone" value="${userForm.userPhone}"></li>

				<li><spring:message code="infoheader.gender"/> : <a href="#" data-ref="gender" style="margin-top: -35%;margin-left: 80%;" class="done">${userForm.userGender}</a>
					<input type="hidden" name="userGender" id="userGender" hidden-ref="gender" value="${userForm.userGender}"/></li>

				<li><spring:message code="infoheader.married"/> : <a href="#" data-ref="married" style="margin-top: -35%;margin-left: 80%;" class="done">${userForm.userMarried}</a>
					<input type="hidden" name="userMarried" id="userMarried" hidden-ref="married" value="${userForm.userMarried}"/></li>

				<li><spring:message code="infoheader.dateOfmarriage"/> : <a href="#" data-ref="marriageDt" style="margin-top: -32%;margin-left: 148%;" class="done">${userForm.userMarriedDt}</a>
					<input type="hidden" name="userMarriedDt" id="userMarriedDt" hidden-ref="marriageDt" value="${userForm.userMarriedDt}"/></li>

				<li><spring:message code="infoheader.dateOfbirth"/> : <a href="#" data-ref="age" style="margin-top: -33%;margin-left: 117%;" class="done">${userForm.userdob}</a>
					<input type="hidden" name="userdob" id="userdob" hidden-ref="age" value="${userForm.userdob}"/></li>
			</ol>
		</div>

		<div id="tab-3" class="navigation tab-content">
			<ol>
				<li><spring:message code="infoheader.travelingwithkids"/> : <a href="#" data-ref="kids" style="margin-top: -33%;margin-left: 170%" class="done">${userForm.userKids}</a>
					<input type="hidden" name="userKids" id="userKids" hidden-ref="kids" value="${userForm.userKids}"/></li>
				<li><spring:message code="infoheader.travelingwithpets"/> : <a href="#" data-ref="pets" style="margin-top: -33%;margin-left: 172%" class="done">${userForm.userPets}</a>
					<input type="hidden" name="userPets" id="userPets" hidden-ref="pets" value="${userForm.userPets}"/></li>
				<li><spring:message code="infoheader.interests"/> :<a href="#" data-ref="interests" style="margin-top: -35%;margin-left: 80%;" class="done">${userForm.userInterests}</a>
					<input type="hidden" name="userInterests" id="userInterests" hidden-ref="interests" value="${userForm.userInterests}"></li>
			</ol>
		</div>
		<div id="tab-4" class="navigation tab-content">
			<ol>
				<li><spring:message code="infoheader.occupation"/> :<a href="#" data-ref="occupation" style="margin-top: -35%;margin-left: 236%;" class="done">${userForm.userOccupation}</a>
					<input type="hidden" name="userOccupation" id="userOccupation" hidden-ref="occupation" value="${userForm.userOccupation}"></li>
				<li><spring:message code="infoheader.salary"/> :<a href="#" data-ref="salary" style="margin-top: -35%;margin-left: 80%;" class="done">${userForm.userSalary}</a>
					<input type="hidden" name="userSalary" id="userSalary" hidden-ref="salary" value="${userForm.userSalary}"></li>
			</ol>
		</div>
		</form:form>
	</div>
</div>
<h1 id="wf" style="opacity:0;width: 600px; margin-top:1.1em;display:none; margin-bottom:1em">Thank you</h1>
</body>
</html>
