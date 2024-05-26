package com.todolist.integration;

import com.todolist.TodoListaApplication;
import com.todolist.controller.dto.request.TarefaRequestDTO;
import com.todolist.entity.enums.TipoTarefaEnum;
import com.todolist.mock.MockFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {TodoListaApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class TarefaControllerIntegrationTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.port = 8080;
    }

    @Test
    public void testCriarListaTarefas() {
        final var newTask = MockFactory.tarefaRequestDTOMockFactory();
        newTask.setTipoTarefa(TipoTarefaEnum.LIVRE);

        given()
                .contentType(ContentType.JSON)
                .body(newTask)
                .when()
                .post("/api/tarefas")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("titulo", equalTo("Test Title"))
                .body("descricao", equalTo("Test Description"));
    }

    @Test
    public void testGetListaTarefas() {
        given()
                .when()
                .get("/api/tarefas")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("tarefas", notNullValue())
                .body("tarefas.size()", greaterThan(0));
    }

    @Test
    public void testDeletarListaTarefas() {
        given()
                .when()
                .delete("/api/tarefas/{id}", 1)
                .then()
                .statusCode(200)
                .body(equalTo("Tarefa deletada com sucesso"));
    }
}
