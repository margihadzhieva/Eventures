package org.softuni.eventures.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseContoller {
    public ModelAndView index(){
        return this.view("index");
    }

}
