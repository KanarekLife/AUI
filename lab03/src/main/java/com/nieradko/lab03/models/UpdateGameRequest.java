package com.nieradko.lab03.models;

import lombok.Value;

@Value
public class UpdateGameRequest {
    String name;
    int minimalPlayerAge;
}
