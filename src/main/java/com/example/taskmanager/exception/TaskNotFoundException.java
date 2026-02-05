package com.example.taskmanager.exception;

/**
 * Exception thrown when a task cannot be found.
 */
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Task not found with id: " + id);
    }
}

