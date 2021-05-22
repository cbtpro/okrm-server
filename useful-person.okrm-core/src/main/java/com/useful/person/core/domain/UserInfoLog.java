/**
 * 用户操作历史
 */
package com.useful.person.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonView;
import com.useful.person.core.constants.UserAction;
import com.useful.person.core.domain.UserInfo.UserInfoDetailView;

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
@Table(name = "t_user_info_log")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class UserInfoLog implements Serializable {

    private static final long serialVersionUID = 6620662048345368634L;

    @Getter
    @Setter
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)
    @JoinColumn(name = "user_uuid")
    private UserInfo user;

    @Id
    @Getter
    @Setter
    @GeneratedValue(generator = "uuid2")
    private String uuid;

    @Getter
    @Setter
    @Enumerated
    @NotNull
    private UserAction actionType;

    @Getter
    @Setter
    private String actionValue;

    @Getter
    @Setter
    private String oldValue;

    @Getter
    @Setter
    @JsonView(UserInfoDetailView.class)
    @Column(nullable = false, insertable = true, updatable = false)
    @CreationTimestamp
    private Date createTime;
}
