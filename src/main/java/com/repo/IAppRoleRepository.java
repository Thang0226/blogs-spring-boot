package com.repo;

import com.model.user.AppRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRoleRepository extends CrudRepository<AppRole, Long> {
}
