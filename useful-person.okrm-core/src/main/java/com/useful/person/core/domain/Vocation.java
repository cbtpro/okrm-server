/**
 * 职业：作家、画家、销售、程序员、企业主、饭店老板、保险经纪人、房产经纪人、劳工、建筑工人、保安、无业游民
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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 * 职业
 */
@Entity
@Table(name = "t_vocation")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("职业模型")
public class Vocation {

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	@ApiModelProperty("唯一标识")
	private String uuid;

	@Getter
	@Setter
	@ApiModelProperty(value = "职业名称", example = "程序员")
	private String vocation;

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
