package com.todolist.service;

import com.todolist.controller.dto.request.TarefaRequestDTO;
import com.todolist.controller.dto.response.TarefaDTO;
import com.todolist.controller.dto.response.TarefaResponseDTO;
import com.todolist.entity.TarefaEntity;
import com.todolist.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ListaService {

    private final TarefaRepository repository;

    public ListaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public TarefaResponseDTO getListaTarefas() {

        final var tarefas = repository.findAll();

        final var tarefasDTO = tarefas.stream()
                .map(tarefa -> new TarefaDTO()
                        .setId(tarefa.getId())
                        .setTitulo(tarefa.getTitulo())
                        .setDescricao(tarefa.getDescricao())
                        .setDataCriacao(tarefa.getDataCriacao())
                        .setDataConclusao(tarefa.getDataConclusao())
                        .setStatus(tarefa.getStatus()))
                .collect(Collectors.toList());

        return new TarefaResponseDTO()
                .setTarefas(tarefasDTO);
    }

    public TarefaEntity criarListaTarefas(TarefaRequestDTO request) {

        final var tarefa = new TarefaEntity(
                request.getTitulo(),
                request.getDescricao(),
                request.getDataCriacao(),
                null,
                request.getStatus());

        return repository.save(tarefa);
    }

    public TarefaEntity editarListaTarefas(TarefaRequestDTO request) {

        final var tarefa = new TarefaEntity(
                request.getTitulo(),
                request.getDescricao(),
                request.getDataCriacao(),
                null,
                request.getStatus());

        return repository.save(tarefa);
    }

    public void deletarListaTarefas(Long id) {

        final var tarefa = repository.findById(id)
                .orElse(new TarefaEntity());

        repository.delete(tarefa);
    }
}
