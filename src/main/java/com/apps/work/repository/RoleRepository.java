package com.apps.work.repository;

import com.apps.work.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.name = :name")
    public Role getRoleByName(@Param("name") String name);

}