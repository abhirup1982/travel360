package com.easy.travel.registration.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.travel.registration.model.RegistrationDataModel;
import com.easy.travel.registration.repo.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServiceImpl.class);
    private final RegistrationRepository repository;

    @Autowired
    public RegistrationServiceImpl(final RegistrationRepository repository) {
        this.repository = repository;
    }

    @PersistenceContext
    private EntityManager em;
   
    @Transactional
    public RegistrationDataModel save(@NotNull @Valid final RegistrationDataModel lowFareSearch) {
        return repository.save(lowFareSearch);
    }

    public List<RegistrationDataModel> getList() {
        return repository.findAll();
    }

	public RegistrationDataModel getModelByUserName(String userName) {
		RegistrationDataModel model = em.find(RegistrationDataModel.class, userName);
		return model;
	}    

}