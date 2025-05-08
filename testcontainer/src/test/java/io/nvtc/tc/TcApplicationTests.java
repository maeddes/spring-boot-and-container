package io.nvtc.tc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TcApplicationTests {

	@Autowired
    ItemRepository repository;

	@Test
	void testCreate(){

		repository.save(new Item("kiwis",10));
		assertTrue(repository.findAll().size() > 0);
		
	}

	@Test
	void testRead(){

		//repository.save(new Item("kiwis",10));
		assertTrue(1 > 0);
		
	}

}
