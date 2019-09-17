package com.useful_person.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Tag")
@Table(name = "t_task_tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

	@Id
	@Getter
	@Setter
	private String uuid;

//	@ManyToMany
//	@JoinTable(name = "t_task")
//	private List<Task> items;

	@Getter
	@Setter
	@NotNull
	private String title;

	@Getter
	@Setter
	private String description;

	@Getter
	@Setter
	private String createTime;

	@Getter
	@Setter
	private String updateTime;

}
