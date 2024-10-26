package com.nieradko.lab03.models;

import lombok.Value;

@Value
public class CreateGameRequest {
    String name;
    int minimalPlayerAge;
}
