/**
 * 
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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.useful.person.core.utils.JsonSerializer.Date2LongSerializer;
import com.useful.person.core.utils.JsonSerializer.MoneyJsonDeserializer;
import com.useful.person.core.utils.JsonSerializer.MoneyJsonSerializer;

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
@Table(name = "t_book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
@ApiModel("书籍模型")
public class Book {

    @Id
    @Getter
    @Setter
    @GeneratedValue(generator = "uuid2")
    @ApiModelProperty(value = "全局唯一标识")
    private String uuid;

    @Getter
    @Setter
    @ApiModelProperty(value = "书名", required = true, example = "有理想就有疼痛")
    private String title;

    @Getter
    @Setter
    @ApiModelProperty(value = "副书名", example = "中国当代文化名人访谈录")
    private String subtitle;

    @Getter
    @Setter
    @ApiModelProperty(value = "图片", example = "http://api.jisuapi.com/isbn/upload/1/1.jpg")
    private String pic;

    @Getter
    @Setter
    @ApiModelProperty(value = "作者", example = "高晓春")
    private String author;

    @Getter
    @Setter
    @ApiModelProperty(value = "摘要", example = "《有理想就有疼痛:中国当代文化名人访谈录》是一份关于当代中国文化的最真实底稿，收录了高晓春与白先勇、冯骥才、余华、莫言、余秋雨、陈忠实等20位当代中国文化大家的对话。通过近距离的访谈，展现了这些文化大家多彩的内心世界，也阐释了他们对生命、艺术、财富及文化的理解。")
    private String summary;

    @Getter
    @Setter
    @ApiModelProperty(value = "出版社")
    private String publisher;

    @Getter
    @Setter
    @ApiModelProperty(value = "出版地")
    private String pubplace;

    @Getter
    @Setter
    @ApiModelProperty(value = "出版时间")
    private Date pubdate;

    @Getter
    @Setter
    @ApiModelProperty(value = "页数")
    private Integer page;

    @Getter
    @Setter
    @ApiModelProperty(value = "价格", example = "CNY 20.01")
    @JsonDeserialize(using = MoneyJsonDeserializer.class)
    @JsonSerialize(using = MoneyJsonSerializer.class)
    private String price;

    @Getter
    @Setter
    @ApiModelProperty(value = "装帧方式")
    private String binding;

    @Getter
    @Setter
    @ApiModelProperty(value = "ISBN", example = "9787212058937")
    private String isbn;

    @Getter
    @Setter
    @ApiModelProperty(value = "ISBN 10位", example = "721205893")
    private String isbn10;

    @Getter
    @Setter
    @ApiModelProperty(value = "主题词", example = "名人-访问记-中国-现代")
    private String keyword;

    @Getter
    @Setter
    @ApiModelProperty(value = "版次", example = "1")
    private Integer edition;

    @Getter
    @Setter
    @ApiModelProperty(value = "印次", example = "1")
    private Integer impression;

    @Getter
    @Setter
    @ApiModelProperty(value = "正文语种")
    private String language;

    @Getter
    @Setter
    @ApiModelProperty(value = "开本", example = "23×18")
    private String format;

    @Getter
    @Setter
    @Column(length = 5000)
    @ApiModelProperty(value = "描述", example = "这是一段描述")
    private String description;

    @Getter
    @Setter
    @UpdateTimestamp
    @ApiModelProperty(value = "更新时间，自动获取")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    @Getter
    @Setter
    @Column(nullable = false, insertable = true, updatable = false)
    @CreationTimestamp
    @JsonSerialize(using = Date2LongSerializer.class)
    @ApiModelProperty(value = "创建时间自动获取")
    private Date createTime;
}
