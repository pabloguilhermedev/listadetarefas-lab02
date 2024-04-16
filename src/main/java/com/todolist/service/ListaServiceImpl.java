package com.todolist.service;

import com.todolist.controller.dto.request.TarefaRequestDTO;
import com.todolist.controller.dto.response.TarefaDTO;
import com.todolist.controller.dto.response.TarefaResponseDTO;
import com.todolist.entity.TarefaEntity;
import com.todolist.entity.enums.StatusTarefaEnum;
import com.todolist.entity.enums.TipoTarefaEnum;
import com.todolist.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class ListaServiceImpl {

    private final TarefaRepository repository;

    public ListaServiceImpl(TarefaRepository repository) {
        this.repository = repository;
    }

    public TarefaResponseDTO getListaTarefas() {
        var tarefas = repository.findAll();
        var tarefasDTO = tarefas.stream()
                .map(this::convertToTarefaDTO)
                .collect(Collectors.toList());

        return new TarefaResponseDTO().setTarefas(tarefasDTO);
    }

    private TarefaDTO convertToTarefaDTO(TarefaEntity tarefa) {
        return new TarefaDTO()
                .setId(tarefa.getId())
                .setTitulo(tarefa.getTitulo())
                .setDescricao(tarefa.getDescricao())
                .setDataCriacao(tarefa.getDataCriacao())
                .setDataConclusao(tarefa.getDataConclusao())
                .setPrioridadeTarefa(tarefa.getPrioridade())
                .setTipoTarefa(tarefa.getTipoTarefa())
                .setStatus(calcularStatus(tarefa));
    }

    public TarefaEntity criarListaTarefas(TarefaRequestDTO request) {

        validarDataConclusao(request);

        final var tarefa = new TarefaEntity(
                request.getTitulo(),
                request.getDescricao(),
                request.getDataCriacao(),
                request.getDataConclusao(),
                request.getStatus(),
                request.getTipoTarefa().name(),
                request.getPrioridadeTarefaEnum().name()
        );

        return repository.save(tarefa);
    }

    public TarefaEntity editarListaTarefas(TarefaRequestDTO request) {

        validarDataConclusao(request);

        final var tarefa = new TarefaEntity(
                request.getTitulo(),
                request.getDescricao(),
                request.getDataCriacao(),
                request.getDataConclusao(),
                request.getStatus(),
                request.getTipoTarefa().name(),
                request.getPrioridadeTarefaEnum().name()
        );

        return repository.save(tarefa);
    }

    private void validarDataConclusao(TarefaRequestDTO request) {
        if (request.getTipoTarefa().equals(TipoTarefaEnum.DATA) &&
                (request.getDataConclusao() == null || request.getDataConclusao().isBefore(LocalDateTime.now()))) {
            throw new IllegalArgumentException("Para tarefas do tipo 'Data', a data de conclusão deve ser igual ou superior à data atual.");
        }
    }


    public void deletarListaTarefas(Long id) {

        final var tarefa = repository.findById(id)
                .orElse(new TarefaEntity());

        repository.delete(tarefa);
    }

    private String calcularStatus(TarefaEntity tarefa) {
        if (StatusTarefaEnum.CONCLUIDO.name().equals(tarefa.getStatus())) {
            return StatusTarefaEnum.CONCLUIDO.name();
        }

        if (tarefa.getDataConclusao() != null) {
            final var diasDeAtraso = LocalDateTime.now().until(tarefa.getDataConclusao(), ChronoUnit.DAYS);
            return diasDeAtraso < 0 ? -diasDeAtraso + " dias de atraso" : StatusTarefaEnum.PREVISTA.name();
        }

        return StatusTarefaEnum.PREVISTA.name();
    }
}
