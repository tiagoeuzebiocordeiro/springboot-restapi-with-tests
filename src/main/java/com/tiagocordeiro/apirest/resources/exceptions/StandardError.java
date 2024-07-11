package com.tiagocordeiro.apirest.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class StandardError {

    private Instant timestamp;
    private Integer code;
    private String error;
    private String path;
}
