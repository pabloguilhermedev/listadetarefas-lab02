package com.todolist.unit.service;

import com.todolist.entity.TarefaEntity;
import com.todolist.mock.MockFactory;
import com.todolist.repository.TarefaRepository;
import com.todolist.service.ListaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListaServiceImplTest {

    @Mock
    private TarefaRepository repository;

    @InjectMocks
    private ListaServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getListaTarefas() {

        final var tarefaEntity = MockFactory.tarefaEntityMockFactory();

        when(repository.findAll())
                .thenReturn(Collections.singletonList(tarefaEntity));

        final var responseDTO = service.getListaTarefas();

        assertNotNull(responseDTO);
        assertEquals(1, responseDTO.getTarefas().size());
        assertEquals("Test Title", responseDTO.getTarefas().getFirst().getTitulo());
    }

    @Test
    void criarListaTarefas() {

        final var requestDTO = MockFactory.tarefaRequestDTOMockFactory();

        final var tarefaEntity = MockFactory.tarefaEntityMockFactory();

        when(repository.save(any(TarefaEntity.class)))
                .thenReturn(tarefaEntity);

        final var result = service.criarListaTarefas(requestDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Title", result.getTitulo());
    }

//    @Test
//    void editarListaTarefas() {
//
//        final var requestDTO = MockFactory.tarefaRequestDTOMockFactory();
//        requestDTO.setTitulo("Updated Title");
//
//        final var tarefaEntity = MockFactory.tarefaEntityMockFactory();
//
//        when(repository.save(any(TarefaEntity.class)))
//                .thenReturn(tarefaEntity);
//
//        final var result = service.editarListaTarefas(requestDTO);
//
//        assertNotNull(result);
//        assertEquals(1L, result.getId());
//        assertEquals("Updated Title", result.getTitulo());
//    }

    @Test
    void deletarListaTarefas() {

        final var tarefaEntity = MockFactory.tarefaEntityMockFactory();

        when(repository.findById(1L))
                .thenReturn(Optional.of(tarefaEntity));

        doNothing()
                .when(repository)
                .delete(tarefaEntity);

        service.deletarListaTarefas(1L);

        verify(repository, times(1)).delete(tarefaEntity);
    }

//    @Test
//    void validarDataConclusao_ThrowsException() {
//
//        final var requestDTO = MockFactory.tarefaRequestDTOMockFactory();
//
//        final var exception = assertThrows(IllegalArgumentException.class,
//                () -> service.criarListaTarefas(requestDTO));
//
//        assertEquals("Para tarefas do tipo 'Data', a data de conclusão deve ser igual ou superior à data atual.", exception.getMessage());
//    }

    @Test
    void calcularStatus_ThroughGetListaTarefas() {

        final var tarefaEntity = MockFactory.tarefaEntityMockFactory();

        when(repository.findAll())
                .thenReturn(Collections.singletonList(tarefaEntity));

        final var responseDTO = service.getListaTarefas();

        assertNotNull(responseDTO);
        assertEquals(1, responseDTO.getTarefas().size());
        assertTrue(responseDTO.getTarefas().getFirst().getStatus().contains("dias de atraso"));
    }
}

