package com.task;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.task.model.Priority;
import com.task.model.Status;
import com.task.model.Task;
import com.task.model.TravelAgent;
import com.task.service.TaskService;

/**
 * @author JeevaR
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class TourismTaskServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TourismTaskServiceApplication.class, args);
	}

	@Autowired
	private TaskService taskService;

	@Override
	public void run(String... args) throws Exception {
		Task task = new Task();
		task.setTaskName("Clean the room");
		task.setOwner("Prasanna");
		task.setStartDate(LocalDateTime.of(2021, 10, 03, 10, 20));
		task.setEndDate(LocalDateTime.of(2021, 11, 04, 12, 34));
		task.setDuration(50);
		task.setCategory("House Keeping");
		task.setPriority(Priority.LOW);
		task.setStatus(Status.DEFINED);
		TravelAgent agent=new TravelAgent();
		
		System.out.println(taskService.addTask(task));

	}

}
