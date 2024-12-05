package com.example.demo.Model;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final MyAppUserRepository userRepository;

    public List<UserTask> getTasksForUser(String username) {
        MyAppUser user = userRepository.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return taskRepository.findByUser(user);
    }

    public UserTask addTask(UserTask task, String username) {
        MyAppUser user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        task.setUser(user);
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public UserTask updateTask(Long taskId, UserTask updatedTask) {
        UserTask existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setPriority(updatedTask.getPriority());
        return taskRepository.save(existingTask);
    }
}
