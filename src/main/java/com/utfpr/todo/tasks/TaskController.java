package com.utfpr.todo.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @PostMapping
  public ResponseEntity<TaskOutputDTO> create(HttpServletRequest request, @RequestBody @Valid TaskInputDTO task) {

    String userId = request.getAttribute("userId").toString();

    TaskOutputDTO createdTask = taskService.create(task, userId);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);

  }

  @PatchMapping("/{id}/complete")
  public ResponseEntity<?> complete(@PathVariable String id) {

    TaskModel updatedTask = taskService.complete(id);

    return ResponseEntity.status(HttpStatus.OK).body(updatedTask);

  }

}
