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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.useful.person.core.utils.JsonSerializer.Date2LongSerializer;

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
@Table(name = "t_life_road_map")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("路线图模型")
public class LifeRoadMap implements Serializable {

	private static final long serialVersionUID = 9145399565539742425L;

	@Getter
	@Setter
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
	@JoinColumn(name = "user_uuid")
	@ApiModelProperty(value = "用户信息")
	private UserInfo user;

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	@ApiModelProperty("唯一标识")
	private String uuid;

	@Getter
	@Setter
	@ApiModelProperty(value = "性别", required = true)
	private int sex;

	@Getter
	@Setter
	@ApiModelProperty(value = "事件发生年龄")
	private int age;

	@Getter
	@Setter
	@ApiModelProperty(value = "事件")
	private String event;

	@Getter
	@Setter
	@ApiModelProperty(value = "描述")
	private String description;

	@Getter
	@Setter
	@ApiModelProperty(value = "第几次")
	private int times;

	@Getter
	@Setter
	@UpdateTimestamp
	@JsonSerialize(using = Date2LongSerializer.class)
	@ApiModelProperty(value = "更新时间，自动获取")
	private Date updateTime;

	@Getter
	@Setter
	@Column(nullable = false, insertable = true, updatable = false)
	@CreationTimestamp
	@JsonSerialize(using = Date2LongSerializer.class)
	@ApiModelProperty(value = "创建时间自动获取")
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
