package io.pivotal.demo.springperson.controller;

import io.pivotal.demo.springperson.domain.FlightResponse;
import io.pivotal.demo.springperson.domain.FlightResponses;
import io.pivotal.demo.springperson.domain.FlightWrapper;
import io.pivotal.demo.springperson.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/flights")
public class FlightController {

	private FlightRepository repository;

	@Autowired
    public FlightController(FlightRepository repository)
	{
		this.repository = repository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<FlightResponses> flights()
	{
		return repository.findAll();
	}

	@RequestMapping(method = RequestMethod.PUT, headers="content-type=application/json")
    public void add(@RequestBody @Valid FlightWrapper flightObject) {
        repository.save(flightObject.getFlightRequestWrapper().toString(), flightObject.getFlightResponses());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void update(@RequestBody @Valid FlightWrapper flightObject) {
        repository.save(flightObject.getFlightRequestWrapper().toString(), flightObject.getFlightResponses());
    }

    @RequestMapping(value = "/{flightKey}", method = RequestMethod.GET)
    public FlightResponses getById(@PathVariable String flightKey) {
        return repository.findOne(flightKey);
    }

    @RequestMapping(value = "/{flightKey}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable String flightKey) {
        repository.delete(flightKey);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll() {
        repository.deleteAll();
    }
}