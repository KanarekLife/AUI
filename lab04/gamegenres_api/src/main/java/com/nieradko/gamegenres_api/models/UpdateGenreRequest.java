package com.nieradko.gamegenres_api.models;

import lombok.Value;

@Value
public class UpdateGenreRequest {
    String name;
    boolean supportsMultiplayer;
}
