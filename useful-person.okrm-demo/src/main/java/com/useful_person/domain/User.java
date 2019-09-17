package com.useful_person.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;
import org.springframework.data.domain.Persistable;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Entity(name = "User")
@Table(name = "t_user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Persistable<String> {

	public interface UserInfoSimpleView {
	};

	public interface UserInfoDetailView extends UserInfoSimpleView {
	};

	@JsonView(UserInfoSimpleView.class)
	@Getter
	@Setter
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String uuid;

	@Getter
	@Setter
	@NotNull(message = "用户名不能为空")
	@JsonView(UserInfoDetailView.class)
	private String username;

	@Getter
	@Setter
	@NotNull(message = "用户昵称不能为空")
	@JsonView(UserInfoSimpleView.class)
	private String nickname;

	@Getter
	@Setter
	@NotNull(message = "密码不能为空")
	private String password;

	@Getter
	@Setter
	@Past(message = "生日必须是过去的时间")
	private Date birthday;
	
	@Getter
	@Setter
	private String identityCard;
	
	@Getter
	@Setter
	@Email(message = "Email格式不正确")
	private String email;

	@Getter
	@Setter
	@Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount", parameters = {
			@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY") })
	private Money hourlyWage;

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

	@Override
	public String getId() {
		return this.uuid;
	}

	@Override
	public boolean isNew() {
		return StringUtils.isEmpty(this.uuid);
	}

}
