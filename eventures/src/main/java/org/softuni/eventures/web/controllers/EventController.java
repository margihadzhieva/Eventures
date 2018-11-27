package org.softuni.eventures.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.eventures.domain.entities.Event;
import org.softuni.eventures.domain.models.binding.EventCreateBindingModel;
import org.softuni.eventures.domain.models.binding.EventOrderBindingModel;
import org.softuni.eventures.domain.models.service.EventServiceModel;
import org.softuni.eventures.domain.models.view.AllEventsViewModel;
import org.softuni.eventures.domain.models.view.MyEventsViewModel;
import org.softuni.eventures.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/events")
public class EventController extends BaseController {
    private final EventService eventService;
    private final ModelMapper modelMapper;

    @Autowired
    public EventController(EventService eventService, ModelMapper modelMapper) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/create")
    public ModelAndView eventCreate(){
        return this.view("event-create");

    }

    @PostMapping("/create")
    public ModelAndView createEventConfirm(@ModelAttribute EventCreateBindingModel eventCreateBindingModel){

        boolean result =    this.eventService.createEvent(this.modelMapper.map(eventCreateBindingModel, EventServiceModel.class));

        if(!result) {
            throw new IllegalArgumentException("asd");
        }

        return this.redirect("all");
    }

    @GetMapping("/all")
    public ModelAndView allEvents(ModelAndView modelAndView) {
        Set<AllEventsViewModel> allEventsViewModel = this
                .eventService
                .getAll()
                .stream()
                .map(x -> this.modelMapper.map(x, AllEventsViewModel.class))
                .collect(Collectors.toSet());


        modelAndView.addObject("allEvents", allEventsViewModel);
        return  this.view("events-all", modelAndView);
    }

    @PostMapping("/order")
    public ModelAndView order(@ModelAttribute EventOrderBindingModel eventOrderBindingModel, Principal principal){
        boolean result = this.eventService
                .orderEvent(eventOrderBindingModel.getTickets(),
                        eventOrderBindingModel.getEventId(), principal.getName());

     if(!result) {
        throw new IllegalArgumentException("asd");
    }

        return this.redirect("all");
}

    @GetMapping("/my")
    public ModelAndView myEvents(Principal principal, ModelAndView modelAndView) {
        Set<MyEventsViewModel> myEventsViewModel = this
                .eventService
                .myEvents(principal.getName())
                .stream()
                .map(x -> this.modelMapper.map(x, MyEventsViewModel.class))
                .collect(Collectors.toSet());
        modelAndView.addObject("myEvents", myEventsViewModel);

        return this.view("events-my", modelAndView);
    }

    }



