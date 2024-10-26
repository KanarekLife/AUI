package com.nieradko.lab03.models;

import lombok.Value;

@Value
public class CreateGenreRequest {
    String name;
    boolean supportsMultiplayer;
}
