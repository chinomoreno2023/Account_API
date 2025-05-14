package com.accountapi.service.consumer;

import com.accountapi.model.event.Event;

public interface CreatedEventHandler {
    void handle(Event event, String messageId);
}
