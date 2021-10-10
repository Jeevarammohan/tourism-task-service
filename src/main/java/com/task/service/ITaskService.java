package com.task.service;

import java.util.List;

import com.task.exception.TaskNotFoundException;
import com.task.model.Priority;
import com.task.model.Status;
import com.task.model.Task;
import com.task.model.Worker;

/**
 * @author JeevaR
 *
 */
public interface ITaskService {

	Task addTask(Task task);

	void updateTask(Task task);

	void deleteTask(Integer taskId);

	Task getByTaskId(Integer taskId) throws TaskNotFoundException;

	List<Task> getAllTask();

	List<Task> getByName(String taskName) throws TaskNotFoundException;

	List<Task> getByOwner(String owner) throws TaskNotFoundException;

	List<Task> getByCategory(String category) throws TaskNotFoundException;

	List<Task> getByPriority(Priority priority) throws TaskNotFoundException;

	List<Task> getByStatus(Status status) throws TaskNotFoundException;
	
	Worker assignTask(Integer taskId,Integer workerId) throws TaskNotFoundException;
	
	Worker removeTask(Integer taskId,Integer workerId) throws TaskNotFoundException;

	String updateWorker(Worker worker);

	Worker getWorkerById(int workerId);

	List<Worker> getAllWorkers();

	List<Worker> getWorkerByName(String workerName);

	List<Worker> getWorkerByType(String type);

	List<Worker> getWorkerByAvailability(String availability);

	List<Worker> getWorkerByNoTask();

}
