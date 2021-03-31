/**
 * 
 */
package com.useful.person.core.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author peter
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("VO响应数据")
public class ResponseData<T> implements Serializable {

	private static final long serialVersionUID = -6079487872936692537L;

	/**
	 * 返回代码 {@link com.useful.person.core.constants.ReturnCode}
	 */
	@Getter
	@Setter
	@JsonView(GeneralViews.IErrorView.class)
	@ApiModelProperty(value = "返回代码", example = "0")
	private int code;

	@Getter
	@Setter
	@JsonView(GeneralViews.IErrorView.class)
	@ApiModelProperty(value = "文字消息")
	private String content;

	/**
	 * 返回结果，如果有的话
	 */
	@Getter
	@Setter
	@JsonView(GeneralViews.INormalView.class)
	@ApiModelProperty(value = "返回结果")
	private T data;

}
