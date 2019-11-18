/**
 * 
 */
package com.useful.person.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "t_book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
public class Book {

	@Id
	@Getter
	@Setter
	@GeneratedValue(generator = "uuid2")
	private String uuid;

	/**
	 * 书名 eg. 有理想就有疼痛
	 */
	@Getter
	@Setter
	private String title;

	/**
	 * 副书名 eg. 中国当代文化名人访谈录
	 */
	@Getter
	@Setter
	private String subtitle;

	/**
	 * 图片 eg. http://api.jisuapi.com/isbn/upload/1/1.jpg
	 */
	@Getter
	@Setter
	private String pic;

	/**
	 * 作者 eg. 高晓春
	 */
	@Getter
	@Setter
	private String author;

	/**
	 * 摘要 eg. 《有理想就有疼痛:中国当代文化名人访谈录》是一份关于当代中国文化的最真实底稿，收录了高晓春与白先勇、冯骥才、余华、莫言、余秋雨、陈忠实等20位当代中国文化大家的对话。通过近距离的访谈，展现了这些文化大家多彩的内心世界，也阐释了他们对生命、艺术、财富及文化的理解。
	 */
	@Getter
	@Setter
	private String summary;

	/**
	 * 出版社 
	 */
	@Getter
	@Setter
	private String publisher;


	/**
	 * 出版地
	 */
	@Getter
	@Setter
	private String pubplace;

	/**
	 * 出版时间
	 */
	@Getter
	@Setter
	private String pubdate;

	/**
	 * 页数
	 */
	@Getter
	@Setter
	private String page;

	/**
	 * 价格
	 */
	@Getter
	@Setter
	private String price;

	/**
	 * 装帧方式
	 */
	@Getter
	@Setter
	private String binding;

	/**
	 * ISBN eg. 9787212058937
	 */
	@Getter
	@Setter
	private String isbn;

	/**
	 * ISBN 10位 eg. 7212058939
	 */
	@Getter
	@Setter
	private String isbn10;

	/**
	 * 主题词 eg. 名人-访问记-中国-现代
	 */
	@Getter
	@Setter
	private String keyword;

	/**
	 * 版次 eg. 1版
	 */
	@Getter
	@Setter
	private String edition;

	/**
	 * 印次
	 */
	@Getter
	@Setter
	private String impression;

	/**
	 * 正文语种
	 */
	@Getter
	@Setter
	private String language;

	/**
	 * 开本 eg. 23×18
	 */
	@Getter
	@Setter
	private String format;

	@Getter
	@Setter
	private String description;

	@Getter
	@Setter
	@UpdateTimestamp
	private Date updateTime;

	@Getter
	@Setter
	@Column(nullable = false, insertable = true, updatable = false)
	@CreationTimestamp
	private Date createTime;
}
