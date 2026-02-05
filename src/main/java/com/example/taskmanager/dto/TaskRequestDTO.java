package com.example.taskmanager.dto;

import com.example.taskmanager.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;

/**
 * Incoming payload for creating or updating a task.
 */
public class TaskRequestDTO {

    @NotBlank(message = "Title must not be blank")
    private String title;

    private String description;

    private TaskStatus status;

    public TaskRequestDTO() {
    }

    public TaskRequestDTO(String title, String description, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

