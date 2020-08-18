/**
 * 业余爱好：运动、相声、音乐、游泳、园艺、电影
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.useful.person.core.domain.UserInfo.UserInfoDetailView;

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
@Table(name = "t_hobby")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class Hobby {

	@Getter
	@Setter
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "t_hobby_tags", joinColumns = {
			@JoinColumn(name = "hobby_id", referencedColumnName = "uuid")
	}, inverseJoinColumns = { @JoinColumn(name = "tag_id", referencedColumnName = "uuid")})
	@JsonIgnoreProperties(value = { "hobbys" })
	@Builder.Default
	private Set<Tag> tags = new HashSet<>();

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
	private String content;

	@Getter
	@Setter
	private String description;

	@Getter
	@Setter
	@JsonView(UserInfoDetailView.class)
	@UpdateTimestamp
	private Date updateTime;

	@Getter
	@Setter
	@JsonView(UserInfoDetailView.class)
	@Column(nullable = false, insertable = true, updatable = false)
	@CreationTimestamp
	private Date createTime;
}
