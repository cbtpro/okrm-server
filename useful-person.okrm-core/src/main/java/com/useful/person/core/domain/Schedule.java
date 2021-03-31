/**
 * 
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
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("计划表模型")
public class Schedule {


	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	@ApiModelProperty("唯一标识")
	private String uuid;

	/**
	 * 事件
	 */
	@Getter
	@Setter
	@ApiModelProperty
	private String event;

	@Getter
	@Setter
	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	@ApiModelProperty(value = "是否公开")
	private boolean publicity;

	@Getter
	@Setter
	@ApiModelProperty(value = "开始时间")
	private Date startTime;

	@Getter
	@Setter
	@ApiModelProperty(value = "结束时间")
	private Date endTime;

	@Getter
	@Setter
	@ApiModelProperty(value = "地点")
	private String location;

	@Getter
	@Setter
	@ApiModelProperty(value = "经度")
	private Double longitude;

	@Getter
	@Setter
	@ApiModelProperty(value = "纬度")
	private Double latitude;

	@Getter
	@Setter
	@ApiModelProperty(value = "描述")
	private String description;

	@Getter
	@Setter
	@UpdateTimestamp
	@ApiModelProperty(value = "更新时间，自动获取")
	private Date updateTime;

	@Getter
	@Setter
	@Column(nullable = false, insertable = true, updatable = false)
	@CreationTimestamp
	@ApiModelProperty(value = "创建时间自动获取")
	private Date createTime;
}
