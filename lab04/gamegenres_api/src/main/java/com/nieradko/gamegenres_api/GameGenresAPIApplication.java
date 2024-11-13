package com.nieradko.gamegenres_api;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GameGenresAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameGenresAPIApplication.class, args);
	}

	@Bean
	@Qualifier("gamesAPI")
	public RestTemplate getGamesAPIRestTemplate(@Value("${gateway.gamesapi}") String gamesAPIUrl) {
		return new RestTemplateBuilder()
				.rootUri(gamesAPIUrl)
				.build();
	}
}
