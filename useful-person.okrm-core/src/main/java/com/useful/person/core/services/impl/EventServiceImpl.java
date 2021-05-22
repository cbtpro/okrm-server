package com.useful.person.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.useful.person.core.domain.Event;
import com.useful.person.core.exception.EventNotExistException;
import com.useful.person.core.repository.EventRepository;
import com.useful.person.core.services.BasicService;
import com.useful.person.core.services.EventService;

/**
 * 
 * @author peter
 *
 */
@Service("eventService")
public class EventServiceImpl implements EventService, BasicService<Event> {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event findByUuid(String uuid) {
        return eventRepository.findById(uuid).orElseThrow(() -> new EventNotExistException(uuid));
    }

    @Override
    public Event saveOne(Event entity) {
        return eventRepository.save(entity);
    }

    @Override
    public List<Event> saveAll(List<Event> entities) {
        return eventRepository.saveAll(entities);
    }

    @Override
    public void deleteOne(Event entity) {
        eventRepository.delete(entity);
    }

    @Override
    public void deleteByUuid(String uuid) {
        eventRepository.deleteById(uuid);
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public void deleteAll() {
        eventRepository.deleteAll();
    }

}
