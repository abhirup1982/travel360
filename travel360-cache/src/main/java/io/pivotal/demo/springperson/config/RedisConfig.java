package io.pivotal.demo.springperson.config;

import io.pivotal.demo.springperson.domain.FlightResponses;
import io.pivotal.demo.springperson.domain.GeoLocation;
import io.pivotal.demo.springperson.repository.FlightRepository;
import io.pivotal.demo.springperson.repository.GeoLocationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

	@Bean
    public FlightRepository flightRepository(RedisTemplate<String, FlightResponses> redisTemplate) {
		return new FlightRepository(redisTemplate);
	}

    @Bean
    public GeoLocationRepository sampleRepository(RedisTemplate<String, GeoLocation> redisTemplate) {
        return new GeoLocationRepository(redisTemplate);
    }

	@Bean
	public RedisTemplate<String, FlightResponses> flightRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, FlightResponses> template = new RedisTemplate();

		template.setConnectionFactory(redisConnectionFactory);

		/*RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		RedisSerializer<List> flightSerializer = new Jackson2JsonRedisSerializer<>(List.class);

		template.setKeySerializer(stringSerializer);
		template.setValueSerializer(flightSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setHashValueSerializer(flightSerializer);*/

		return template;
	}

    @Bean
    public RedisTemplate<String, GeoLocation> sampleRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, GeoLocation> template = new RedisTemplate();

        template.setConnectionFactory(redisConnectionFactory);

        /*RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        RedisSerializer<Requests> flightSerializer = new Jackson2JsonRedisSerializer<>(Requests.class);

        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(flightSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(flightSerializer);*/

        return template;
    }
}