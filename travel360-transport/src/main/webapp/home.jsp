<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${contextPath}/transport/resources/css/jquery-ui.min.css">
    <link rel="stylesheet" href="${contextPath}/transport/resources/css/style.css">
    <link rel="stylesheet" href="${contextPath}/transport/resources/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="${contextPath}/transport/resources/css/bootstrap.css">

    <script src="${contextPath}/transport/resources/js/jquery-1.12.4.js"></script>
    <script src="${contextPath}/transport/resources/js/jquery-ui.min.js"></script>
    <script src="${contextPath}/transport/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/transport/resources/js/travel360.js"></script>
    <script src="${contextPath}/transport/resources/js/jquery.dataTables.min.js"></script>
    <script src="${contextPath}/transport/resources/js/dataTables.select.min.js"></script>

</head>
<body>
<input name="language" id="language" type="hidden" value="${language}"/>
<input name="jwttoken" id="jwttoken" type="hidden" value="${jwt_token}"/>
<input name="username" id="username" type="hidden" value="${username}"/>
<input name="username" id="subscribed" type="hidden" value="${subscribed}"/>
<input name="username" id="lastLoginAt" type="hidden" value="${lastLoginAt}"/>
<div class="container">
    <div class="row" style="background-color: rgba(51, 49, 53, 0.18);">
        <div class="col-lg-4">
            <img src="../transport/resources/img/bus.gif" style="height: 100%;width:70%;"/>
        </div>
        <div class="col-lg-7">
            <h1 style="align-self: center;color: rgba(224, 42, 29, 0.75);font-style: italic;font-family: Book Antiqua;"><spring:message code="bus.title.message"/> </h1>
        </div>
        <div class="col-lg-1">
            <a href="javascript:searchFlights()"><img src="../transport/resources/img/return.png" style="width: 50px;"/></a>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <table id="transportDetailsRsp" class="compact hover row-border stripe" cellspacing="0" width="100%">
                <thead>
                <tr style="background-color: aliceblue;font-family: tahoma;font-size: 15px;">
                    <th><spring:message code="bus.data1.message"/></th>
                    <th><spring:message code="bus.data2.message"/></th>
                    <th><spring:message code="bus.data3.message"/></th>
                    <th><spring:message code="bus.data4.message"/></th>
                    <th><spring:message code="bus.data5.message"/></th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row" style="background-color: rgba(51, 49, 53, 0.18);">
        <div class="col-lg-4">
            <img src="../transport/resources/img/carworld.gif" style="height: 100%;width:70%;"/>
        </div>
        <div class="col-lg-8">
            <h1 style="align-self: center;color: rgba(224, 42, 29, 0.75);font-style: italic;font-family: Book Antiqua;"><spring:message code="car.title.message"/></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <table id="showCarRentalRsp" class="compact hover row-border stripe" cellspacing="0" width="100%">
                <thead>
                    <tr style="background-color: aliceblue;font-family: tahoma;font-size: 15px;">
                        <th><spring:message code="bus.tblcolhead.vendorcompname"/></th>
                        <th><spring:message code="bus.tblcolhead.vehtype"/></th>
                        <th><spring:message code="bus.tblcolhead.avlstatus"/></th>
                        <th><spring:message code="bus.tblcolhead.extramileage"/></th>
                        <th><spring:message code="bus.tblcolhead.allowance"/></th>
                        <th><spring:message code="bus.tblcolhead.totalcharge"/></th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>