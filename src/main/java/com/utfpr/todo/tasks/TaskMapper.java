package com.utfpr.todo.tasks;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    
    public TaskModel fromInput(TaskInputDTO input, String userId) {

        return TaskModel.builder()
            .userId(userId)
            .title(input.getTitle())
            .description(input.getDescription())
            .priority(input.getPriority())
            .startAt(input.getStartAt())
            .endAt(input.getEndAt())
            .completed(false)
            .build();

    }

    public TaskOutputDTO fromModel(TaskModel taskModel) {

        return TaskOutputDTO.builder()
            .id(taskModel.getId())
            .userId(taskModel.getUserId())
            .title(taskModel.getTitle())
            .description(taskModel.getDescription())
            .priority(taskModel.getPriority())
            .completed(taskModel.isCompleted())
            .startAt(taskModel.getStartAt())
            .endAt(taskModel.getEndAt())
            .build();

    }

}
