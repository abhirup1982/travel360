package com.easy.travel.registration.controller;

import java.util.List;

import com.easy.travel.registration.model.Notification;
import com.easy.travel.registration.service.TravelNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.easy.travel.registration.model.RegistrationDataModel;
import com.easy.travel.registration.service.RegistrationService;
import com.easy.travel.registration.validation.UserValidator;

@RestController
public class RegistrationRestExternalAPIController {

	private static final Logger LOG =
	LoggerFactory.getLogger(RegistrationRestExternalAPIController.class);

	@Autowired
	private TravelNotificationService travelNotificationServiceObj;

	@Autowired
	public RegistrationRestExternalAPIController(final RegistrationService registerService) {
		this.registerService = registerService;
	}

	private final RegistrationService registerService;

	@RequestMapping(path = "/getAll", method = RequestMethod.GET)
	public List<RegistrationDataModel> listEmployees() {
		List<RegistrationDataModel> lowFareSearchs = registerService.getList();
		return lowFareSearchs;
	}

	@RequestMapping(value = "/available", method = RequestMethod.POST)
	public boolean getDetailsByuserName(String username) {
		RegistrationDataModel model = registerService.getModelByUserName(username);
		return model == null;
	}

	@RequestMapping(value = "/fetch/notifications", method = RequestMethod.GET)
	public Notification getUserNotification(@RequestParam("username") String username) {
		RegistrationDataModel model = registerService.getModelByUserName(username);
		Notification userNotificationObj = travelNotificationServiceObj.randomNotification(model);
		return userNotificationObj;
	}


}
