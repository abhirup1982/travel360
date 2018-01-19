package com.easy.travel.registration.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.easy.travel.registration.model.Notification;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easy.travel.registration.model.RegistrationDataModel;
import org.springframework.stereotype.Service;

@Service
public class TravelNotificationService {

    @Autowired
    private KieContainer kieContainer;

	Random random = new Random();
	DateTimeFormatter sdf = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	public static Notification genericNotification_1() {
		return Notification.buildNotification("Check out our airport lounge",
				"Fully stacked bar with liquors from all around the world");
	}

	public Notification genericNotification_2() {
		return Notification.buildNotification("Travel360 eatery now serving mexican food",
				"Get 5% discount on an espresso with every Taco order");
	}

	public Notification genericNotification_3() {
		return Notification.buildNotification("TIA Fashion",
				"Check out the TIA fashion apparels section at Travel360 airport outlets");
	}

	public Notification genericNotification_4() {
		return Notification.buildNotification("Samsung Christmas Bonanza",
				"Flat 10% off on all items on 24-26 Dec");
	}

	public Notification genericNotification_5() {
		return Notification.buildNotification("Travel360 Wellness spa",
				"Relax while you wait, let us pamper you while you wait at our airport Spa and Lounge");
	}

	public Notification petNotification_1() {
		return Notification.buildNotification("Pet Food",
				"Now get 50% discount on paw warmers and jumpers");
	}

	public Notification petNotification_2() {
		return Notification.buildNotification("Pet Fashion",
				"Now get 10% discount on all pet treats");
	}

	public Notification marriedManNotification_1() {
		return Notification.buildNotification("Exotic flowers",
				"Shower her with roses from Travel360 airport flower store");
	}

	public Notification marriedManNotification_2() {
		return Notification.buildNotification("Premium cigars",
				"Browse exclusive brands at Travel360 lounge store");
	}

	public Notification unmarriedWomanNotification_3() {
		return Notification.buildNotification("Flat 20% off",
				"On diamond jewellery at Travel360 outlets");
	}

	public Notification travellingWithKidsNotification_1() {
		return Notification.buildNotification("Baby Food and baby products",
				"Browse Travel360 airport stores");
	}

	public Notification travellingWithKidsNotification_2() {
		return Notification.buildNotification("Travel360 Toy Store",
				"20% off on all lego items");
	}

	public Notification travellingWithKidsNotification_3() {
		return Notification.buildNotification("Travel360 Laser Tag Arena",
				"Newly opened have fun while you wait for your flight");
	}

	public Notification seniorCitizenNotification_1() {
		return Notification.buildNotification("Travel360 Wellness",
				"Visit out airport spa for 5% off on all services, just show your boarding pass");
	}

	public Notification randomNotification(RegistrationDataModel model) {
		
		/*List<Notification> userNotificationList = new ArrayList<Notification>();

		if ("Yes".equalsIgnoreCase(model.getUserPets())) {
			userNotificationList.add(petNotification_1());
			userNotificationList.add(petNotification_2());
		}

		if ("Male".equals(model.getUserGender()) && "Yes".equalsIgnoreCase(model.getUserMarried())) {
			userNotificationList.add(marriedManNotification_1());
		}

		if ("Male".equals(model.getUserGender()) && "No".equalsIgnoreCase(model.getUserMarried())) {
			userNotificationList.add(marriedManNotification_2());
		}

		if ("Female".equals(model.getUserGender()) && "No".equals(model.getUserMarried())) {
			userNotificationList.add(unmarriedWomanNotification_3());
		}

		userNotificationList.add(genericNotification_1());
		userNotificationList.add(genericNotification_2());
		userNotificationList.add(genericNotification_3());
		userNotificationList.add(genericNotification_4());
		userNotificationList.add(genericNotification_5());

		if ("Yes".equalsIgnoreCase(model.getUserKids())) {
			userNotificationList.add(travellingWithKidsNotification_1());
		}

		if ("Yes".equalsIgnoreCase(model.getUserKids())) {
			userNotificationList.add(travellingWithKidsNotification_2());
		}

		if ("Yes".equals(model.getUserKids())) {
			userNotificationList.add(travellingWithKidsNotification_3());
		}

		LocalDate localDate = LocalDate.parse(model.getUserdob(), sdf);

		if (LocalDate.now().getYear() - localDate.getYear() > 60) {
			userNotificationList.add(seniorCitizenNotification_1());
		}

		return userNotificationList.get(random.nextInt(userNotificationList.size()));*/

        List<Notification> userNotificationList = getNotifications(model);
        System.out.println("************" + userNotificationList.size());
        return userNotificationList.get(0);
	}


	public List<Notification> getNotifications(RegistrationDataModel dataModel) {
		//get the stateful session
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(dataModel);
		kieSession.fireAllRules();
		kieSession.dispose();
		return dataModel.getNotifications();
	}
}
