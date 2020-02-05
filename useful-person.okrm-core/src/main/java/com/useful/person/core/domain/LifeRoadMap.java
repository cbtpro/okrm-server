/**
 * 人生路线图，包含人生在合适年纪应该发生的重大事件
 */
package com.useful.person.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author peter
 *
 */
@Entity
@Table(name = "t_life_road_map")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class LifeRoadMap implements Serializable {

	private static final long serialVersionUID = 9145399565539742425L;

	@Getter
	@Setter
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
	@JoinColumn(name = "user_uuid")
	private UserInfo user;

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	private String uuid;

	/**
	 * 性别
	 */
	@Getter
	@Setter
	private int sex;

	/**
	 * 事件发生年龄
	 */
	@Getter
	@Setter
	private int age;

	/**
	 * 事件
	 */
	@Getter
	@Setter
	private String event;

	/**
	 * 描述
	 */
	@Getter
	@Setter
	private String description;

	/**
	 * 第几次
	 */
	@Getter
	@Setter
	private int times;

	@Getter
	@Setter
	@UpdateTimestamp
	private Date updateTime;

	@Getter
	@Setter
	@Column(nullable = false, insertable = true, updatable = false)
	@CreationTimestamp
	private Date createTime;

	public LifeRoadMap(String uuid, int sex, int age, String event, String description, int times, Date updateTime, Date createTime) {
		this.uuid = uuid;
		this.sex = sex;
		this.age = age;
		this.event = event;
		this.description = description;
		this.times = times;
		this.updateTime = updateTime;
		this.createTime = createTime;
	}
}
