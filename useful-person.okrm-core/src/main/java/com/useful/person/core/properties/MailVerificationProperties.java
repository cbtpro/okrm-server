package com.useful.person.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peter
 *
 */
public class MailVerificationProperties {

    @Getter
    @Setter
    private int expireIn = 60 * 60 * 24 * 2;

}
