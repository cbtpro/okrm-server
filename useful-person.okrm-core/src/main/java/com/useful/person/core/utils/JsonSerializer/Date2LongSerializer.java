package com.useful.person.core.utils.JsonSerializer;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class Date2LongSerializer<T> extends JsonSerializer<T> {

	@Override
	public void serialize(T value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (value instanceof Date) {
			Date date = (Date) value;
			gen.writeNumber(date.getTime());
		} else if (value instanceof Timestamp) {
			Timestamp ts = (Timestamp) value;
			gen.writeNumber(ts.getTime());
		}
	}

}
