package com.useful_person.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Task")
@Table(name = "t_task")
@Data
@Builder
public class Task {

	@Id
	@Getter
	@Setter
	private String uuid;

	@ManyToMany
	@JoinTable(name = "t_task_tag")
	private List<Tag> items;
	@Getter
	@Setter
	private String title;
	
	@Getter
	@Setter
	private String content;

	@Getter
	@Setter
	private String description;

	@Getter
	@Setter
	private long createTime;

	@Getter
	@Setter
	private long updateTime;

}
