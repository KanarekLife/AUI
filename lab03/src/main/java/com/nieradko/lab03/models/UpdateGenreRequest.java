package com.nieradko.lab03.models;

import lombok.Value;

@Value
public class UpdateGenreRequest {
    String name;
    boolean supportsMultiplayer;
}
