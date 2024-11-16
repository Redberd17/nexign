package ru.nefedova.nexign.input.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nefedova.nexign.input.web.model.TaskRequest;
import ru.nefedova.nexign.input.web.model.TaskResponse;
import ru.nefedova.nexign.input.web.model.TaskShortResponse;
import ru.nefedova.nexign.service.TaskCompositeService;

import java.util.List;

import static ru.nefedova.nexign.common.constant.Endpoint.Task.TASKS;
import static ru.nefedova.nexign.common.constant.Endpoint.Task.TASKS_BY_ID_PATTERN;
import static ru.nefedova.nexign.common.constant.Endpoint.Task.TASKS_STATUS_BY_ID;

@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "Задачи", description = "Контроллер для управления задачами")
public class TaskController {

    private final TaskCompositeService taskCompositeService;

    @Operation(summary = "Получение всех задач")
    @GetMapping(TASKS)
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(taskCompositeService.getAllTasks());
    }

    @Operation(summary = "Получение задачи по id")
    @GetMapping(TASKS_BY_ID_PATTERN)
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable long id) {
        return ResponseEntity.ok(taskCompositeService.getTaskById(id));
    }

    @Operation(summary = "Получение статуса задачи по id")
    @GetMapping(TASKS_STATUS_BY_ID)
    public ResponseEntity<TaskShortResponse> getTaskStatusById(@PathVariable long id) {
        return ResponseEntity.ok(taskCompositeService.getTaskStatusById(id));
    }

    @Operation(summary = "Создание задачи")
    @PostMapping(TASKS)
    public ResponseEntity<Long> createTask(@RequestBody @Valid TaskRequest request) {
        return ResponseEntity.ok(taskCompositeService.createTask(request));
    }

    @Operation(summary = "Удаление задачи")
    @DeleteMapping(TASKS_BY_ID_PATTERN)
    public ResponseEntity<String> deleteTask(@PathVariable long id) {
        return ResponseEntity.ok(taskCompositeService.deleteById(id));
    }

}
