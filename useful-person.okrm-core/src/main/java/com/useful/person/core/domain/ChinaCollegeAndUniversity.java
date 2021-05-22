/**
 * 数据涞源 2019年全国高等学校名单 http://www.moe.gov.cn/jyb_xxgk/s5743/s5744/201906/t20190617_386200.html
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
 * @author peter
 *
 */
@Entity
@Table(name = "t_china_college_and_university")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("学校模型")
public class ChinaCollegeAndUniversity implements Serializable {

    private static final long serialVersionUID = -2234841852430857074L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "uuid2")
    @ApiModelProperty(value = "用来标志全局唯一性，自动获取")
    private String uuid;

    @Getter
    @Setter
    @ApiModelProperty("序号，不具有排名意义")
    private long number;

    @Getter
    @Setter
    @ApiModelProperty(value = "学校名称", required = true)
    private String name;

    @Getter
    @Setter
    @ApiModelProperty(value = "学校标识码", required = true)
    private String identificationCode;

    @Getter
    @Setter
    @ApiModelProperty(value = "主管部门：教育部、北京市、工业和信息化部、中央办公厅、国家卫生健康委员会、应急管理部、外交部、公安部、国家体育总局、国家民委、中华妇女联合会、北京市教委、共青团中央、中华全国总工会等", required = true)
    private String competentDepartment;

    @Getter
    @Setter
    @ApiModelProperty(value = "所在省份", required = true)
    private String province;

    @Getter
    @Setter
    @ApiModelProperty(value = "地址", required = true)
    private String location;

    @Getter
    @Setter
    @ApiModelProperty(value = "经纬度：经度")
    private Double longitude;

    @Getter
    @Setter
    @ApiModelProperty(value = "经纬度：纬度")
    private Double latitude;

    @Getter
    @Setter
    @ApiModelProperty(value = "办学层次：本科、专科等", required = true)
    private String educationCourse;

    @Getter
    @Setter
    @ApiModelProperty(value = "官网")
    private String officialWebsite;

    @Getter
    @Setter
    @ApiModelProperty(value = "备注：民办或着其他值")
    private String remark;

    @Getter
    @Setter
    @ApiModelProperty(value = "类型：全日制、非全日制", required = true)
    private String type;

    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @UpdateTimestamp
    @JsonSerialize(using = Date2LongSerializer.class)
    @ApiModelProperty(value = "更新时间，UTC自动获取")
    private Date updateTime;

    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @Column(nullable = false, insertable = true, updatable = false)
    @CreationTimestamp
    @JsonSerialize(using = Date2LongSerializer.class)
    @ApiModelProperty(value = "创建时间，UTC自动获取")
    private Date createTime;
}
