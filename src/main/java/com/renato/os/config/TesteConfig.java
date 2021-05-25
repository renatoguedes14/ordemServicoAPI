package com.renato.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.renato.os.service.DbService;

@Configuration
@Profile("test")
public class TesteConfig {
	
	@Autowired
	private DbService dbService;

	@Bean
	public void instanciaDb() {
		this.dbService.instanciaDb();
	}

}
