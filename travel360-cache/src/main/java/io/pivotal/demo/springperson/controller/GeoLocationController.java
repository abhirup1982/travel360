package io.pivotal.demo.springperson.controller;

import io.pivotal.demo.springperson.domain.GeoLocation;
import io.pivotal.demo.springperson.repository.GeoLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/coordinates")
public class GeoLocationController {

    @Autowired
	private GeoLocationRepository repository;

    public GeoLocationController(GeoLocationRepository repository)
	{
		this.repository = repository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<GeoLocation> flights()
	{
		return repository.findAll();
	}

	@RequestMapping(path = "/{key}", method = RequestMethod.PUT, headers="content-type=application/json")
    public void add(@PathVariable("key") String key, @RequestBody GeoLocation geoLocation) {
        repository.save(key, geoLocation);
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public GeoLocation getById(@PathVariable("key") String flightKey) {
        return repository.findOne(flightKey);
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("key") String flightKey) {
        repository.delete(flightKey);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAll() {
        repository.deleteAll();
    }
}