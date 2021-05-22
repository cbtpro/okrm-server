package com.useful.person.core.domain;

import java.util.Date;

import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.useful.person.core.domain.UserInfo.UserInfoDetailView;
import com.useful.person.core.utils.JsonSerializer.Date2LongSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class BaseDomain {

    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @UpdateTimestamp
    @ApiModelProperty(value = "更新时间，自动获取")
    @JsonSerialize(using = Date2LongSerializer.class)
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