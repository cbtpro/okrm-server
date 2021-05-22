/**
 * 
 */
package com.useful.person.core.vo;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
@Data
public class LifeRoadMapVO {

    @Getter
    @Setter
    private String uuid;

    /**
     * 性别
     */
    @Getter
    @Setter
    private Integer sex;

    /**
     * 事件发生年龄
     */
    @Getter
    @Setter
    private Integer age;

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
    private Integer times;

    @Getter
    @Setter
    private Date updateTime;

    @Getter
    @Setter
    private Date createTime;

    public LifeRoadMapVO(String uuid, int sex, int age, String event, String description, int times, Date updateTime,
            Date createTime) {
        this.uuid = uuid;
        this.sex = sex;
        this.age = age;
        this.event = event;
        this.description = description;
        this.times = times;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }
}
