package com.task.model;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Worker {

	@Id
	private Integer workerId;
	private String workerName;
	@Enumerated(EnumType.STRING)
	private WorkerType type;
	@Enumerated(EnumType.STRING)
	private Availability availability;
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;
}
