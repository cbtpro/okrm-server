/**
 * 
 */
package com.useful.person.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
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
@Table(name = "t_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class Schedule {


	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	private String uuid;

	/**
	 * 事件
	 */
	@Getter
	@Setter
	private String event;

	/**
	 * 是否公开，公开有助于召集到志同道合的朋友
	 */
	@Getter
	@Setter
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean publicity;

	/**
	 * 开始时间
	 */
	@Getter
	@Setter
	private Date startTime;

	/**
	 * 结束时间
	 */
	@Getter
	@Setter
	private Date endTime;


	/**
	 * 地点
	 */
	@Getter
	@Setter
	private String location;

	/**
	 * 经度
	 */
	@Getter
	@Setter
	private float longitude;

	/**
	 * 纬度
	 */
	@Getter
	@Setter
	private float latitude;
	/**
	 * 描述
	 */
	@Getter
	@Setter
	private String description;

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
