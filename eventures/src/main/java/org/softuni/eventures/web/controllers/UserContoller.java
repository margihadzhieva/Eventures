package org.softuni.eventures.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.eventures.domain.models.binding.UserLoginBindingModel;
import org.softuni.eventures.domain.models.binding.UserRegisterBindingModel;
import org.softuni.eventures.domain.models.service.UserServiceModel;
import org.softuni.eventures.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserContoller extends BaseController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserContoller(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public ModelAndView register(){
        return this.view("register");

    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel userRegisterBindingModel){
        if(!userRegisterBindingModel.getPassword().
                equals(userRegisterBindingModel.getConfirmPassword())){
            return this.view("register");
        }
        this.userService.createUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return this.redirect("/login");

    }

    @GetMapping("/login")
    public ModelAndView login(){
        return this.view("login");
    }



}
