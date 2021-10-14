package com.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.task.exception.TaskCategoryNotFoundException;
import com.task.exception.TaskNameNotFoundException;
import com.task.exception.TaskNotFoundException;
import com.task.exception.TaskOwnerNotFoundException;
import com.task.model.Priority;
import com.task.model.Status;
import com.task.model.Task;
import com.task.model.Worker;
import com.task.repository.ITaskRepository;

/**
 * @author JeevaR
 *
 */
@Service
public class TaskServiceImpl implements ITaskService {

	@Autowired
	RestTemplate restTemplate;

	private static final String BASEURL = "http://WORKER-SERVICE/worker-api/";

	@Autowired
	private ITaskRepository taskRepository;

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
		Task task = taskRepository.findById(taskId).get();
		if (task == null) {
			throw new TaskNotFoundException("Invalid task Id");
		}
		return task;
	}

	@Override
	public List<Task> getAllTask() {
		return taskRepository.findAll();
	}

	@Override
	public List<Task> getByName(String taskName) throws TaskNotFoundException {
		List<Task> tasksByName = taskRepository.findByTaskName(taskName);
		if (tasksByName.isEmpty()) {
			throw new TaskNameNotFoundException("Invalid Task Name! Give a valid Task name");
		}
		return tasksByName;
	}

	@Override
	public List<Task> getByCategory(String category) throws TaskNotFoundException {
		List<Task> tasksByCategory = taskRepository.findByCategory(category);
		if (tasksByCategory.isEmpty()) {
			throw new TaskCategoryNotFoundException("Invalid Task Category! Give a valid category");
		}
		return tasksByCategory;
	}

	@Override
	public List<Task> getByPriority(Priority priority) throws TaskNotFoundException {

		List<Task> tasksByPriority = taskRepository.findByPriority(priority);
		if (tasksByPriority.isEmpty()) {
			throw new TaskNotFoundException("Invalid Task priority! Give a valid priority");
		}
		return tasksByPriority;
	}

	@Override
	public List<Task> getByStatus(Status status) throws TaskNotFoundException {
		return taskRepository.findByStatus(status);
	}

	@Override
	public List<Task> getByOwner(String owner) throws TaskNotFoundException {
		List<Task> tasksByOwner = taskRepository.findByOwner(owner);
		if (tasksByOwner.isEmpty()) {
			throw new TaskOwnerNotFoundException("Invalid Task owner! Give a valid owner name");
		}
		return tasksByOwner;
	}

	@Override
	public Worker assignTask(Integer taskId, Integer workerId) throws TaskNotFoundException {
		String url = BASEURL + "workers/" + workerId;
		String updateUrl = BASEURL + "workers";
		ResponseEntity<Worker> workerResponse = restTemplate.getForEntity(url, Worker.class);
		Worker worker = workerResponse.getBody();
		Task task = getByTaskId(taskId);
		task.setStatus(Status.IN_PROGRESS);
		updateTask(task);
		worker.setTask(task);
		ResponseEntity<Worker> response = restTemplate.postForEntity(updateUrl, worker, Worker.class);
		return response.getBody();

	}

	@Override
	public Worker removeTask(Integer taskId, Integer workerId) throws TaskNotFoundException {
		String url = BASEURL + "workers/" + workerId;
		String postUrl = BASEURL + "workers";
		ResponseEntity<Worker> workerResponse = restTemplate.getForEntity(url, Worker.class);
		Worker worker = workerResponse.getBody();
		Task task = getByTaskId(taskId);
		task.setStatus(Status.COMPLETED);
		updateTask(task);
		worker.setTask(null);
		ResponseEntity<Worker> response = restTemplate.postForEntity(postUrl, worker, Worker.class);
		return response.getBody();
	}

	@Override
	public String updateWorker(Worker worker) {
		String url = BASEURL + "workers";
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		return "Updated Worker Details";
	}

	@Override
	public Worker getWorkerById(int workerId) {
		String url = BASEURL + "workers/" + workerId;
		Worker worker = restTemplate.getForEntity(url, Worker.class).getBody();
		return worker;
	}

	@Override
	public List<Worker> getAllWorkers() {
		String url = BASEURL + "workers";
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		return response.getBody();

	}

	@Override
	public List<Worker> getWorkerByName(String workerName) {
		String url = BASEURL + "workers/workerName/" + workerName;
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		return response.getBody();

	}

	@Override
	public List<Worker> getWorkerByType(String type) {
		String url = BASEURL + "workers/type/" + type;
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		return response.getBody();
	}

	@Override
	public List<Worker> getWorkerByAvailability(String availability) {
		String url = BASEURL + "workers/availability/" + availability;
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		return response.getBody();
	}

	@Override
	public List<Worker> getWorkerByNoTask() {
		String url = BASEURL + "workers/no-task";
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		return response.getBody();
	}

}
