package com.useful.person.browser.support;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
public class SimpleResponse {

    public SimpleResponse(int code, Object content) {
        this.code = code;
        this.content = content;
        this.data = null;
    }

    public SimpleResponse(int code, Object content, Object data) {
        this.code = code;
        this.content = content;
        this.data = data;
    }

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private Object content;

    @Getter
    @Setter
    private Object data;

}
