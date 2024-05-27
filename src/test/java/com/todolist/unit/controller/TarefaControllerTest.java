package com.todolist.unit.controller;

import com.todolist.controller.TarefaController;
import com.todolist.controller.dto.request.TarefaRequestDTO;
import com.todolist.controller.dto.response.TarefaResponseDTO;
import com.todolist.entity.enums.TipoTarefaEnum;
import com.todolist.mock.MockFactory;
import com.todolist.service.ListaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

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

        final var responseDTO = new TarefaResponseDTO();

        when(listaServiceImpl.getListaTarefas())
                .thenReturn(responseDTO);

        final var response = tarefaController.getListaTarefas();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void criarListaTarefas() {

        final var requestDTO = MockFactory.tarefaRequestDTOMockFactory();

        final var tarefaEntity = MockFactory.tarefaEntityMockFactory();

        when(listaServiceImpl.criarListaTarefas(any(TarefaRequestDTO.class)))
                .thenReturn(tarefaEntity);

        final var response = tarefaController.criarListaTarefas(requestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefaEntity, response.getBody());
    }

    @Test
    void editarListaTarefas() {
        final var requestDTO = MockFactory.tarefaRequestDTOMockFactory();
        final var id = 1L;
        final var tarefaEntity = MockFactory.tarefaEntityMockFactory();

        when(listaServiceImpl.editarListaTarefas(any(TarefaRequestDTO.class), eq(id)))
                .thenReturn(tarefaEntity);

        final var response = tarefaController.editarListaTarefas(requestDTO, id);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tarefaEntity, response.getBody());
    }

    @Test
    void deletarListaTarefas() {

        doNothing()
                .when(listaServiceImpl)
                .deletarListaTarefas(1L);

        final var response = tarefaController.deletarListaTarefas(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tarefa deletada com sucesso", response.getBody());
    }
}

