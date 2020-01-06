/**
 * 
 */
package com.useful.person.core.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.joda.money.Money;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;

/**
 * @author peter
 *
 */
@Data
@Builder
@Entity
@Table(name = "t_user")
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class UserInfo implements UserDetails  {

	private static final long serialVersionUID = -2809225013056302802L;

	public interface UserInfoSimpleView {};

	public interface UserInfoDetailView extends UserInfoSimpleView {};

	public interface UserInfoSecurityView extends UserInfoSimpleView {};

	@Getter
	@Setter
	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<LifeRoadMap> lifeRoadMapList;

	@Getter
	@Setter
	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Suggest> suggestList;

	@Id
	@JsonView(UserInfoSimpleView.class)
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	private String uuid;

	@Getter
	@Setter
	@NotNull(message = "用户名不能为空")
	@Length(min = 6, max = 12)
	@JsonView(UserInfoDetailView.class)
	private String username;

	@Getter
	@Setter
	@NotNull(message = "用户昵称不能为空")
	@JsonView(UserInfoSimpleView.class)
	private String nickname;

	@Getter
	@Setter
	@JsonView(UserInfoSimpleView.class)
	private String avatar;

	@Getter
	@Setter
	@JsonView(UserInfoSimpleView.class)
	private String mobile;

	@Getter
	@Setter
	@NotNull(message = "密码不能为空")
	private String password;

	@Getter
	@Setter
	@JsonView(UserInfoSimpleView.class)
	@Past(message = "生日必须是过去的时间")
	private Date birthday;
	
	@Getter
	@Setter
	private String identityCard;
	
	@Getter
	@Setter
	@JsonView(UserInfoSimpleView.class)
	@Email(message = "Email格式不正确")
	private String email;

	@Getter
	@Setter
	@JsonView(UserInfoSimpleView.class)
	@Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount", parameters = {
			@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY") })
	private Money hourlyWage;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("normal");
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

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