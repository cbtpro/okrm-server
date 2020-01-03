/**
 * 投诉建议
 */
package com.useful.person.core.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
@Entity
@Table(name = "t_suggest")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class Suggest {

	@Getter
	@Setter
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = true)
	@JoinColumn(name = "user_uuid")
	private UserInfo user;

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	private String uuid;

	@Getter
	@Setter
	private String nickname;

	@Getter
	@Setter
	private String wechat;

	@Getter
	@Setter
	private String qq;

	@Getter
	@Setter
	private String email;

	@Getter
	@Setter
	String mobile;

	@Getter
	@Setter
	@NotBlank
	private String suggest;

}
