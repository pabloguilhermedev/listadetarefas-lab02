package com.todolist.unit.service;

import com.todolist.controller.dto.request.TarefaRequestDTO;
import com.todolist.controller.dto.response.TarefaResponseDTO;
import com.todolist.entity.TarefaEntity;
import com.todolist.entity.enums.PrioridadeTarefaEnum;
import com.todolist.entity.enums.StatusTarefaEnum;
import com.todolist.entity.enums.TipoTarefaEnum;
import com.todolist.repository.TarefaRepository;
import com.todolist.service.ListaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
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
        TarefaEntity tarefaEntity = new TarefaEntity();
        tarefaEntity.setId(1L);
        tarefaEntity.setTitulo("Test Title");
        tarefaEntity.setDescricao("Test Description");
        tarefaEntity.setDataCriacao(LocalDateTime.now());
        tarefaEntity.setDataConclusao(LocalDateTime.now().plusDays(1));
        tarefaEntity.setPrioridade("Alta");
        tarefaEntity.setTipoTarefa(TipoTarefaEnum.DATA.name());
        tarefaEntity.setStatus(StatusTarefaEnum.PREVISTA.name());

        when(repository.findAll())
                .thenReturn(Collections.singletonList(tarefaEntity));

        TarefaResponseDTO responseDTO = service.getListaTarefas();

        assertNotNull(responseDTO);
        assertEquals(1, responseDTO.getTarefas().size());
        assertEquals("Test Title", responseDTO.getTarefas().getFirst().getTitulo());
    }

    @Test
    void criarListaTarefas() {
        TarefaRequestDTO requestDTO = new TarefaRequestDTO();
        requestDTO.setTitulo("Test Title");
        requestDTO.setDescricao("Test Description");
        requestDTO.setDataCriacao(LocalDateTime.now());
        requestDTO.setDataConclusao(LocalDateTime.now().plusDays(1));
        requestDTO.setStatus(StatusTarefaEnum.PREVISTA.name());
        requestDTO.setTipoTarefa(TipoTarefaEnum.DATA);
        requestDTO.setPrioridadeTarefaEnum(PrioridadeTarefaEnum.ALTA);

        TarefaEntity tarefaEntity = new TarefaEntity();
        tarefaEntity.setId(1L);
        tarefaEntity.setTitulo("Test Title");
        tarefaEntity.setDescricao("Test Description");

        when(repository.save(any(TarefaEntity.class)))
                .thenReturn(tarefaEntity);

        TarefaEntity result = service.criarListaTarefas(requestDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Title", result.getTitulo());
    }

    @Test
    void editarListaTarefas() {
        TarefaRequestDTO requestDTO = new TarefaRequestDTO();
        requestDTO.setTitulo("Updated Title");
        requestDTO.setDescricao("Updated Description");
        requestDTO.setDataCriacao(LocalDateTime.now());
        requestDTO.setDataConclusao(LocalDateTime.now().plusDays(1));
        requestDTO.setStatus(StatusTarefaEnum.PREVISTA.name());
        requestDTO.setTipoTarefa(TipoTarefaEnum.DATA);
        requestDTO.setPrioridadeTarefaEnum(PrioridadeTarefaEnum.ALTA);

        TarefaEntity tarefaEntity = new TarefaEntity();
        tarefaEntity.setId(1L);
        tarefaEntity.setTitulo("Updated Title");
        tarefaEntity.setDescricao("Updated Description");

        when(repository.save(any(TarefaEntity.class)))
                .thenReturn(tarefaEntity);

        TarefaEntity result = service.editarListaTarefas(requestDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated Title", result.getTitulo());
    }

    @Test
    void deletarListaTarefas() {
        TarefaEntity tarefaEntity = new TarefaEntity();
        tarefaEntity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(tarefaEntity));
        doNothing().when(repository).delete(tarefaEntity);

        service.deletarListaTarefas(1L);

        verify(repository, times(1)).delete(tarefaEntity);
    }

    @Test
    void validarDataConclusao_ThrowsException() {
        TarefaRequestDTO requestDTO = new TarefaRequestDTO();
        requestDTO.setTipoTarefa(TipoTarefaEnum.DATA);
        requestDTO.setDataConclusao(LocalDateTime.now().minusDays(1));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.criarListaTarefas(requestDTO));

        assertEquals("Para tarefas do tipo 'Data', a data de conclusão deve ser igual ou superior à data atual.", exception.getMessage());
    }

    @Test
    void calcularStatus_ThroughGetListaTarefas() {
        TarefaEntity tarefaEntity = new TarefaEntity();
        tarefaEntity.setId(1L);
        tarefaEntity.setTitulo("Test Title");
        tarefaEntity.setDescricao("Test Description");
        tarefaEntity.setDataCriacao(LocalDateTime.now());
        tarefaEntity.setDataConclusao(LocalDateTime.now().minusDays(1));
        tarefaEntity.setPrioridade("Alta");
        tarefaEntity.setTipoTarefa(TipoTarefaEnum.DATA.name());
        tarefaEntity.setStatus(StatusTarefaEnum.PREVISTA.name());

        when(repository.findAll()).thenReturn(Collections.singletonList(tarefaEntity));

        TarefaResponseDTO responseDTO = service.getListaTarefas();

        assertNotNull(responseDTO);
        assertEquals(1, responseDTO.getTarefas().size());
        assertTrue(responseDTO.getTarefas().getFirst().getStatus().contains("dias de atraso"));
    }
}

