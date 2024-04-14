package com.xandra.eventscalendar.events;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class EventService {

	@Autowired
	private EventRepository repo;
	
	@Autowired
	private ModelMapper mapper;

	public Event createEvent(CreateEventDTO data) {

		Event newEvent = mapper.map(data, Event.class);
		
		return this.repo.save(newEvent);
	}

	public List<Event> getAll() {
		return this.repo.findAll();
	}

	public Optional<Event> findById(Long id) {
		return this.repo.findById(id);
	}

	public Optional<Event> updateById(@Valid UpdateEventDTO data, Long id) {
		Optional<Event> maybeEvent = this.findById(id);
		
		if (maybeEvent.isEmpty()) {
			return maybeEvent;
		}
		
		Event foundEvent = maybeEvent.get();
		
		mapper.map(data, foundEvent);

		Event updatedEvent = this.repo.save(foundEvent);
		return Optional.of(updatedEvent);
	}

	public boolean deleteEventById(Long id) {
		Optional<Event> maybeEvent = this.repo.findById(id);
		if (maybeEvent.isEmpty()) {
			return false;
		}
		this.repo.delete(maybeEvent.get());
		return true;
	}
}
