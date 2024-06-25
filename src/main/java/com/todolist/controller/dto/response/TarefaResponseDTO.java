package com.todolist.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TarefaResponseDTO {

    @JsonProperty("tasks")
    private List<TarefaDTO> tarefas;
}
