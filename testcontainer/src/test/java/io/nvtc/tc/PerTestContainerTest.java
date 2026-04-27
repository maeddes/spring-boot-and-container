package io.nvtc.tc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

/*

Does not use the global testcontainer configuration, but creates a new one for each test class. 
This is useful if you want to have different configurations for different test classes.

*/

@SpringBootTest
@Testcontainers
public class PerTestContainerTest {

	@Autowired
    ItemRepository repository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres =
            new PostgreSQLContainer("postgres:16-alpine");

    @Test
	void testCreate(){

		repository.save(new Item("bananas",10));
		assertTrue(repository.findAll().size() > 0);
		
	}
    
}
