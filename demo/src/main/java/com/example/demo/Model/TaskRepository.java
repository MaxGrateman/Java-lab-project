package com.example.demo.Model;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<UserTask, Long> {
    List<UserTask> findByUser(MyAppUser user);
}
