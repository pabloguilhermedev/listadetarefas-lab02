package com.todolist.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class TarefaDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("dataCriacao")
    private LocalDateTime dataCriacao;

    @JsonProperty("dataConclusao")
    private LocalDateTime dataConclusao;

    @JsonProperty("status")
    private String status;
}
