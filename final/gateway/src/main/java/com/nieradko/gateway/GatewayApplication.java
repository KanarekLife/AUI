package com.nieradko.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {
	private final String GamesAPIUrl;
	private final String GameGenresAPIUrl;

    public GatewayApplication(@Value("${gateway.gamesapi}") String gamesAPIUrl, @Value("${gateway.gamegenresapi}") String gameGenresAPIUrl) {
        GamesAPIUrl = gamesAPIUrl;
        GameGenresAPIUrl = gameGenresAPIUrl;
    }

    public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("genres", route -> route.path("/genres/", "/genres/{uuid}/").uri(GameGenresAPIUrl))
				.route("games", route -> route.path("/games/", "/games/{uuid}/", "/genres/{uuid}/games/").uri(GamesAPIUrl))
				.build();

	}

}
