package com.rickdev.DevTasks.Service;

import com.rickdev.DevTasks.Dto.TaskDto;
import com.rickdev.DevTasks.Model.StatusEnum;
import com.rickdev.DevTasks.Model.TaskModel;
import com.rickdev.DevTasks.Repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.config.Task;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;


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
        task.setDataCriacao(dto.dataCriacao());

        when(taskRepository.save(any(TaskModel.class))).thenReturn(task);

        TaskModel taskSalvas = taskService.salvar(dto);

        assertNotNull(taskSalvas);
        assertEquals("Limpeza", taskSalvas.getTitulo());

        verify(taskRepository, times(1)).save(any(TaskModel.class));
    }

    @Test
    @DisplayName("Should return a list of tasks successfully")
    void listar() {
        TaskModel task1 = new TaskModel();
        task1.setTitulo("Limpeza");
        task1.setDescricao("Realizar limpeza de perifericos");
        task1.setDataCriacao(LocalDateTime.now());
        task1.setStatus(StatusEnum.A_FAZER);

        TaskModel task2 = new TaskModel();
        task2.setTitulo("Manutencao");
        task2.setDescricao("Realizar manutencao de perifericos");
        task2.setDataCriacao(LocalDateTime.now());
        task2.setStatus(StatusEnum.A_FAZER);

        List<TaskModel> listaMocks = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(listaMocks);

        List<TaskModel> tarefas = taskService.listarTodos();

        //deve testar o retorno da service
        assertNotNull(tarefas);
        assertEquals("Limpeza", tarefas.get(0).getTitulo());
        assertEquals("Manutencao", tarefas.get(1).getTitulo());

        verify(taskRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return sucess finding taskById")
    void atualizarWithSucees() {
        Long id = 1L;
        TaskDto dto = new TaskDto("Limpeza",
                "Limpar os PC",
                StatusEnum.A_FAZER,
                LocalDateTime.now());

        TaskModel task = new TaskModel();
        task.setTitulo(dto.titulo());
        task.setDescricao(dto.descricao());
        task.setStatus(dto.status());
        task.setDataCriacao(dto.dataCriacao());

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        when(taskRepository.saveAndFlush(any(TaskModel.class))).thenReturn(task);

        TaskModel taskAtualizada = taskService.atualizarPorId(id, dto);

        assertNotNull(taskAtualizada);
        assertEquals("Limpeza", taskAtualizada.getTitulo());

        verify(taskRepository, times(1)).saveAndFlush(any(TaskModel.class));
    }

    @Test
    @DisplayName("Should return exception when id is invalid")
    void atualizarWithException() {
        Long id = 1L;
        TaskDto dto = new TaskDto("Limpeza",
                "Limpar os PC",
                StatusEnum.A_FAZER,
                LocalDateTime.now());

        TaskModel task = new TaskModel();
        task.setTitulo(dto.titulo());
        task.setDescricao(dto.descricao());
        task.setStatus(dto.status());
        task.setDataCriacao(dto.dataCriacao());

        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, (() -> {
            taskService.atualizarPorId(id, dto);
        }));

        assertEquals("Task Nao existente", exception.getMessage());

        verify(taskRepository, never()).saveAndFlush(task);

    }

}