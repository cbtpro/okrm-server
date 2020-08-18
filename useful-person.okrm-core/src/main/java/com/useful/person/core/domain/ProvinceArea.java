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

import lombok.Getter;
import lombok.Setter;

/**
 * @author cbtpro
 *
 */
@Entity
@Table(name = "t_province_area")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class ProvinceArea implements Serializable {

	private static final long serialVersionUID = 3669161463250183394L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(generator = "uuid2")
	private String uuid;

	@Getter
	@Setter
	private String code;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String upperCode;

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
