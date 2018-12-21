package hu.roszpapad.konyvklub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KonyvklubApplication {

	public static void main(String[] args) {
		SpringApplication.run(KonyvklubApplication.class, args);
	}
}
