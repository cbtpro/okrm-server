/**
 * 
 */
package com.useful.person.core.constants;

import lombok.Getter;
import lombok.Setter;

/**
 * @author peter
 *
 */
public enum UserAction {

    SIGNIN(1, "注册用户"), SIGNUP(2, "登陆用户"), SIGNOUT(3, "登出用户"), UPDATE_USERNAME(4, "更新用户名"), UPDATE_PASSWORD(5, "更新密码"),
    UPDATE_AVATAR(6, "更新头像"), UPDATE_NICKNAME(7, "更新昵称"), UPDATE_MOBILE(8, "更新手机号"), UPDATE_EMAIL(9, "更新电子邮箱"),
    UPDATE_BIRTHDAY(10, "更新生日"), UPDATE_IDENTITY_CARD(11, "更新身份证信息"),

    ADD_ROLE(12, "添加角色"), UPDATE_ROLE(13, "修改角色"), DEL_ROLE(14, "删除角色"),

    LOGOFF(0, "注销用户"); // 彻底注销用户

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String type;

    private UserAction(int code, String type) {
        this.code = code;
        this.type = type;
    }
}
