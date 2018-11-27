package org.softuni.eventures.domain.models.binding;

import org.softuni.eventures.domain.entities.Event;
import org.softuni.eventures.domain.entities.User;

import java.time.LocalDateTime;

public class EventOrderBindingModel {
    private String eventId;

    private Integer tickets;

    public EventOrderBindingModel() {
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }


    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }
}

