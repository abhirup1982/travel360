package com.easy.travel.registration.service;

import java.util.List;

import com.easy.travel.registration.model.RegistrationDataModel;

public interface RegistrationService {
	RegistrationDataModel save(RegistrationDataModel lowfareReq);

	List<RegistrationDataModel> getList();

	RegistrationDataModel getModelByUserName(String userName);
}