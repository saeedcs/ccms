package com.apps.work.repository;

import com.apps.work.model.Application;
import com.apps.work.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}