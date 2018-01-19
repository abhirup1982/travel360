package io.pivotal.demo.springperson.repository;

import io.pivotal.demo.springperson.domain.GeoLocation;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Set;

@NoRepositoryBean
public class GeoLocationRepository {

	public static final String GEO_LOCATION_KEY = "geo";

	private final HashOperations<String, String, GeoLocation> hashOps;

	public GeoLocationRepository(RedisTemplate<String, GeoLocation> redisTemplate) {
        this.hashOps = redisTemplate.opsForHash();
	}

	public long count() {
		return hashOps.keys(GEO_LOCATION_KEY).size();
	}

	public void delete(String key) {
		hashOps.delete(GEO_LOCATION_KEY, key);
	}

	public void deleteAll() {
		Set<String> flights = hashOps.keys(GEO_LOCATION_KEY);
		for (String flight : flights) {
			delete(flight);
		}

	}

	public boolean exists(String flightKey) {
		return hashOps.hasKey(GEO_LOCATION_KEY,  flightKey);
	}

	public List<GeoLocation> findAll() {
		return hashOps.values(GEO_LOCATION_KEY);
	}

	public GeoLocation findOne(String flightKey) {
		return hashOps.get(GEO_LOCATION_KEY, flightKey);
	}

	public void save(String flightKey, GeoLocation flightResponses) {
		hashOps.put(GEO_LOCATION_KEY, flightKey, flightResponses);
	}
}