package br.com.cobertura.api.resource.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StandarError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String path;

}
