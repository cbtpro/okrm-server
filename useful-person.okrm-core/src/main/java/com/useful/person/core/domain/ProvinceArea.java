/**
 * 省份地区
 */
package com.useful.person.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonView;
import com.useful.person.core.domain.UserInfo.UserInfoDetailView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author cbtpro
 *
 */
@Entity
@Table(name = "t_province_area")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("省份区域模型")
public class ProvinceArea implements Serializable {

	private static final long serialVersionUID = 3669161463250183394L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(generator = "uuid2")
	@ApiModelProperty(value = "全局唯一标识")
	private String uuid;

	@Getter
	@Setter
	@ApiModelProperty(value = "城市代码：例如北京市的code是110000", example = "110000", required = true)
	private String code;

	@Getter
	@Setter
	@ApiModelProperty(value = "城市名称", required = true)
	private String name;

	@Getter
	@Setter
	@ApiModelProperty(value = "上级代码，最上级是86", required = true)
	private String upperCode;

	@Getter
	@Setter
	@ApiModelProperty(value = "描述")
	private String description;

	@Getter
	@Setter
	@JsonView(UserInfoDetailView.class)
	@UpdateTimestamp
	@ApiModelProperty(value = "更新时间，自动获取")
	private Date updateTime;

	@Getter
	@Setter
	@JsonView(UserInfoDetailView.class)
	@Column(nullable = false, insertable = true, updatable = false)
	@CreationTimestamp
	@ApiModelProperty(value = "创建时间，自动获取")
	private Date createTime;
}
