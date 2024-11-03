package com.cineflicks.userservice;

import com.cineflicks.userservice.adapter.spi.persistence.entity.RoleEntity;
import com.cineflicks.userservice.adapter.spi.persistence.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaAuditing
@EnableAsync
@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository){
		return args -> {
			if ( roleRepository.findByName("USER").isEmpty()) {
                roleRepository.save(RoleEntity.builder().name("USER").build());
			}
		};
	}

}