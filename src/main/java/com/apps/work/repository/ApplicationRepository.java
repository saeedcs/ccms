package com.apps.work.repository;

import com.apps.work.model.Application;
import com.apps.work.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {

}