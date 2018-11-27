package org.softuni.eventures.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.eventures.domain.models.binding.EventOrderBindingModel;
import org.softuni.eventures.domain.models.view.AllOrdersViewModel;
import org.softuni.eventures.service.EventService;
import org.softuni.eventures.service.OrderService;
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
@RequestMapping("/orders")
public class OrderController extends BaseController {

  private final EventService eventService;

  private final  OrderService orderService;

  private final ModelMapper modelMapper;

    public OrderController(EventService eventService, OrderService orderService, ModelMapper modelMapper) {
        this.eventService = eventService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public ModelAndView allOrders(ModelAndView modelAndView){
        Set<AllOrdersViewModel> allOrdersViewModel = this
                .orderService
                .getAll()
                .stream()
                .map(x -> this.modelMapper.map(x, AllOrdersViewModel.class))
                .collect(Collectors.toSet());

        modelAndView.addObject("allOrders", allOrdersViewModel);

        return this.view("orders-all", modelAndView);
    }


}
