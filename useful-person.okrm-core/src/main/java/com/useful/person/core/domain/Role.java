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

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.useful.person.core.domain.UserInfo.UserInfoDetailView;
import com.useful.person.core.utils.JsonSerializer.Date2LongSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("用户角色模型")
public class Role implements Serializable {

	private static final long serialVersionUID = 68671604656999651L;

	@Id
	@Getter
	@Setter
	@JsonView(UserInfoDetailView.class)
	@GeneratedValue(generator = "uuid2")
	@ApiModelProperty("唯一标识")
	private String uuid;

	@Getter
	@Setter
	@JsonView(UserInfoDetailView.class)
	@Column(nullable = false)
	@ApiModelProperty(value = "角色名称", example = "普通用户")
	private String rolename;

	@Getter
	@Setter
	@JsonView(UserInfoDetailView.class)
	@ApiModelProperty(value = "角色描述", example = "只拥有查询权限")
	private String description;


	@Getter
	@Setter
	@JsonView(UserInfoDetailView.class)
	@UpdateTimestamp
	@JsonSerialize(using = Date2LongSerializer.class)
	@ApiModelProperty(value = "更新时间，自动获取")
	private Timestamp updateTime;

	@Getter
	@Setter
	@JsonView(UserInfoDetailView.class)
	@Column(nullable = false, insertable = true, updatable = false)
	@CreationTimestamp
	@JsonSerialize(using = Date2LongSerializer.class)
	@ApiModelProperty(value = "创建时间自动获取")
	private Timestamp createTime;
}
