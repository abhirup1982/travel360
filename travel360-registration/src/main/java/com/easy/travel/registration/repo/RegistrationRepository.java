package com.easy.travel.registration.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easy.travel.registration.model.RegistrationDataModel;

public interface RegistrationRepository extends JpaRepository<RegistrationDataModel, String> {
}