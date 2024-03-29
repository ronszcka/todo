package com.utfpr.todo.tasks;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.utfpr.todo.exceptions.NotFoundException;
import com.utfpr.todo.exceptions.ValidationException;

@Service
public class TaskService {

  private TaskRepository taskRepository;

  private TaskMapper taskMapper;

  public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.taskMapper = taskMapper;
  }

  public TaskOutputDTO create(TaskInputDTO taskInput, String userId) {

    LocalDateTime currentDate = LocalDateTime.now();

    if (currentDate.isAfter(taskInput.getStartAt())) {
      throw new ValidationException("A data de início deve ser maior que a data atual");
    }

    if (taskInput.getEndAt().isBefore(taskInput.getStartAt())) {
      throw new ValidationException("A data de término deve ser maior que a data de início");
    }

    TaskModel taskModel = taskMapper.fromInput(taskInput, userId);

    TaskModel createdTask = taskRepository.save(taskModel);

    TaskOutputDTO taskOutput = taskMapper.fromModel(createdTask);

    return taskOutput;

  }

  public TaskModel complete(String id) {

    TaskModel task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found"));

    task.setCompleted(true);

    return taskRepository.save(task);

  }

}
