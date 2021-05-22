/**
 * 通用异常
 */
package com.useful.person.core.authentication.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author cbtpro
 *
 */
public class GeneralException extends RuntimeException {

    private static final long serialVersionUID = 4574252497967225026L;

    @Getter
    @Setter
    private Object attribute;

    /**
     * 通用异常的构造器
     * 
     * @param attribute 通用属性
     * @param msg       异常消息
     */
    public GeneralException(Object attribute, String msg) {
        super(msg);
        this.attribute = attribute;
    }
}
