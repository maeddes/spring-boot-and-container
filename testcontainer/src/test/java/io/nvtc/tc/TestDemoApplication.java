package io.nvtc.tc;

import org.springframework.boot.SpringApplication;

public class TestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.from(TcApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}