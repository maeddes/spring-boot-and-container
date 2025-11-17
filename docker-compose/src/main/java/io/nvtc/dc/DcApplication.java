package io.nvtc.dc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DcApplication {

	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DcApplication.class);
        //app.addListeners(new ConfigReportPrinter());
        app.run(args);
	}

}
