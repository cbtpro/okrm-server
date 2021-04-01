/**
 * 
 */
package com.useful.person.core.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "t_event")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("事件模型")
public class Event implements Persistable<String> {

	@Getter
	@Setter
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "t_event_tags", joinColumns = {
			@JoinColumn(name = "event_id", referencedColumnName = "uuid")
	}, inverseJoinColumns = { @JoinColumn(name = "tag_id", referencedColumnName = "uuid") })
	@JsonIgnoreProperties(value = { "events" })
	@Builder.Default
	@ApiModelProperty(value = "标签集合")
	private Set<Tag> tags = new HashSet<>();

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	@ApiModelProperty("唯一标识")
	private String uuid;

	@Getter
	@Setter
	@Column(nullable = false)
	@ApiModelProperty(value = "事件标题", required = true)
	private String title;

	@Getter
	@Setter
	@ApiModelProperty(value = "事件内容", required = true)
	private String content;

	@Getter
	@Setter
	@ApiModelProperty(value = "事件描述")
	private String description;

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

	@Override
	public String getId() {
		return this.uuid;
	}

	@Override
	public boolean isNew() {
		return StringUtils.isBlank(this.uuid);
	}

}
