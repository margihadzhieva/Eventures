package org.softuni.eventures.service;

import org.softuni.eventures.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface UserService extends UserDetailsService {
    boolean createUser(UserServiceModel userServiceModel);
        Set<UserServiceModel> getAll();




}
