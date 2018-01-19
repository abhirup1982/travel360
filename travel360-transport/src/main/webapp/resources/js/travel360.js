$( function() {

    $("#username").change(new function () {
        if($("#username").val().trim().length == 0) {
            window.location.href = "/transport/logout";
        }
    });

	var getUrlParameter = function getUrlParameter(sParam) {
	    var sPageURL = decodeURIComponent(window.location.search.substring(1));
	    var sURLVariables = sPageURL.split('&');
	    var sParameterName;
	    var i;

	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');

	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : sParameterName[1];
	        }
	    }
	};
	
	//start
	 $('#transportDetailsRsp').dataTable(
	            {
	                "ajax": {
	                    "url" : "../transport/resources/js/busresp.json",
	                    "type" : "GET",
	                    "dataSrc": function (json) {
	                        return json.BusRouteInfo;
	                    }
	                },
	                "columns": [
	                    { "data": "RouteId.Code"},
	                    { "data": "RouteId.Fare" },
	                    { "data": "Route.RouteDesc" },
	                    { "data": "FirstDepartureTime" },
	                    { "data": "Freq" }
	                ],
	                "pageLength": 5,
	                "searching": false,
	                "columnDefs": [{"targets": [0, 1, 2, 3, 4], "orderable" : false,}],
	                "bLengthChange": false,
	                "select": {
	                    "style": 'single'
	                }
	            }
	        );     

  //start
	 $('#showCarRentalRsp').dataTable(
	            {
	            	
	                "ajax": {
                     "url" : "../transport/carrental/data"+
                     "?pickUpDateTime="+getUrlParameter('pickuptime')+
                     "&returnDateTime="+getUrlParameter('returnTime')+
                     "&locationCode="+getUrlParameter('destination'),
	                    "type" : "GET",
	                    "dataSrc": function (json) {
	                    	var data = json.OTA_VehAvailRateRS.VehAvailRSCore.VehVendorAvails.VehVendorAvail;
	                    	if(data == null) {
	                    	    data = JSON.parse("[{\"RPH\": \"1\",\"VehAvailCore\": {\"RentalRate\": {\"AvailabilityStatus\": \"\",\"Vehicle\": {\"VehType\": [\"\"]}},\"VehicleCharges\": {\"VehicleCharge\": {\"Mileage\": {\"Allowance\": \"\",\"ExtraMileageCharge\": \"\"},\"TotalCharge\": {\"Amount\": \"\"}}}},\"Vendor\": {\"CompanyShortName\": \"\"}}]");
                            }
                            return data;
	                    }
	                },
	                "columns": [
	                    { "data": "Vendor.CompanyShortName" },
	                    { "data": "VehAvailCore.RentalRate.Vehicle.VehType" },
	                    { "data": "VehAvailCore.RentalRate.AvailabilityStatus" },
                        { "data": "VehAvailCore.VehicleCharges.VehicleCharge.Mileage.Allowance" },
	                    { "data": "VehAvailCore.VehicleCharges.VehicleCharge.Mileage.ExtraMileageCharge" },
	                    { "data": "VehAvailCore.VehicleCharges.VehicleCharge.TotalCharge.Amount" }
	                ],
	                "pageLength": 4,
	                "searching": false,
	                "columnDefs": [{"targets": [0, 1, 2, 3 , 4], "orderable" : false,}, {"targets": [5], "orderable" : true,}],
	                "bLengthChange": false,
	                "select": {
	                    "style": 'single'
	                }
	            }
	        );
} );


function searchFlights() {
    window.location.href = "/transport/navigate/flight?token=" + $('#jwttoken').val() + "&language=" + $('#language').val() + "&subscribed=" + $('#subscribed').val() + "&lastLoginAt=" + $('#lastLoginAt').val();
}