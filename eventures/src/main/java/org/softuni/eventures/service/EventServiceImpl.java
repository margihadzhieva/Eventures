package org.softuni.eventures.service;

import org.modelmapper.ModelMapper;
import org.softuni.eventures.domain.entities.Event;
import org.softuni.eventures.domain.entities.User;
import org.softuni.eventures.domain.models.service.EventServiceModel;
import org.softuni.eventures.domain.models.service.MyEventsServiceModel;
import org.softuni.eventures.domain.models.service.OrderServiceModel;
import org.softuni.eventures.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final ModelMapper modelMapper;

    private final UserService userService;

    private final OrderService orderService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper, UserService userService, OrderService orderService) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.orderService = orderService;
    }


    private void placeOrder(Event event, User customer, Integer tickets) {
        OrderServiceModel orderServiceModel = new OrderServiceModel();

        orderServiceModel.setOrderedOn(LocalDateTime.now());
        orderServiceModel.setEvent(event);
        orderServiceModel.setCustomer(customer);
        orderServiceModel.setTicketsCount(tickets);

        this.orderService.createOrder(orderServiceModel);
    }


    @Override
    public boolean createEvent(EventServiceModel eventServiceModel) {
        Event eventEntity = this.modelMapper.map(eventServiceModel, Event.class);

        try {
            this.eventRepository.save(eventEntity);
        } catch (Exception ignored) {
            return false;
        }

        return true;
    }

    @Override
    public Set<EventServiceModel> getAll() {
        return this.eventRepository
                .findAll()
                .stream()
                .map(x -> this.modelMapper.map(x, EventServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void orderEvent(String eventId, String username, Integer tickets) {
        Event event = this.eventRepository
                .findById(eventId)
                .orElse(null);

        User customer = (User) this
                .userService
                .loadUserByUsername(username);

        if(event == null || customer == null) {
            throw new IllegalArgumentException("Order Event or Customer cannot be null!");
        }

        if(event.extractRemainingTickets() < tickets) {
            throw new IllegalArgumentException("Not enough tickets.");
        }

        event
                .setSoldTickets(event.getSoldTickets()
                        + tickets);

        this.placeOrder(event, customer, tickets);
    }





    @Override
    public Set<MyEventsServiceModel> myEvents(String currentUser) {
        String userId = ((User)this.userService
                .loadUserByUsername(currentUser))
                .getId();

        Set<OrderServiceModel> allOrdersFromUser = this.orderService.getAllByUserId(userId);

        Set<MyEventsServiceModel> myEventsServiceModels = new HashSet<>();

        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        for (OrderServiceModel orderServiceModel : allOrdersFromUser) {
            MyEventsServiceModel resultModel = this
                    .modelMapper.map(orderServiceModel.getEvent(), MyEventsServiceModel.class);

            resultModel.setTickets(orderServiceModel.getTicketsCount());

            myEventsServiceModels.add(resultModel);
        }

        return myEventsServiceModels;
    }
}

