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

    @JsonProperty("dataConclusao")
    private LocalDateTime dataConclusao;

    @JsonProperty("status")
    private String status = "Em andamento";

    @JsonProperty("tipo_tarefa")
    private TipoTarefaEnum tipoTarefa = TipoTarefaEnum.LIVRE;

    @JsonProperty("prioridade")
    private PrioridadeTarefaEnum prioridadeTarefaEnum;
}
