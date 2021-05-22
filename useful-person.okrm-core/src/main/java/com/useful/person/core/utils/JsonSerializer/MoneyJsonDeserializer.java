package com.useful.person.core.utils.JsonSerializer;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.joda.money.Money;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class MoneyJsonDeserializer extends JsonDeserializer<Money> {

    /**
     * 反序列化 如果字符串中包含非数字，但又不能被解析则会抛出异常IllegalArgumentException
     */
    @Override
    public Money deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String text = p.getText();
        Money money = null;
        if (StringUtils.isNotBlank(text)) {
            money = Money.parse(text);
        }
        return money;
    }

}
