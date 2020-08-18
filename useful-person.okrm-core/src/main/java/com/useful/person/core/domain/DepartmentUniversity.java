/**
 * 大学的院系结构 structure of the university
 * 大学院系：土木工程、考古、经管系、社会学系、信息管理系、历史学系、中国语言文学系、国际关系学系
 */
package com.useful.person.core.domain;

import java.util.Date;

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

/**
 * @author peter
 *
 */
@Entity
@Table(name = "t_department_university")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class DepartmentUniversity {

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

}
