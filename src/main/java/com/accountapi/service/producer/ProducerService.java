package com.accountapi.service.producer;

import com.accountapi.model.event.Event;

public interface ProducerService {
    String createEvent(Event event);
}
