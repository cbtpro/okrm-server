package com.useful.person.core.utils.JsonSerializer;

import java.io.IOException;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MoneyJsonSerializer extends JsonSerializer<Money> {

    /**
     * 序列化
     */
    @Override
    public void serialize(Money value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(Money.of(CurrencyUnit.of("CNY"), value.getAmountMinorLong() / 100.0).toString());
//		gen.writeString(Long.toString(value.getAmountMinorLong()));
    }

}
