package com.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.exception.TaskNotFoundException;
import com.task.model.Task;
import com.task.repository.TaskRepository;

/**
 * @author JeevaR
 *
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task addTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public void updateTask(Task task) {
		taskRepository.save(task);
	}

	@Override
	public void deleteTask(Integer taskId) {
		taskRepository.deleteById(taskId);
	}

	@Override
	public Task getByTaskId(Integer taskId) throws TaskNotFoundException {
		return taskRepository.findById(taskId).get();
	}

	@Override
	public List<Task> getAllTask() {
		return taskRepository.findAll();
	}

	@Override
	public List<Task> getByCategory(String category) throws TaskNotFoundException {
		return taskRepository.findByCategory(category);
	}

	@Override
	public List<Task> getByPriority(String priority) throws TaskNotFoundException {
		return taskRepository.findByPriority(priority);
	}

	@Override
	public List<Task> getByStatus(String status) throws TaskNotFoundException {
		return taskRepository.findByStatus(status);
	}

	@Override
	public List<Task> getByOwner(String owner) throws TaskNotFoundException {
		return taskRepository.findByOwner(owner);
	}

}
