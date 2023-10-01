package io.github.sursodsousa.mscreditevaluator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaClient
public class MscreditevaluatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscreditevaluatorApplication.class, args);
	}

}
