package com.task.service;

import java.util.List;

import com.task.exception.TaskNotFoundException;
import com.task.model.Task;

/**
 * @author JeevaR
 *
 */
public interface TaskService {

	Task addTask(Task task);

	void updateTask(Task task);

	void deleteTask(Integer taskId);

	Task getByTaskId(Integer taskId) throws TaskNotFoundException;

	List<Task> getAllTask();
	
	List<Task> getByOwner(String owner) throws TaskNotFoundException;

	List<Task> getByCategory(String category) throws TaskNotFoundException;

	List<Task> getByPriority(String priority) throws TaskNotFoundException;

	List<Task> getByStatus(String status) throws TaskNotFoundException;

}
