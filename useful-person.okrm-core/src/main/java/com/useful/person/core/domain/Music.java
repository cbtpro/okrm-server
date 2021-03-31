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
@Table(name = "t_music")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("音乐模型")
public class Music {

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	@ApiModelProperty("唯一标识")
	private String uuid;

	@Getter
	@Setter
	@ApiModelProperty(value = "音乐名称")
	private String title;

	@Getter
	@Setter
	@ApiModelProperty(value = "音乐封面")
	private String pic;

	@Getter
	@Setter
	@ApiModelProperty(value = "表演者")
	private String performer;

	@Getter
	@Setter
	@ApiModelProperty(value = "作词")
	private String writeWords;

	@Getter
	@Setter
	@ApiModelProperty(value = "作曲")
	private String compose;

	@Getter
	@Setter
	@ApiModelProperty(value = "发行日期")
	private String issueDate;

	@Getter
	@Setter
	@ApiModelProperty(value = "发行商")
	private String publisher;

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
