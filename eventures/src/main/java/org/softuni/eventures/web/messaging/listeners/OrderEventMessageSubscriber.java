package org.softuni.eventures.web.messaging.listeners;

import org.softuni.eventures.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

public class OrderEventMessageSubscriber {
    private final EventService eventService;

    @Autowired
    public OrderEventMessageSubscriber(EventService eventService) {
        this.eventService = eventService;
    }

    @JmsListener(destination = "order-event-listener")
    public void onOrder(Message message) throws JMSException {
        if(message instanceof MapMessage) {
            MapMessage mappedMessage = ((MapMessage) message);

            String eventId = (String) mappedMessage.getObject("eventId");
            String username = (String) mappedMessage.getObject("username");
            Integer tickets = (Integer) mappedMessage.getObject("tickets");

            this.eventService.orderEvent(eventId, username, tickets);
        }
    }

}
