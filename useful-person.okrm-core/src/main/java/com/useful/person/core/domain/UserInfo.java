/**
 * 
 */
package com.useful.person.core.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.useful.person.core.annotation.SensitiveInfo;
import com.useful.person.core.constants.UserRole;
import com.useful.person.core.properties.SecurityConstants;
import com.useful.person.core.sensitive.SensitiveType;
import com.useful.person.core.utils.JsonSerializer.Date2LongSerializer;
import com.useful.person.core.utils.JsonSerializer.MoneyJsonDeserializer;
import com.useful.person.core.utils.JsonSerializer.MoneyJsonSerializer;
import com.useful.person.core.vo.GeneralViews;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@ToString
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("用户模型")
public class UserInfo implements UserDetails {

    private static final long serialVersionUID = -2809225013056302802L;

    public interface UserInfoMobileSignupView extends GeneralViews.INormalView {
    }

    public interface UserInfoSimpleView extends UserInfoMobileSignupView {
    };

    public interface UserInfoDetailView extends UserInfoSimpleView {
    };

    public interface UserInfoSecurityView extends UserInfoSimpleView {
    };

    private static Set<Role> defaultRoles = new HashSet<>();
    static {
        defaultRoles.add(Role.builder().rolename(UserRole.NORMAL.getName()).build());
    }
//	@Getter
//	@Setter
//	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private Set<LifeRoadMap> lifeRoadMaps;
//
//	@Getter
//	@Setter
//	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private Set<Suggest> suggests;

    @Id
    @JsonView(UserInfoSimpleView.class)
    @Getter
    @Setter
    @ToString.Include(rank = 0)
    @GeneratedValue(generator = "uuid2")
    @ApiModelProperty(value = "唯一标识，自动获取")
    private String uuid;

    @Getter
    @Setter
    @ToString.Include(rank = 1)
    @NotNull(message = "用户名不能为空")
    @Length(min = 6, max = 12)
    @JsonView(UserInfoDetailView.class)
    @ApiModelProperty(value = "用户名")
    private String username;

    @Getter
    @Setter
    @ToString.Include(rank = 2)
    @NotNull(message = "用户昵称不能为空")
    @JsonView(UserInfoSimpleView.class)
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @Getter
    @Setter
    @JsonView(UserInfoSimpleView.class)
    @ApiModelProperty(value = "头像完整地址")
    private String avatar;

    @Getter
    @Setter
    @ApiModelProperty(value = "头像文件名")
    private String avatarName;

    @Getter
    @Setter
    @ApiModelProperty(value = "头像文件扩展名")
    private String avatarExtension;

    @Getter
    @Setter
    @JsonView(UserInfoMobileSignupView.class)
    @SensitiveInfo(SensitiveType.MOBILE)
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @Getter
    @Setter
    @ToString.Exclude
    @NotNull(message = "密码不能为空")
    @SensitiveInfo(SensitiveType.PASSWORD)
    @ApiModelProperty(value = "密码", example = "^FHJFD^*(EYHBF*D^")
    private String password;

    @Getter
    @Setter
    @JsonView(UserInfoSimpleView.class)
    @Past(message = "生日必须是过去的时间")
    @JsonSerialize(using = Date2LongSerializer.class)
    @ApiModelProperty(value = "生日")
    private Timestamp birthday;

    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @SensitiveInfo(SensitiveType.ID_CARD_NAME)
    @ApiModelProperty(value = "ID名称", example = "陈一禾")
    private String identityCardName;

    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @SensitiveInfo(SensitiveType.ID_CARD)
    @ApiModelProperty(value = "ID NO.", example = "43068220000101000X")
    private String identityCardNo;

    @Getter
    @Setter
    @ToString.Include(rank = 3)
    @JsonView(UserInfoSimpleView.class)
    @Email(message = "Email格式不正确")
    @SensitiveInfo(SensitiveType.EMAIL)
    @ApiModelProperty(value = "电子邮箱")
    private String email;

    // 第一级行政区
    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @ApiModelProperty(value = "所在第一级行政区")
    private String province;

    // 第二级行政区
    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @ApiModelProperty(value = "所在第二级行政区")
    private String city;

    // 第三级行政区
    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @ApiModelProperty(value = "所在第三级行政区")
    private String county;

    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @ApiModelProperty(value = "所在经度", example = "116.4")
    private Double longitude;

    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @ApiModelProperty(value = "所在纬度", example = "39.9")
    private Double latitude;

    @Getter
    @Setter
    @JsonView(UserInfoSimpleView.class)
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount", parameters = {
            @org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY") })
    @JsonDeserialize(using = MoneyJsonDeserializer.class)
    @JsonSerialize(using = MoneyJsonSerializer.class)
    @ApiModelProperty(value = "时薪", example = "CNY 200")
    private Money hourlyWage;

    @Getter
    @Setter
    @Builder.Default
    @JsonView(UserInfoDetailView.class)
    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_role", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "uuid") }, inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "uuid") })
    @ApiModelProperty(value = "拥有角色")
    private Set<Role> roles = defaultRoles;

    @Getter
    @Setter
    @Builder.Default
    @JsonView(UserInfoDetailView.class)
    @Type(type = "boolean")
    @ApiModelProperty(value = "是否启用")
    private Boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Iterator<Role> it = roles.iterator();
        while (it.hasNext()) {
            Role role = it.next();
            authorities.add(new GrantedAuthority() {
                private static final long serialVersionUID = 8273750940438029660L;

                @Override
                public String getAuthority() {
                    String rolename = role.getRolename();
                    if (rolename == null) {
                        rolename = UserRole.NORMAL.getName();
                    }
                    return SecurityConstants.DEFAULT_ROLE_NAME_PREFIX + rolename;
                }
            });
        }
        if (authorities.size() == 0) {
            authorities.add(new GrantedAuthority() {
                private static final long serialVersionUID = 8562597452607156013L;

                @Override
                public String getAuthority() {
                    return SecurityConstants.DEFAULT_ROLE_NAME_PREFIX + UserRole.NORMAL.getName();
                }
            });
        }
        return authorities;
    }

    @Override
    @ApiModelProperty(value = "账号是否未过期")
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @ApiModelProperty(value = "账号是否锁定")
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @ApiModelProperty(value = "凭证是否未过期")
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @ApiModelProperty(value = "是否启用")
    public boolean isEnabled() {
        return true;
    }

    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @JsonSerialize(using = Date2LongSerializer.class)
    @UpdateTimestamp
    @ApiModelProperty(value = "更新时间，自动获取")
    private Timestamp updateTime;

    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @Column(nullable = false, insertable = true, updatable = false)
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = Date2LongSerializer.class)
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间自动获取")
    private Timestamp createTime;

    /**
     * 判断是否拥有某个角色
     * 
     * @param role
     * @return
     */
    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    public void addRoles(List<Role> roles) {
        roles.stream().forEach((role) -> roles.add(role));
    }

    public boolean addRole(Role role) {
        return roles.add(role);
    }

    public boolean removeRole(Role role) {
        return roles.remove(role);
    }
}