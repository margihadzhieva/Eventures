package org.softuni.eventures.service;

import org.softuni.eventures.domain.models.service.EventServiceModel;
import org.softuni.eventures.domain.models.service.MyEventsServiceModel;

import java.util.Set;

public interface EventService {

    boolean createEvent(EventServiceModel eventServiceModel);

    Set<EventServiceModel> getAll();

    public void orderEvent(String eventId, String username, Integer tickets);


    Set<MyEventsServiceModel> myEvents(String currentUser);
}
