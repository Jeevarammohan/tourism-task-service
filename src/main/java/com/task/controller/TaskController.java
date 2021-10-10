package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.Priority;
import com.task.model.Status;
import com.task.model.Task;
import com.task.model.Worker;
import com.task.service.ITaskService;

/**
 * @author JeevaR
 *
 */
@RestController
@RequestMapping("/task-api")
public class TaskController {

	@Autowired
	ITaskService taskService;

	@PostMapping("/tasks")
	ResponseEntity<Task> addTask(@RequestBody Task task) {
		Task ntask = taskService.addTask(task);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Task is added");
		return ResponseEntity.status(HttpStatus.CREATED).body(ntask);

	}

	@PutMapping("/tasks")
	ResponseEntity<Void> updateTask(@RequestBody Task task) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Task is updated");
		taskService.updateTask(task);
		return ResponseEntity.accepted().headers(headers).build();
	}

	@DeleteMapping("/tasks/task-id/{taskId}")
	ResponseEntity<Void> deleteTask(@PathVariable("taskId") Integer taskId) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Task is deleted");
		taskService.deleteTask(taskId);
		return ResponseEntity.ok().headers(headers).build();
	}

	@GetMapping("/tasks/{taskId}")
	ResponseEntity<Task> getByTaskId(@PathVariable("taskId") Integer taskId) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Task is retrieved by id");
		Task ntask = taskService.getByTaskId(taskId);
		return ResponseEntity.ok().body(ntask);
	}

	@GetMapping("/tasks")
	ResponseEntity<List<Task>> getAllTask() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "All Tasks are retrieved");
		List<Task> taskList = taskService.getAllTask();
		return ResponseEntity.ok().body(taskList);
	}

	@GetMapping("/tasks/task-name/{taskName}")
	ResponseEntity<List<Task>> getByName(@PathVariable("taskName") String taskName) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "All Tasks are retrieved by owner");
		List<Task> taskList = taskService.getByName(taskName);
		return ResponseEntity.ok().body(taskList);
	}

	@GetMapping("/tasks/owner/{owner}")
	ResponseEntity<List<Task>> getByOwner(@PathVariable("owner") String owner) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "All Tasks are retrieved by owner");
		List<Task> taskList = taskService.getByOwner(owner);
		return ResponseEntity.ok().body(taskList);
	}

	@GetMapping("/tasks/category/{category}")
	ResponseEntity<List<Task>> getByCategory(@PathVariable("category") String category) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "All Tasks are retrieved by category");
		List<Task> taskList = taskService.getByCategory(category);
		return ResponseEntity.ok().body(taskList);
	}

	@GetMapping("/tasks/priority/{priority}")
	ResponseEntity<List<Task>> getByPriority(@PathVariable("priority") String priority) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "All Tasks are retrieved by priority");
		List<Task> taskList = taskService.getByPriority(Priority.valueOf(priority));
		return ResponseEntity.ok().body(taskList);
	}

	@GetMapping("/tasks/status/{status}")
	ResponseEntity<List<Task>> getByStatus(@PathVariable("status") String status) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "All Tasks are retrieved by status");
		List<Task> taskList = taskService.getByStatus(Status.valueOf(status));
		return ResponseEntity.ok().body(taskList);
	}

	@PutMapping("/tasks/workers")
	ResponseEntity<String> updateWorker(@RequestBody Worker worker) {
		String response = taskService.updateWorker(worker);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/tasks/workers/{workerId}")
	ResponseEntity<Worker> getWorkerById(@PathVariable("workerId") int workerId) {
		Worker worker = taskService.getWorkerById(workerId);
		return ResponseEntity.ok().body(worker);
	}

	@GetMapping("/tasks/workers")
	ResponseEntity<List<Worker>> getAllWorkers() {
		List<Worker> allWorkers = taskService.getAllWorkers();
		return ResponseEntity.ok().body(allWorkers);
	}

	@GetMapping("/tasks/workers/task-id/{taskId}/worker-id/{workerId}/assign")
	ResponseEntity<Worker> assignTaskToWorker(@PathVariable("taskId") Integer taskId,
			@PathVariable("workerId") Integer workerId) {
		Worker worker = taskService.assignTask(taskId, workerId);
		return ResponseEntity.accepted().body(worker);

	}

	@GetMapping("/tasks/workers/task-id/{taskId}/worker-id/{workerId}/free")
	ResponseEntity<Worker> removeTaskFromWorker(@PathVariable("taskId") Integer taskId,
			@PathVariable("workerId") Integer workerId) {
		Worker worker = taskService.removeTask(taskId, workerId);
		return ResponseEntity.accepted().body(worker);

	}

	@GetMapping("/tasks/workers/worker-name/{workerName}")
	ResponseEntity<List<Worker>> getWorkerByName(@PathVariable("workername") String workerName) {
		List<Worker> workersByName = taskService.getWorkerByName(workerName);
		return ResponseEntity.ok().body(workersByName);
	}

	@GetMapping("/tasks/workers/type/{type}")
	ResponseEntity<List<Worker>> getWorkerByType(@PathVariable("type") String type) {
		List<Worker> workersByType = taskService.getWorkerByType(type);
		return ResponseEntity.ok().body(workersByType);
	}

	@GetMapping("/tasks/workers/availability/{availability}")
	ResponseEntity<List<Worker>> getWorkerByAvailability(@PathVariable("availability") String availability) {
		List<Worker> workersByAvailabilty = taskService.getWorkerByAvailability(availability);
		return ResponseEntity.ok().body(workersByAvailabilty);
	}

	@GetMapping("/tasks/workers/no-task")
	ResponseEntity<List<Worker>> getWorkerByNoTask() {
		List<Worker> workersWithNoTask = taskService.getWorkerByNoTask();
		return ResponseEntity.ok().body(workersWithNoTask);
	}

}
