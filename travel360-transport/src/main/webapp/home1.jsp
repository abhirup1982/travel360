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
<input type="hidden" id="username" value="${username}"/>
<div class="bg">
    <div class="transport-widget">
        <div class ="row" style="background-color:hsla(192, 95%, 83%, 0.58);height: 109px;width: 83%;" id="showBusLinkDiv">
            <div class="col-md-3">
                <img src="../transport/resources/img/bus.gif" style="height: 100%;width:70%;"/>
            </div>
            <div class="col-md-2"></div>
            <div  class="col-md-7" style="align-self: center;color: #e5a289;font-style: oblique;font-family: fantasy;">
                <h1>Local Bus Routes</h1>
            </div>
        </div>
        <div style="margin-left: 10%;margin-top: 3%" id="showTransportDiv">
            <table id="transportDetailsRsp" class="compact hover row-border stripe" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>Route Code</th>
                    <th>Fare($)</th>
                    <th>Route</th>
                    <th>First Departure at</th>
                    <th>Frequency</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <div class ="row" style="background-color:hsla(192, 95%, 83%, 0.58);height: 109px;width: 83%;" id="showCarRentalLinkDiv">
            <div class="col-md-3">
                <img src="../transport/resources/img/carworld.gif" style="height: 100%;width:70%;"/>
            </div>
            <div class="col-md-2"></div>
            <div  class="col-md-7" style="align-self: center;color: #e5a289;font-style: oblique;font-family: fantasy;">
                <h1>Love the road...Rent a car!<h1>
            </div>
        </div>

        <div style="margin-left: 10%;margin-top: 3%" id="showCarRentalDiv">
            <table id="showCarRentalRsp" class="compact hover row-border stripe" cellspacing="0" width="100%">
                <thead>
                    <tr>
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