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
@Table(name = "t_movie")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("电影模型")
public class Movie {

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	@ApiModelProperty(value = "唯一标识，自动获取")
	private String uuid;

	@Getter
	@Setter
	@ApiModelProperty(value = "影视名称")
	private String title;

	@Getter
	@Setter
	@ApiModelProperty(value = "影视封面")
	private String pic;

	@Getter
	@Setter
	@ApiModelProperty(value = "导演")
	private String director;

	@Getter
	@Setter
	@ApiModelProperty(value = "编剧")
	private String scriptwriter;

	@Getter
	@Setter
	@ApiModelProperty(value = "主演")
	private String actor;

	@Getter
	@Setter
	@ApiModelProperty(value = "类型")
	private String type;

	@Getter
	@Setter
	@ApiModelProperty(value = "出品")
	private String producer;

	@Getter
	@Setter
	@ApiModelProperty(value = "语言")
	private String language;

	@Getter
	@Setter
	@ApiModelProperty(value = "豆瓣地址")
	private String doubanUrl;

	@Getter
	@Setter
	@ApiModelProperty(value = "imdb地址")
	private String imdbUrl;

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
