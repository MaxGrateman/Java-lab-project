package com.example.demo.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.TaskService;
import com.example.demo.Model.UserTask;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<UserTask> getTasks(Principal principal) {
        return taskService.getTasksForUser(principal.getName());
    }

    @PostMapping
    public UserTask addTask(@RequestBody UserTask task, Principal principal) {
        return taskService.addTask(task, principal.getName());
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}")
    public UserTask updateTask(@PathVariable Long taskId, @RequestBody UserTask task) {
        return taskService.updateTask(taskId, task);
    }
    
}
