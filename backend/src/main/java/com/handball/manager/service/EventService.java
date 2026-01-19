package com.handball.manager.service;

import com.handball.manager.model.Event;
import com.handball.manager.model.MatchResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EventService {

    private final Map<Long, Event> events = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<>(events.values());
        eventList.sort(Comparator.comparing(Event::getDateTime).reversed());
        return eventList;
    }

    public Optional<Event> getEventById(Long id) {
        return Optional.ofNullable(events.get(id));
    }

    public Event createEvent(Event event) {
        validateEvent(event);
        
        Long id = idGenerator.getAndIncrement();
        event.setId(id);
        events.put(id, event);
        return event;
    }

    public Event updateEvent(Long id, Event event) {
        if (!events.containsKey(id)) {
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
        
        validateEvent(event);
        
        Event existing = events.get(id);
        event.setId(id);
        event.setMatchResult(existing.getMatchResult());
        events.put(id, event);
        return event;
    }

    public void deleteEvent(Long id) {
        events.remove(id);
    }

    public Event updateMatchResult(Long id, MatchResult result) {
        Event event = events.get(id);
        if (event == null) {
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
        
        if (event.getType() != Event.EventType.MATCH) {
            throw new IllegalArgumentException("Can only add results to matches");
        }
        
        event.setMatchResult(result);
        return event;
    }

    private void validateEvent(Event event) {
        if (event.getType() == Event.EventType.MATCH) {
            if (event.getOpponent() == null || event.getOpponent().trim().isEmpty()) {
                throw new IllegalArgumentException("Opponent is required for matches");
            }
        }
    }

    public void initSampleData() {
        Event training = new Event(null, Event.EventType.TRAINING, "Weekly Training", 
                "Sports Hall A", LocalDateTime.now().plusDays(2), 90, null, "Focus on defense");
        createEvent(training);
        
        Event match = new Event(null, Event.EventType.MATCH, "League Match", 
                "Home Arena", LocalDateTime.now().plusDays(5), 60, "City Rivals", "Important game");
        createEvent(match);
    }
}