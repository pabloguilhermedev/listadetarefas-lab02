package com.todolist.controller;

import com.todolist.controller.dto.request.TarefaRequestDTO;
import com.todolist.controller.dto.response.TarefaResponseDTO;
import com.todolist.entity.TarefaEntity;
import com.todolist.service.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/tarefas")
@RestController
public class TarefaController {

    private final ListaService listaService;

    public TarefaController(ListaService listaService) {
        this.listaService = listaService;
    }


    @GetMapping()
    @Operation(summary = "Lista todas as tarefas da lista")
    public ResponseEntity<TarefaResponseDTO> getListaTarefas() {

        final var response = listaService.getListaTarefas();

        return ResponseEntity.ok(response);
    }

    @PostMapping()
    @Operation(summary = "Criar uma lista de tarefas")
    public ResponseEntity<TarefaEntity> criarListaTarefas(@RequestBody TarefaRequestDTO request) {

        final var tarefa = listaService.criarListaTarefas(request);

        return ResponseEntity.ok(tarefa);
    }

    @PutMapping()
    @Operation(summary = "Editar uma lista de tarefas")
    public ResponseEntity<TarefaEntity> editarListaTarefas(@RequestBody TarefaRequestDTO request) {

        final var tarefa = listaService.editarListaTarefas(request);

        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma tarefa especifica da lista de tarefas ou marcar como concluída")
    public ResponseEntity<String> deletarListaTarefas(@PathVariable Long id) {

        listaService.deletarListaTarefas(id);

        return ResponseEntity.ok("Tarefa deletada com sucesso");
    }
}
