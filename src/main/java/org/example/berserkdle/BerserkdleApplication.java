package org.example.berserkdle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BerserkdleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BerserkdleApplication.class, args);
    }

}
