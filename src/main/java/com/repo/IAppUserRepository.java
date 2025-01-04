package com.repo;

import com.model.user.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
