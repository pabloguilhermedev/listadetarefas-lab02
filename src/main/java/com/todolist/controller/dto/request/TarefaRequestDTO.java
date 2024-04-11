package com.todolist.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class TarefaRequestDTO {

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("dataCriacao")
    private LocalDateTime dataCriacao;

    @JsonProperty("status")
    private String status;
}
