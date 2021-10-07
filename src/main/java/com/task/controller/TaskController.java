package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.Task;
import com.task.service.TaskService;

/**
 * @author JeevaR
 *
 */
@RestController
@RequestMapping("/task-api")
public class TaskController {

	@Autowired
	TaskService taskService;

	@PostMapping("/tasks")
	ResponseEntity<Task> addTask(@RequestBody Task task) {
		Task ntask = taskService.addTask(task);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Task is added");
		return ResponseEntity.ok().body(ntask);

	}

	@PutMapping("/tasks")
	ResponseEntity<Void> updateTask(@RequestBody Task task) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Task is updated");
		taskService.updateTask(task);
		return ResponseEntity.ok().headers(headers).build();
	}

	@DeleteMapping("/tasks/taskid/{taskId}")
	ResponseEntity<Void> deleteTask(@PathVariable("taskId") Integer taskId) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Task is deleted");
		taskService.deleteTask(taskId);
		return ResponseEntity.ok().headers(headers).build();
	}

	@GetMapping("/task/{taskId}")
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
		List<Task> taskList = taskService.getByPriority(priority);
		return ResponseEntity.ok().body(taskList);
	}

	@GetMapping("/tasks/status/{status}")
	ResponseEntity<List<Task>> getByStatus(@PathVariable("status") String status) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "All Tasks are retrieved by status");
		List<Task> taskList = taskService.getByStatus(status);
		return ResponseEntity.ok().body(taskList);
	}
}
