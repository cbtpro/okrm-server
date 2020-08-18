/**
 * 
 */
package com.useful.person.core.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

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
public class ResponseData<T> implements Serializable {

	private static final long serialVersionUID = -6079487872936692537L;

	/**
	 * 返回代码 {@link com.useful.person.core.constants.ReturnCode}
	 */
	@Getter
	@Setter
	@JsonView(GeneralViews.IErrorView.class)
	private int code;

	/**
	 * 文字消息
	 */
	@Getter
	@Setter
	@JsonView(GeneralViews.IErrorView.class)
	private String content;

	/**
	 * 返回结果，如果有的话
	 */
	@Getter
	@Setter
	@JsonView(GeneralViews.INormalView.class)
	private T data;

}
