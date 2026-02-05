package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequestDTO;
import com.example.taskmanager.dto.TaskResponseDTO;
import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskServiceImplTest {

    private final TaskRepository taskRepository = mock(TaskRepository.class);
    private final TaskServiceImpl taskService = new TaskServiceImpl(taskRepository);

    @Test
    @DisplayName("getTaskById returns task when found")
    void getTaskByIdReturnsTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test task");
        task.setDescription("Test description");
        task.setStatus(TaskStatus.TODO);
        task.setCreatedAt(LocalDateTime.now());

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskResponseDTO result = taskService.getTaskById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Test task", result.getTitle());
        assertEquals(TaskStatus.TODO, result.getStatus());
    }

    @Test
    @DisplayName("getTaskById throws TaskNotFoundException when not found")
    void getTaskByIdThrowsWhenNotFound() {
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(42L));
    }

    @Test
    @DisplayName("createTask persists and returns created task")
    void createTaskPersistsTask() {
        TaskRequestDTO request = new TaskRequestDTO("New task", "Some description", TaskStatus.IN_PROGRESS);

        Task saved = new Task();
        saved.setId(10L);
        saved.setTitle(request.getTitle());
        saved.setDescription(request.getDescription());
        saved.setStatus(request.getStatus());
        saved.setCreatedAt(LocalDateTime.now());

        when(taskRepository.save(Mockito.any(Task.class))).thenReturn(saved);

        TaskResponseDTO result = taskService.createTask(request);

        assertEquals(10L, result.getId());
        assertEquals("New task", result.getTitle());
        assertEquals(TaskStatus.IN_PROGRESS, result.getStatus());
    }
}

