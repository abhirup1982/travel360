package com.easy.travel.registration.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.easy.travel.registration.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.easy.travel.registration.model.RegistrationDataModel;
import com.easy.travel.registration.service.RegistrationService;
import com.easy.travel.registration.validation.UserValidator;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RegistrationController {

	private final RegistrationService registrationObj;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${travel360.login.url}")
	private String login_url;

	@Value("${travel360.search.flight.url}")
	private String flight_url;

	@Value("${travel360.authentication.url}")
	private String authentication_url;

    @Value("${travel360.notification.url}")
    private String notification_url;

	public static final String HEADER_STRING = "Authorization";

	@Autowired
	public RegistrationController(final RegistrationService registrationObj) {
		this.registrationObj = registrationObj;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, @RequestParam("language") String language) {
		RegistrationDataModel dataModel = new RegistrationDataModel();
		dataModel.setLang(language);
		model.addAttribute("edit", "false");
		model.addAttribute("userForm", dataModel);
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public RedirectView welcome(@ModelAttribute("userForm") @Valid RegistrationDataModel userForm, BindingResult result) throws JsonProcessingException {
		System.out.println(userForm);
		userValidator.validate(userForm, result);
		registrationObj.save(userForm);
		notifyLoginService(userForm);
		String token = getToken(userForm.getUserName());
		return new RedirectView(flight_url + "/search/home?token="+token+"&newUser=true&subscribed="+"Yes".equalsIgnoreCase(userForm.getNotified()) + "&language=" + userForm.getLang());

		/*if (result.hasErrors()) {
			return new ModelAndView("home");
		} else {
			registrationObj.save(request);
			notifyLoginService(request);
			model.addAttribute("userForm", new RegistrationDataModel());
			model.addAttribute("fullName", userForm.getFullName());
			model.addAttribute("userName", userForm.getUserName());
			return new ModelAndView("welcome");
		}*/
	}

	private void notifyLoginService(RegistrationDataModel request) throws JsonProcessingException {
		User user = new User();
		user.setUsername(request.getUserName().toLowerCase());
		user.setPassword(request.getUserPassword());
		user.setLastLoginAt(Timestamp.valueOf(LocalDateTime.now()));
		user.setNotified("Yes".equalsIgnoreCase(request.getNotified()));
		restTemplate.postForObject(login_url + "/save/user", user, String.class);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public RedirectView editsave(@RequestParam(value = "jwt_token") String jwt_token, @ModelAttribute("userForm") @Valid RegistrationDataModel userForm, BindingResult result, HttpServletRequest req) {

		String userName = ValidateToken.validate(jwt_token);

		userForm.setJwt_token(jwt_token);

		registrationObj.save(userForm);
		return new RedirectView(flight_url + "/search/home?token="+jwt_token+"&newUser=false&subscribed="+"Yes".equalsIgnoreCase(userForm.getNotified()) + "&language=" + userForm.getLang());
	}


	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editpage(@RequestParam("token") String jwt_token, @RequestParam("language") String language) {
		ModelAndView modelAndView = new ModelAndView("home");
		String userName = ValidateToken.validate(jwt_token);
		final RegistrationDataModel listByuserObj = registrationObj.getModelByUserName(userName);
		listByuserObj.setLang(language);
		listByuserObj.setJwt_token(jwt_token);
		modelAndView.addObject("edit", "true");
		modelAndView.addObject("userForm", listByuserObj);
		modelAndView.addObject("userViewForm", listByuserObj);
		return modelAndView;

	}

	@ModelAttribute("command")
	public RegistrationDataModel formBackingObject() {
		return new RegistrationDataModel();
	}

	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public String logout() {
		return "redirect:/home";
	}

	@RequestMapping(path = "/registration/logout", method = RequestMethod.GET)
	public RedirectView navigateToLogin() throws ServletException, IOException {
		return new RedirectView(login_url + "/login?logout");
	}

	private String getToken(String userName) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity entity = new HttpEntity<>(headers);
		HttpEntity<String> response = restTemplate.exchange(authentication_url + "/auth/getToken/" + userName, HttpMethod.GET, entity, String.class);
		HttpHeaders rspheaders = response.getHeaders();
		return rspheaders.get(HEADER_STRING).get(0);
	}
}
