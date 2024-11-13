package com.nieradko.games_api.models;

import lombok.Value;

@Value
public class CreateGameRequest {
    String name;
    int minimalPlayerAge;
}
