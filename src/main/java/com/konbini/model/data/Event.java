package com.konbini.model.data;

import lombok.Getter;
import lombok.Setter;

/**
 * FullCalendar用Event Object
 * @see https://fullcalendar.io/docs/event_data/Event_Object/
 */
@Getter
@Setter
public class Event {
	
	
    private String title;
    private String start;
}
