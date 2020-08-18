package com.useful.person.core.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonView;
import com.useful.person.core.domain.UserInfo.UserInfoDetailView;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
@Table(name = "t_temp_file")
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class TempFile implements Serializable {

	private static final long serialVersionUID = -306560126029437310L;

	@Getter
	@Setter
	@Id
	@GeneratedValue(generator = "uuid2")
	private String uuid;

	@Getter
	@Setter
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = true)
	@JoinColumn(name = "user_uuid")
	private UserInfo user;
	/**
	 * 文件的存储地址，目前只有OSS一条路径
	 */
	@Getter
	@Setter
	private String url;

	/**
	 * 删除标记，默认是是true，当确认文件的确是要保留后，立即标记为false
	 */
	@Getter
	@Setter
	@Builder.Default
	private Boolean canBeDelete = true;

	/**
	 * 过期时间，如果过期了，程序就将可以删除的标记的记录从OSS中删除，否则就直接删除这条记录
	 */
	@Getter
	@Setter
	private LocalDateTime expireTime;

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

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expireTime);
	}
}
