package com.accountapi.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event {
    private String eventId;
    private Long from;
    private Long to;
    private BigDecimal value;
}
