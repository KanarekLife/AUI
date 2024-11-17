package com.nieradko.games_api.models;

import lombok.Value;

@Value
public class UpdateGameRequest {
    String name;
    int minimalPlayerAge;
}
