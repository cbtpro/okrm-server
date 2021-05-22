/**
 * 投诉建议
 */
package com.useful.person.core.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
@Entity
@Table(name = "t_suggest")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("用户建议模型")
public class Suggest {

    @Getter
    @Setter
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = true)
    @JoinColumn(name = "user_uuid")
    @ApiModelProperty(value = "用户信息")
    private UserInfo user;

    @Id
    @Getter
    @Setter
    @GeneratedValue(generator = "uuid2")
    @ApiModelProperty(value = "唯一标识，自动获取")
    private String uuid;

    @Getter
    @Setter
    @ApiModelProperty(value = "联系昵称")
    private String nickname;

    @Getter
    @Setter
    @ApiModelProperty(value = "联系微信")
    private String wechat;

    @Getter
    @Setter
    @ApiModelProperty(value = "联系qq")
    private String qq;

    @Getter
    @Setter
    @ApiModelProperty(value = "联系邮箱")
    private String email;

    @Getter
    @Setter
    @ApiModelProperty(value = "联系电话")
    String mobile;

    @Getter
    @Setter
    @NotBlank
    @Column(nullable = false)
    @ApiModelProperty(value = "建议内容")
    private String suggest;

    @Getter
    @Setter
    @Column(nullable = false, insertable = true, updatable = false)
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间自动获取")
    private Date createTime;
}
