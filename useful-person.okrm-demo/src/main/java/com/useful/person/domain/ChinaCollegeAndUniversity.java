/**
 * 数据涞源 2019年全国高等学校名单 http://www.moe.gov.cn/jyb_xxgk/s5743/s5744/201906/t20190617_386200.html
 */
package com.useful.person.domain;

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
import com.useful.person.core.authentication.domain.UserInfo.UserInfoDetailView;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
@Entity
@Table(name = "t_china_college_and_university")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class ChinaCollegeAndUniversity {

	/**
	 * uuid，用来标志全局唯一性
	 */
	@Getter
	@Setter
	@Id
	@GeneratedValue(generator = "uuid2")
	private String uuid;

	/**
	 * 序号，不具有排名意义
	 */
	@Getter
	@Setter
	private long number;

	/**
	 * 学校名称
	 */
	@Getter
	@Setter
	private String name;

	/**
	 * 学校标识码
	 */
	@Getter
	@Setter
	private String identificationCode;

	/**
	 * 主管部门
	 */
	@Getter
	@Setter
	private String competentDepartment;

	/**
	 * 所在省份
	 */
	@Getter
	@Setter
	private String province;

	/**
	 * 所在地
	 */
	@Getter
	@Setter
	private String location;

	/**
	 * 经度
	 */
	@Getter
	@Setter
	private float longitude;

	/**
	 * 纬度
	 */
	@Getter
	@Setter
	private float latitude;

	/**
	 * 办学层次
	 */
	@Getter
	@Setter
	private String educationCourse;

	/**
	 * 官网
	 */
	@Getter
	@Setter
	private String officialWebsite;

	/**
	 * 备注：民办或者其他值
	 */
	@Getter
	@Setter
	private String remark;

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
