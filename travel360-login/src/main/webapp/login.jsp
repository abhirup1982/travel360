<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title><spring:message code="login.your.account"/></title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <link href="${contextPath}/resources/css/login_style.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <link rel="stylesheet" href="${contextPath}/resources/css/base.css" />
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css" />
    <script src="${contextPath}/resources/js/jquery-1.9.1.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

    <![endif]-->

    <script>
        $(document).ready(function() {
            $( '.dropdown' ).hover(
                function(){
                    $(this).children('.sub-menu').slideDown(200);
                },
                function(){
                    $(this).children('.sub-menu').slideUp(200);
                }
            );
        });
    </script>
</head>

<body>
<div class="bg">
<nav>
    <ul class="content clearfix">
        <li class="dropdown">
            <a style="font-family: 'Book Antiqua'" href="#"><spring:message code="msg.choose.language"/></a>
            <ul class="sub-menu">
                <li><a href="<%=request.getContextPath()%>/doChangeLocale?lang=en"><b style="font-family: 'Book Antiqua'">English</b></a></li>
                <li><a href="<%=request.getContextPath()%>/doChangeLocale?lang=cn"><b><spring:message code="lang.chinese"/></b></a></li>
            </ul>
        </li>
    </ul>
</nav>
    <form class="form-1" method="POST" action="${contextPath}/login">
        <p class="field">
            <c:if test="${not empty message}">
                <span><spring:message code="${message}"/></span>
            </c:if>
        </p>
        <p class="field">
            <input type="text" name="username" aria-describedby="username" placeholder="<spring:message code="username.message"/>" autofocus="true"/>
            <i class="icon-user icon-large"></i>
        </p>
        <p class="field">
            <input type="password" name="password" aria-describedby="username" placeholder="<spring:message code="password.message"/>">
            <i class="icon-lock icon-large"></i>
        </p>
        <p class="submit">
            <button type="submit" name="submit"><i class="icon-arrow-right icon-large"></i></button>
        </p>
        <p>
        <c:if test="${not empty error}">
            <span><spring:message code="${error}"/></span>
        </c:if>
        </p>
        <p>
            <a href="<spring:eval expression="@environment.getProperty('travel360.registration.url')" />/home?language=${lang}"><spring:message code="registration.create.account"/></a>
        </p>
    </form>

 <%--   <form method="POST" action="${contextPath}/login" class="form-signin">

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="<spring:message code="username.message"/>"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="<spring:message code="password.message"/>"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="lang.login"/></button>
            <h4 class="text-center"><a href="${contextPath}/registration"></a></h4>
        </div>

    </form>--%>

</div>
</div>
<!-- /container -->
</body>
</html>
