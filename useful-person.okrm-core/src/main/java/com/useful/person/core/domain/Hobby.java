/**
 * 业余爱好：运动、相声、音乐、游泳、园艺、电影
 */
package com.useful.person.core.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.useful.person.core.domain.UserInfo.UserInfoDetailView;
import com.useful.person.core.utils.JsonSerializer.Date2LongSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author peter
 *
 */
@Entity
@Table(name = "t_hobby")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("兴趣爱好模型")
public class Hobby {

    @Getter
    @Setter
    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "t_hobby_tags", joinColumns = {
            @JoinColumn(name = "hobby_id", referencedColumnName = "uuid") }, inverseJoinColumns = {
                    @JoinColumn(name = "tag_id", referencedColumnName = "uuid") })
    @JsonIgnoreProperties(value = { "hobbys" })
    @Builder.Default
    @ApiModelProperty(value = "标签集合")
    private Set<Tag> tags = new HashSet<>();

    @Id
    @Getter
    @Setter
    @GeneratedValue(generator = "uuid2")
    @ApiModelProperty("唯一标识")
    private String uuid;

    @Getter
    @Setter
    @ApiModelProperty(value = "兴趣标题", required = true)
    private String title;

    @Getter
    @Setter
    @ApiModelProperty(value = "兴趣内容", required = true)
    private String content;

    @Getter
    @Setter
    @ApiModelProperty(value = "兴趣描述")
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
