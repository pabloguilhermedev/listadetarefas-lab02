package com.todolist.mock;

import com.todolist.controller.dto.request.TarefaRequestDTO;
import com.todolist.entity.TarefaEntity;
import com.todolist.entity.enums.PrioridadeTarefaEnum;
import com.todolist.entity.enums.StatusTarefaEnum;
import com.todolist.entity.enums.TipoTarefaEnum;

import java.time.LocalDateTime;

public class MockFactory {

    public static TarefaRequestDTO tarefaRequestDTOMockFactory() {

        return new TarefaRequestDTO()
                .setTitulo("Test Title")
                .setDescricao("Test Description")
                .setDataCriacao(LocalDateTime.now())
                .setDataConclusao(LocalDateTime.now())
                .setStatus("PREVISTA")
                .setTipoTarefa(TipoTarefaEnum.DATA)
                .setPrioridadeTarefaEnum(PrioridadeTarefaEnum.MEDIA);
    }

    public static TarefaRequestDTO tarefaRequestDTOEditMockFactory() {

        return new TarefaRequestDTO()
                .setTitulo("Updated Title")
                .setDescricao("Test Description")
                .setDataCriacao(LocalDateTime.now())
                .setDataConclusao(LocalDateTime.now().plusDays(1))
                .setStatus("PREVISTA")
                .setTipoTarefa(TipoTarefaEnum.DATA)
                .setPrioridadeTarefaEnum(PrioridadeTarefaEnum.MEDIA);
    }

    public static TarefaEntity tarefaEntityMockFactory() {

        final var tarefaEntity = new TarefaEntity();

        tarefaEntity.setId(1L);
        tarefaEntity.setTitulo("Test Title");
        tarefaEntity.setDescricao("Test Description");
        tarefaEntity.setDataCriacao(LocalDateTime.now());
        tarefaEntity.setDataConclusao(LocalDateTime.now().minusDays(1));
        tarefaEntity.setPrioridade(PrioridadeTarefaEnum.ALTA.name());
        tarefaEntity.setTipoTarefa(TipoTarefaEnum.DATA.name());
        tarefaEntity.setStatus(StatusTarefaEnum.PREVISTA.name());

        return tarefaEntity;
    }
}
