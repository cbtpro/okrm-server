/**
 * 人生路线图，包含人生在合适年纪应该发生的重大事件
 */
package com.useful.person.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author peter
 *
 */
@Entity
@Table(name = "t_life_map")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LifeMap {

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String uuid;

	/**
	 * 性别
	 */
	@Getter
	@Setter
	private int sex;

	/**
	 * 事件发生年龄
	 */
	@Getter
	@Setter
	private int age;

	/**
	 * 事件
	 */
	@Getter
	@Setter
	private String event;

	/**
	 * 描述
	 */
	@Getter
	@Setter
	private String description;

	/**
	 * 第几次
	 */
	@Getter
	@Setter
	private int times;
}
