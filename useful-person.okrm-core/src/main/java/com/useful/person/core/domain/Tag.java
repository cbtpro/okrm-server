package com.useful.person.core.domain;

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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("标签模型")
public class Tag {

	@Getter
	@Setter
	@ManyToMany(mappedBy = "tags")
	@JsonIgnoreProperties(value = { "tags" })
	@Builder.Default
	@ApiModelProperty(value = "事件列表")
	private Set<Event> events = new HashSet<>();

	@Getter
	@Setter
	@ManyToMany(mappedBy = "tags")
	@JsonIgnoreProperties(value = { "tags" })
	@Builder.Default
	@ApiModelProperty(value = "兴趣列表")
	private Set<Hobby> hobbys = new HashSet<>();

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	@ApiModelProperty("唯一标识")
	private String uuid;

	@Getter
	@Setter
	@NotNull
	@ApiModelProperty(value = "标签名称")
	private String title;

	@Getter
	@Setter
	@ApiModelProperty(value = "标签描述")
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
