package io.pivotal.demo.springperson.repository;

import io.pivotal.demo.springperson.domain.FlightResponse;
import io.pivotal.demo.springperson.domain.FlightResponses;
import io.pivotal.demo.springperson.domain.FlightWrapper;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoRepositoryBean
public class FlightRepository {

	public static final String FLIGHTS_KEY = "flights";

	private final HashOperations<String, String, FlightResponses> hashOps;

	public FlightRepository(RedisTemplate<String, FlightResponses> redisTemplate) {
        this.hashOps = redisTemplate.opsForHash();
	}

	public long count() {
		return hashOps.keys(FLIGHTS_KEY).size();
	}

	public void delete(String key) {
		hashOps.delete(FLIGHTS_KEY, key);
	}

	public void deleteAll() {
		Set<String> flights = hashOps.keys(FLIGHTS_KEY);
		for (String flight : flights) {
			delete(flight);
		}

	}

	public boolean exists(String flightKey) {
		return hashOps.hasKey(FLIGHTS_KEY,  flightKey);
	}

	public List<FlightResponses> findAll() {
		return hashOps.values(FLIGHTS_KEY);
	}

	public FlightResponses findOne(String flightKey) {
		return hashOps.get(FLIGHTS_KEY, flightKey);
	}

	public void save(String flightKey, FlightResponses flightResponses) {
		hashOps.put(FLIGHTS_KEY, flightKey, flightResponses);
	}
}