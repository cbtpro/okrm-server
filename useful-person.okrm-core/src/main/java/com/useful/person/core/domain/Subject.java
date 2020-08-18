/**
 * 学科
 */
package com.useful.person.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
@Entity
@Table(name = "t_subject")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class Subject implements Persistable<String> {

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	private String uuid;

	@Getter
	@Setter
	private String name;


	@Getter
	@Setter
	private String descirption;

	@Getter
	@Setter
	@UpdateTimestamp
	private Date updateTime;

	@Getter
	@Setter
	@Column(nullable = false, insertable = true, updatable = false)
	@CreationTimestamp
	private Date createTime;

	@Override
	public String getId() {
		return this.uuid;
	}

	@Override
	public boolean isNew() {
		return StringUtils.isBlank(this.uuid);
	}
}
