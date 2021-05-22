/**
 * 学院
 */
package com.useful.person.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.useful.person.core.utils.JsonSerializer.Date2LongSerializer;

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
@Table(name = "t_university")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class University {

    @Id
    @Getter
    @Setter
    @GeneratedValue(generator = "uuid2")
    private String uuid;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String descirption;

    @Getter
    @Setter
    @UpdateTimestamp
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    @Getter
    @Setter
    @Column(nullable = false, insertable = true, updatable = false)
    @CreationTimestamp
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

}
