package com.todolist.controller;

import com.todolist.controller.dto.request.TarefaRequestDTO;
import com.todolist.controller.dto.response.TarefaResponseDTO;
import com.todolist.entity.TarefaEntity;
import com.todolist.service.ListaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tarefas")
@RestController
public class TarefaController {

    private final ListaServiceImpl listaServiceImpl;

    public TarefaController(ListaServiceImpl listaServiceImpl) {
        this.listaServiceImpl = listaServiceImpl;
    }


    @GetMapping()
    @Operation(summary = "Lista todas as tarefas da lista")
    public ResponseEntity<TarefaResponseDTO> getListaTarefas() {

        final var response = listaServiceImpl.getListaTarefas();

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    @Operation(summary = "Criar uma lista de tarefas")
    public ResponseEntity<TarefaEntity> criarListaTarefas(@RequestBody @Valid TarefaRequestDTO request) {

        final var tarefa = listaServiceImpl.criarListaTarefas(request);

        return ResponseEntity.ok(tarefa);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar uma lista de tarefas")
    public ResponseEntity<TarefaEntity> editarListaTarefas(@RequestBody TarefaRequestDTO request,
                                                           @PathVariable Long id) {

        final var tarefa = listaServiceImpl.editarListaTarefas(request, id);

        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma tarefa especifica da lista de tarefas ou marcar como concluída")
    public ResponseEntity<String> deletarListaTarefas(@PathVariable Long id) {

        listaServiceImpl.deletarListaTarefas(id);

        return ResponseEntity.ok("Tarefa deletada com sucesso");
    }
}
