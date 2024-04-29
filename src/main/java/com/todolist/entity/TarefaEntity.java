package com.todolist.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Schema(description = "Entidade que representa todos os detalhes de uma tarefa. ")
@Table(name = "tarefas")
public class TarefaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "data_conclusao", nullable = false)
    private LocalDateTime dataConclusao;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "tipo_tarefa")
    private String tipoTarefa;
    @Column(name = "prioridade")
    private String prioridade;

    public TarefaEntity(String tipoTarefa, String prioridade) {
        this.tipoTarefa = tipoTarefa;
        this.prioridade = prioridade;
    }

    public TarefaEntity(String titulo, String descricao, LocalDateTime dataCriacao, LocalDateTime dataConclusao,
                        String status,
                        String tipoTarefa,
                        String prioridade) {

        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.status = status;
        this.tipoTarefa = tipoTarefa;
        this.prioridade = prioridade;
    }
}
