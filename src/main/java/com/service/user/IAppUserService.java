package com.service.user;

import com.model.user.AppUser;
import com.service.IService;

public interface IAppUserService extends IService<AppUser> {
    AppUser findByUsername(String username);
}
