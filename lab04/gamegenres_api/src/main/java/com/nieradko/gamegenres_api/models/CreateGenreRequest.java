package com.nieradko.gamegenres_api.models;

import lombok.Value;

@Value
public class CreateGenreRequest {
    String name;
    boolean supportsMultiplayer;
}
