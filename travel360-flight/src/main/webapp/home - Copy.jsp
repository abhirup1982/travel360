<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="${contextPath}/resources/css/jquery-ui.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/jquery.mThumbnailScroller.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/demos.css">

    <script src="${contextPath}/resources/js/jquery-1.12.4.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>

    <script src="${contextPath}/resources/js/jquery.dataTables.min.js"></script>
    <script src="${contextPath}/resources/js/dataTables.select.min.js"></script>
    <script src="${contextPath}/resources/js/jquery.mThumbnailScroller.js"></script>
    <script src="${contextPath}/resources/js/moment.js"></script>
    <script src="${contextPath}/resources/js/firebase-app.js"></script>
    <script src="${contextPath}/resources/js/firebase-messaging.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDIMwHuGgPF1P7olsp8OYS09FyVyW-P1Ho" async defer></script>
    <script src="${contextPath}/resources/js/travel360-map.js"></script>
    <script src="${contextPath}/resources/js/travel360.js"></script>
    <script src="${contextPath}/resources/js/jquery.slides.min.js"></script>
</head>

<body>
    <div id="loader" style="position: fixed;left: 0px;top: 0px;width: 100%;height: 100%;z-index: 1;">
        <img src="../search/resources/img/loading.gif" style="position: relative;top: 38%;left: 38%;"/>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="row" id="grad1" >
                    <div class="col-md-10">
                        <span style="font-family: inherit;text-align: left;color: #e8e8e8"><spring:message code="welcome.back.message">&nbsp;</spring:message> ${username} &nbsp; !!!</span>
                        <a href="<spring:eval expression="@environment.getProperty('travel360.registration.url')" />/edit?token=${jwt_token}"><span style="color:white;font-family: 'Book Antiqua'"><spring:message code="edit.profile.message"></spring:message></span></a>
                    </div>
                    <div class="col-md-2">
                        <span style="color:white;font-family: 'Book Antiqua'">Not ${username} ?</span> <a href="/search/logout?userName=${username}"><span style="color:white; margin-top: 20px;margin-bottom: 10px;font-family: 'Book Antiqua';text-align: right"><spring:message code="logout.message"></spring:message></span></a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-14">
                        <div class="row">
                            <div style="padding-top: 4%;">
                                <form class="form-inline" method="get" id="searchFlightForm" name="searchFlightForm">
                                    <div style="margin-left: 2%;">
                                        <input name="token" id="token" type="hidden"/>
                                        <input name="subscribed" id="subscribed" type="hidden" value="${subscribed}"/>
                                        <input name="lastLoginAt" id="lastLoginAt" type="hidden" value="${lastLoginAt}"/>
                                        <input name="new_user" id="new_user" type="hidden" value="${new_user}"/>
                                        <input name="jwttoken" id="jwttoken" type="hidden" value="${jwt_token}"/>
                                        <input name="username" id="username" type="hidden" value="${username}"/>
                                        <input name="pointOfSale" id="pointOfSale" type="hidden" value="US"/>
                                        <input name="searchTheme" id="searchTheme" type="hidden"/>
                                        <input name="searchOrigin" id="searchOrigin" type="text" class="search-textbox" placeholder="origin">
                                    </div>
                                    <div style="margin-left: 13%;margin-top: -2%;">
                                        <input name="searchDest" id="searchDest" type="text" class="search-textbox" placeholder="destination">
                                    </div>
                                    <div style="margin-left: 32%;margin-top: -2%;">
                                        <input name="startdatepicker" type="text" id="startdatepicker" class="search-textbox" size="20" placeholder="start date">
                                    </div>
                                    <div style="margin-left: 43%;margin-top: -2%;">
                                        <input name="returndatepicker" type="text" id="returndatepicker" class="search-textbox" size="20" placeholder="return date">
                                    </div>
                                    <div style="margin-left: 57%;margin-top: -2%;">
                                        <p style="color: #333333; font-family:Book Antiqua;font-weight: bold;"><spring:message code="non.stop.message"></spring:message></p>
                                    </div>
                                    <div class="slideThree">
                                        <input name="slideThree" type="checkbox" value="None" id="slideThree" />
                                        <label for="slideThree"></label>
                                    </div>
                                    <div style="position: absolute;margin-left: 77%;margin-top: -4%;width: 8%;">
                                        <p style="color: #333333; font-family:Book Antiqua;font-weight: bold;font-size: medium;"><spring:message code="price.range.message"></spring:message></p>
                                    </div>
                                    <div id="slider-range" style="position: absolute;margin-left: 72%;margin-top: -2%;height: 10px;width: 200px;">
                                        <input name="minfare" id="minfare" type="hidden"/>
                                        <input name="maxfare" id="maxfare" type="hidden"/>
                                    </div>
                                    <div style="margin-left: 92%;margin-top: -5%;">
                                        <a href="#" id="searchFlightLink"><img src="../search/resources/img/search-flight.png" style="height: 65px;"/></a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-11">
                        <div id="searchFlightDiv">
                            <table id="searchFlightRsp" class="compact hover row-border stripe" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th><spring:message code="airline.code.message"></spring:message></th>
                                        <th><spring:message code="departure.date.message"></spring:message></th>
                                        <th><spring:message code="return.date.message"></spring:message></th>
                                        <th><spring:message code="dest.location.message"></spring:message></th>
                                        <th><spring:message code="fare.message"></spring:message></th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div id="collapseDiv" class="col-md-1">
                        <a href="#searchFlightDiv" data-toggle="collapse"><img src="../search/resources/img/expand.png" style="height: 33px; margin-top: 8%;"/></a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div id="content-1" class="content">
                            <p style="color: white; font-size:medium; font-family:Book Antiqua;font-weight: bold;">Don't Have A Destination in mind? Select your dream holiday!</p>
                            <ul>
                                <%--Valid values: BEACH, DISNEY, GAMBLING, HISTORIC, MOUNTAINS, NATIONAL-PARKS, OUTDOORS, ROMANTIC, SHOPPING, SKIING,  THEME-PARK, CARIBBEAN--%>
                                <li><div class = "promo" onmouseleave="$('#beach').hide(700)" onmouseover="$('#beach').show(700)"><img src="../search/resources/img/beach.jpg" class="image_scroll" onload="$('#beach').hide()"/>
                                        <div id="beach" class="hilight">
                                            <i style="font-family: 'Book Antiqua';font-size: small"><spring:message code="beach.message"/></i><p><a class="btn" href="#" onclick="searchThemeRsp('DEN', 'beach')">Search</a></p>
                                        </div>
                                    </div>
                                </li>
                                <li><div class = "promo" onmouseleave="$('#mountains').hide(700)" onmouseover="$('#mountains').show(700)"><img src="../search/resources/img/mountains.jpg" class="image_scroll" onload="$('#mountains').hide()"/>
                                        <div id="mountains" class="hilight">
                                            <i style="font-family: 'Book Antiqua';font-size: small"><spring:message code="ski.message"/></i><p><a class="btn" href="#" onclick="searchThemeRsp('DEN', 'mountains')">Search</a></p>
                                        </div>
                                    </div>
                                </li>
                                <li><div class = "promo" onmouseleave="$('#gambling').hide(700)" onmouseover="$('#gambling').show(700)"><img src="../search/resources/img/gambling.jpg" class="image_scroll" onload="$('#gambling').hide()"/>
                                        <div id="gambling" class="hilight">
                                            <i style="font-family: 'Book Antiqua';font-size: small"><spring:message code="gambling.message"/></i><p><a class="btn" href="#" onclick="searchThemeRsp('DEN', 'gambling')">Search</a></p>
                                        </div>
                                    </div>
                                </li>
                                <li><div class = "promo" onmouseleave="$('#historic').hide(700)" onmouseover="$('#historic').show(700)"><img src="../search/resources/img/historic.jpg" class="image_scroll" onload="$('#historic').hide()"/>
                                        <div id="historic" class="hilight">
                                            <i style="font-family: 'Book Antiqua';font-size: small">Earn Dollars</i><p><a class="btn" href="#" onclick="searchThemeRsp('DEN', 'historic')">Search</a></p>
                                        </div>
                                    </div>
                                </li>
                                <li><div class = "promo" onmouseleave="$('#national-parks').hide(700)" onmouseover="$('#national-parks').show(700)"><img src="../search/resources/img/national-parks.jpg" class="image_scroll" onload="$('#national-parks').hide()"/>
                                        <div id="national-parks" class="hilight">
                                            <i style="font-family: 'Book Antiqua';font-size: small">Earn Dollars</i><p><a class="btn" href="#" onclick="searchThemeRsp('DEN', 'national-parks')">Search</a></p>
                                        </div>
                                    </div>
                                </li>
                                <li><div class = "promo" onmouseleave="$('#outdoors').hide(700)" onmouseover="$('#outdoors').show(700)"><img src="../search/resources/img/outdoors.jpg" class="image_scroll" onload="$('#outdoors').hide()"/>
                                        <div id="outdoors" class="hilight">
                                            <i style="font-family: 'Book Antiqua';font-size: small">Earn Dollars</i><p><a class="btn" href="#" onclick="searchThemeRsp('DEN', 'outdoors')">Search</a></p>
                                        </div>
                                    </div>
                                </li>
                                <li><div class = "promo" onmouseleave="$('#romantic').hide(700)" onmouseover="$('#romantic').show(700)"><img src="../search/resources/img/romantic.jpg" class="image_scroll" onload="$('#romantic').hide()"/>
                                        <div id="romantic" class="hilight">
                                            <i style="font-family: 'Book Antiqua';font-size: small">Earn Dollars</i><p><a class="btn" href="#" onclick="searchThemeRsp('DEN', 'romantic')">Search</a></p>
                                        </div>
                                    </div>
                                </li>
                                <li><div class = "promo" onmouseleave="$('#shopping').hide(700)" onmouseover="$('#shopping').show(700)"><img src="../search/resources/img/shopping.jpg" class="image_scroll" onload="$('#shopping').hide()"/>
                                    <div id="shopping" class="hilight">
                                        <i style="font-family: 'Book Antiqua';font-size: small">Earn Dollars</i><p><a class="btn" href="#" onclick="searchThemeRsp('DEN', 'shopping')">Search</a></p>
                                    </div>
                                </div>
                                </li>
                                <li><div class = "promo" onmouseleave="$('#skiing').hide(700)" onmouseover="$('#skiing').show(700)"><img src="../search/resources/img/skiing.jpg" class="image_scroll" onload="$('#skiing').hide()"/>
                                    <div id="skiing" class="hilight">
                                        <i style="font-family: 'Book Antiqua';font-size: small">Earn Dollars</i><p><a class="btn" href="#" onclick="searchThemeRsp('DEN', 'skiing')">Search</a></p>
                                    </div>
                                </div>
                                </li>
                                <li><div class = "promo" onmouseleave="$('#theme-park').hide(700)" onmouseover="$('#theme-park').show(700)"><img src="../search/resources/img/theme-park.jpg" class="image_scroll" onload="$('#theme-park').hide()"/>
                                    <div id="theme-park" class="hilight">
                                        <i style="font-family: 'Book Antiqua';font-size: small">Earn Dollars</i><p><a class="btn" href="#" onclick="searchThemeRsp('DEN', 'theme-park')">Search</a></p>
                                    </div>
                                </div>
                                </li>
                                <li><div class = "promo" onmouseleave="$('#carribean').hide(700)" onmouseover="$('#carribean').show(700)"><img src="../search/resources/img/carribean.jpg" class="image_scroll" onload="$('#carribean').hide()"/>
                                    <div id="carribean" class="hilight">
                                        <i style="font-family: 'Book Antiqua';font-size: small">Earn Dollars</i><p><a class="btn" href="#" onclick="searchThemeRsp('DEN', 'carribean')">Search</a></p>
                                    </div>
                                </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row" style="padding-top: 5%;padding-bottom: 2%;">
                    <div class="col-md-4">
                        <div id="ad0" class = "promo" onmouseleave="$('#advertisement0').hide(700)" onmouseover="$('#advertisement0').show(700)"><img src="../search/resources/img/" class="advertisement" onload="$('#advertisement0').hide()"/>
                            <div id="advertisement0" class="hilight">
                                <i style="font-family: 'Book Antiqua';font-size: medium"></i><p><a id="hyper0" class="btn" href="#"><spring:message code="book.message"/></a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div id="ad1" class = "promo" onmouseleave="$('#advertisement1').hide(700)" onmouseover="$('#advertisement1').show(700)"><img src="../search/resources/img/" class="advertisement" onload="$('#advertisement1').hide()"/>
                            <div id="advertisement1" class="hilight">
                                <i style="font-family: 'Book Antiqua';font-size: medium"></i><p><a id="hyper1" class="btn" href="#"><spring:message code="book.message"/></a></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div id="ad2" class = "promo" onmouseleave="$('#advertisement2').hide(700)" onmouseover="$('#advertisement2').show(700)"><img src="../search/resources/img/" class="advertisement" onload="$('#advertisement2').hide()"/>
                            <div id="advertisement2" class="hilight">
                                <i style="font-family: 'Book Antiqua';font-size: medium"></i><p><a id="hyper2" class="btn" href="#"><spring:message code="book.message"/></a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="infoDiv" style="background-color: rgba(20, 97, 134, 0.93);position: absolute;left: 110px;bottom: 10px;width: 1100px;height: 300px;">
        <input type="hidden" id="destination"/>
        <input type="hidden" id="pickupTime"/>
        <input type="hidden" id="returnTime"/>
        <div id="mapDiv" style="position: relative;left: 6px;top: 10px;height: 280px;width: 450px;overflow: hidden;   ">

        </div>
        <div id="closeDiv" style="height: 32px;width: 32px;position: relative;left: 1066px;bottom: 278px;">
            <a href="#" onclick="$('#infoDiv').hide(500)"><img src="../search/resources/img/close-icon.png" style="height: 32px;width: 32px;"/></a>
        </div>
        <div style="position: relative;left: 516px;bottom: 255px;width: 68px;">
            <img id="rainImg" src="">
        </div>
        <div style="position: relative;left: 490px;bottom: 250px;width: 109px;text-align: center;">
            <p><span id="rainText" style="color: chartreuse;"></span></p>
        </div>
        <div style="position: relative;left: 585px;bottom: 354px;width: 151px;">
            <span id="temparature" style="color: cyan;font-size: 50px;float: left;"></span>
            <div style="position: relative;left: -1px;top: 7px;font-size: larger;color: cyan;width: 151px;">
                &deg;F
            </div>
        </div>

        <div id="liveclock" class="outer_face" style="left: 556px;top: -253px;">

            <div class="marker oneseven"></div>
            <div class="marker twoeight"></div>
            <div class="marker fourten"></div>
            <div class="marker fiveeleven"></div>

            <div class="inner_face">
                <div class="hand hour"></div>
                <div class="hand minute"></div>
            </div>

        </div>
        <a href="javascript:bookMyCar()" id="bookcar"><img id="bookYourCar" src="../search/resources/img/bookyourcar.png" style="position: relative;left: 749px;bottom: 440px;width: 272px;"></a>
    </div>
</body>
</html>