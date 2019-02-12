package io.github.mamachanko.testingannotations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturnResources() {
        // Setup

        // Exercise
        // GET /api/resources
        ResponseEntity<List<Map<String, String>>> response = testRestTemplate.exchange(
                "/api/resources",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, String>>>() {}
        );

        // Assert
        // that response status code is 200/OK
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // that response body contains list of two resources with id and value
        assertThat(response.getBody()).containsExactlyInAnyOrder(
                singletonMap("id", "1"),
                singletonMap("id", "2")
        );
    }
}
