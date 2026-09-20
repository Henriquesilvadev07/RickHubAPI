package com.rickdev.DevTasks.Service;

import com.rickdev.DevTasks.Dto.TaskDto;
import com.rickdev.DevTasks.Model.StatusEnum;
import com.rickdev.DevTasks.Model.TaskModel;
import com.rickdev.DevTasks.Repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should salvar task successfully")
    void salvar() {
        TaskDto dto = new TaskDto("Limpeza",
                "Limpar os PC",
                StatusEnum.A_FAZER,
                LocalDateTime.now());

        TaskModel task = new TaskModel();
        task.setTitulo(dto.titulo());
        task.setDescricao(dto.descricao());
        task.setStatus(dto.status());
        task.setDataCriacao(dto.dataCricacao());
    }

}