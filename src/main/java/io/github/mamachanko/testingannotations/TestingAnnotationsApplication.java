package io.github.mamachanko.testingannotations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;

@SpringBootApplication
public class TestingAnnotationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingAnnotationsApplication.class, args);
	}

}

@RestController
class Api {

	final private ResourceRepository resourcesRepository;

	Api(ResourceRepository resourcesRepository) {
		this.resourcesRepository = resourcesRepository;
	}

	@GetMapping("/api/resources")
	Object get() {
		return resourcesRepository.findAll();
	}

}

@Repository
class ResourceRepository {
	public List<Map<String, String>> findAll() {
		return asList(
				singletonMap("id", "1"),
				singletonMap("id", "2")
		);
	}
}