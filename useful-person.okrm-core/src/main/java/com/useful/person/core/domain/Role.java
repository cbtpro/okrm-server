package com.useful.person.core.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class Role implements Serializable {

	private static final long serialVersionUID = 68671604656999651L;

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	private String uuid;

	@Getter
	@Setter
	private String rolename;

	@Getter
	@Setter
	private String description;


	@Getter
	@Setter
	@UpdateTimestamp
	private Timestamp updateTime;

	@Getter
	@Setter
	@Column(nullable = false, insertable = true, updatable = false)
	@CreationTimestamp
	private Timestamp createTime;
}
