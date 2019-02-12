package io.github.mamachanko.testingannotations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ManuallyConfiguredMvcTest {

    @Test
    public void shouldReturnResources() throws Exception {
        ResourceRepository resourceRepository = mock(ResourceRepository.class);
        Api api = new Api(resourceRepository);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(api).build();

        given(resourceRepository.findAll()).willReturn(
                asList(singletonMap("id", "one"), singletonMap("id", "two"))
        );

        mockMvc.perform(get("/api/resources"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("one")))
                .andExpect(jsonPath("$[1].id", is("two")));
    }

}
