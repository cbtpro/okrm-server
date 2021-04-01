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
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.useful.person.core.utils.JsonSerializer.Date2LongSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "t_slogon")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("用户口号模型")
public class Slogon {

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	private String uuid;

	@Getter
	@Setter
	private String title;

	@Getter
	@Setter
	private String pic;

	@Getter
	@Setter
	private String author;

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

}
