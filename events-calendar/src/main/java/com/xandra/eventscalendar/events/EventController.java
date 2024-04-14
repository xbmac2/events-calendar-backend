package com.xandra.eventscalendar.events;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xandra.eventscalendar.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@PostMapping
	public ResponseEntity<Event> createEvent(@Valid @RequestBody CreateEventDTO data) {
		Event createdEvent = this.eventService.createEvent(data);
		return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Event>> getAllEvents() {
		List<Event> allEvents = this.eventService.getAll();
		return new ResponseEntity<>(allEvents, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable Long id) throws NotFoundException {
		Optional<Event> maybeEvent = this.eventService.findById(id);
		
		Event foundEvent = maybeEvent.orElseThrow(() -> new NotFoundException(Event.class, id));
		return new ResponseEntity<>(foundEvent, HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Event> updateEventById(@Valid @RequestBody UpdateEventDTO data, @PathVariable Long id) throws NotFoundException {
		Optional<Event> maybeUpdatedEvent = this.eventService.updateById(data, id);
		
		Event updatedEvent = maybeUpdatedEvent.orElseThrow(() -> new NotFoundException(Event.class, id));
		return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Event> deleteEventById(@PathVariable Long id) throws NotFoundException {
		boolean deleted = this.eventService.deleteEventById(id);
		if (!deleted) {
			throw new NotFoundException(Event.class, id);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
