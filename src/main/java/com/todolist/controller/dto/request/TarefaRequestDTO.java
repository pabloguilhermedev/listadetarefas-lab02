package com.todolist.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.todolist.entity.enums.PrioridadeTarefaEnum;
import com.todolist.entity.enums.TipoTarefaEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TarefaRequestDTO {

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("dataCriacao")
    private LocalDateTime dataCriacao;

    @JsonProperty("data_conclusao")
    private LocalDateTime dataConclusao;

    @JsonProperty("status")
    private String status;

    @JsonProperty("tipo_tarefa")
    private TipoTarefaEnum tipoTarefa;

    @JsonProperty("prioridade")
    private PrioridadeTarefaEnum prioridadeTarefaEnum;
}
