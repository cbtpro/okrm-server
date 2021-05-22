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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.useful.person.core.domain.UserInfo.UserInfoDetailView;
import com.useful.person.core.utils.JsonSerializer.Date2LongSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author cbtpro
 *
 */
@Entity
@Table(name = "t_country")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("国家/地区模型")
public class Country implements Serializable {

    private static final long serialVersionUID = 7213672021821440293L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "uuid2")
    @ApiModelProperty("唯一标识")
    private String uuid;

    @Getter
    @Setter
    @ApiModelProperty(value = "序号，不具有排名意义")
    private Long number;

    @Getter
    @Setter
    @ApiModelProperty(value = "显示顺序")
    private Long orderNumber;

    @Getter
    @Setter
    @ApiModelProperty(value = "国际代码", example = "CN", required = true)
    private String internationalDomainCode;

    @Getter
    @Setter
    @ApiModelProperty(value = "中文名称", example = "中国", required = true)
    private String chineseName;

    @Getter
    @Setter
    @ApiModelProperty(value = "英文名称", example = "China", required = true)
    private String englishName;

    @Getter
    @Setter
    @ApiModelProperty(value = "本地名称，使用当地官方语言书写", example = "中国", required = true)
    private String localName;

    @Getter
    @Setter
    @ApiModelProperty(value = "国际电话代码", example = "+86", required = true)
    private String telephoneCode;

    @Getter
    @Setter
    @ApiModelProperty(value = "描述")
    private String description;

    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @UpdateTimestamp
    @JsonSerialize(using = Date2LongSerializer.class)
    @ApiModelProperty(value = "更新时间，自动获取")
    private Date updateTime;

    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @Column(nullable = false, insertable = true, updatable = false)
    @CreationTimestamp
    @JsonSerialize(using = Date2LongSerializer.class)
    @ApiModelProperty(value = "创建时间自动获取")
    private Date createTime;
}
