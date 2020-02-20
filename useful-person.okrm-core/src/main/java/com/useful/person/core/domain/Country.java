/**
 * 国家代码
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
@Table(name = "t_country")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class Country implements Serializable {

	private static final long serialVersionUID = 7213672021821440293L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(generator = "uuid2")
	private String uuid;

	/**
	 *  序号，不具有排名意义
	 */
	@Getter
	@Setter
	private Long number;

	/**
	 * 显示顺序
	 */
	@Getter
	@Setter
	private Long orderNumber;

	@Getter
	@Setter
	private String internationalDomainCode;

	@Getter
	@Setter
	private String chineseName;

	@Getter
	@Setter
	private String englishName;

	@Getter
	@Setter
	private String localName;

	@Getter
	@Setter
	private String telephoneCode;

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
