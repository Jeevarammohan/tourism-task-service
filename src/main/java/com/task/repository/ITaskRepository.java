package com.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.model.Priority;
import com.task.model.Status;
import com.task.model.Task;

/**
 * @author JeevaR
 *
 */
@Repository
public interface ITaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findByTaskName(String taskName);

	List<Task> findByOwner(String owner);

	List<Task> findByCategory(String category);

	List<Task> findByPriority(Priority priority);

	List<Task> findByStatus(Status status);

}
