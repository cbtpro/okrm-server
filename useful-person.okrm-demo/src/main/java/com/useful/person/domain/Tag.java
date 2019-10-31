package com.useful.person.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.useful.person.core.authentication.domain.UserInfo.UserInfoDetailView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
@Entity
@Table(name = "t_tag")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

	@Getter
	@Setter
	@ManyToMany(mappedBy = "tags")
	@JsonIgnoreProperties(value = { "tags" })
	@Builder.Default
	private Set<Task> tasks = new HashSet<>();

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String uuid;

	@Getter
	@Setter
	@NotNull
	private String title;

	@Getter
	@Setter
	private String description;


	@Getter
	@Setter
	@JsonView(UserInfoDetailView.class)
	@UpdateTimestamp
	private Date updateTime;

	@Getter
	@Setter
	@JsonView(UserInfoDetailView.class)
	@Column(nullable = false, insertable = true, updatable = false)
	@CreationTimestamp
	private Date createTime;

}
