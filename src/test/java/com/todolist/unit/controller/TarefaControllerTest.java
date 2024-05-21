package com.todolist.unit.controller;

import com.todolist.controller.TarefaController;
import com.todolist.controller.dto.request.TarefaRequestDTO;
import com.todolist.controller.dto.response.TarefaResponseDTO;
import com.todolist.entity.TarefaEntity;
import com.todolist.entity.enums.PrioridadeTarefaEnum;
import com.todolist.entity.enums.TipoTarefaEnum;
import com.todolist.service.ListaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TarefaControllerTest {

    @Mock
    private ListaServiceImpl listaServiceImpl;

    @InjectMocks
    private TarefaController tarefaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getListaTarefas() {
        TarefaResponseDTO responseDTO = new TarefaResponseDTO();
        when(listaServiceImpl.getListaTarefas()).thenReturn(responseDTO);

        ResponseEntity<TarefaResponseDTO> response = tarefaController.getListaTarefas();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void criarListaTarefas() {
        TarefaRequestDTO requestDTO = new TarefaRequestDTO();
        requestDTO.setTitulo("Test Title");
        requestDTO.setDescricao("Test Description");
        requestDTO.setDataCriacao(LocalDateTime.now());
        requestDTO.setDataConclusao(LocalDateTime.now().plusDays(1));
        requestDTO.setStatus("PREVISTA");
        requestDTO.setTipoTarefa(TipoTarefaEnum.DATA);
        requestDTO.setPrioridadeTarefaEnum(PrioridadeTarefaEnum.MEDIA);

        TarefaEntity tarefaEntity = new TarefaEntity();
        tarefaEntity.setId(1L);
        tarefaEntity.setTitulo("Test Title");
        tarefaEntity.setDescricao("Test Description");

        when(listaServiceImpl.criarListaTarefas(any(TarefaRequestDTO.class))).thenReturn(tarefaEntity);

        ResponseEntity<TarefaEntity> response = tarefaController.criarListaTarefas(requestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefaEntity, response.getBody());
    }

    @Test
    void editarListaTarefas() {
        TarefaRequestDTO requestDTO = new TarefaRequestDTO();
        requestDTO.setTitulo("Updated Title");
        requestDTO.setDescricao("Updated Description");
        requestDTO.setDataCriacao(LocalDateTime.now());
        requestDTO.setDataConclusao(LocalDateTime.now().plusDays(1));
        requestDTO.setStatus("PREVISTA");
        requestDTO.setTipoTarefa(TipoTarefaEnum.DATA);
        requestDTO.setPrioridadeTarefaEnum(PrioridadeTarefaEnum.ALTA);

        TarefaEntity tarefaEntity = new TarefaEntity();
        tarefaEntity.setId(1L);
        tarefaEntity.setTitulo("Updated Title");
        tarefaEntity.setDescricao("Updated Description");

        when(listaServiceImpl.editarListaTarefas(any(TarefaRequestDTO.class))).thenReturn(tarefaEntity);

        ResponseEntity<TarefaEntity> response = tarefaController.editarListaTarefas(requestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefaEntity, response.getBody());
    }

    @Test
    void deletarListaTarefas() {
        doNothing().when(listaServiceImpl).deletarListaTarefas(1L);

        ResponseEntity<String> response = tarefaController.deletarListaTarefas(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals("Tarefa deletada com sucesso", response.getBody());
    }
}

