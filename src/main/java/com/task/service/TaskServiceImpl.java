package com.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.task.exception.TaskNotFoundException;
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
	@Autowired
	private static final String BASEURL = "http://WORKER-SERVICE/worker-service/";

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
