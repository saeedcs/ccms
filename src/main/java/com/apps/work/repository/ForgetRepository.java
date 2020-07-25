package com.apps.work.repository;

import com.apps.work.model.ForgetPassword;
import com.apps.work.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgetRepository extends JpaRepository<ForgetPassword, Long> {
    public ForgetPassword findByCode(String code);
}