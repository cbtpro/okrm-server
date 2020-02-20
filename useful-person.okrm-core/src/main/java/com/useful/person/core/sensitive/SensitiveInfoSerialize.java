/**
 * 
 */
package com.useful.person.core.sensitive;

import java.io.IOException;
import java.util.Objects;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.useful.person.core.annotation.SensitiveInfo;

/**
 * @author cbtpro
 *
 */
public class SensitiveInfoSerialize extends JsonSerializer<String> implements ContextualSerializer {

	private SensitiveType type;

	public SensitiveInfoSerialize() {
	}

	public SensitiveInfoSerialize(final SensitiveType type) {
		this.type = type;
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
			throws JsonMappingException {
		if (ObjectUtils.isEmpty(property)) {
			return prov.findNullValueSerializer(property);
		}
		if (Objects.equals(property.getType().getRawClass(), String.class)) {
			SensitiveInfo sensitiveInfo = property.getAnnotation(SensitiveInfo.class);
			if (ObjectUtils.isEmpty(sensitiveInfo)) {
				sensitiveInfo = property.getContextAnnotation(SensitiveInfo.class);
			}
			if (!ObjectUtils.isEmpty(sensitiveInfo)) {
				return new SensitiveInfoSerialize(sensitiveInfo.value());
			}
		}
		return prov.findValueSerializer(property.getType(), property);
	}

	@Override
	public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		switch (this.type) {
			case CHINESE_NAME: {
				gen.writeString(SensitiveInfoUtils.chineseName(value));
				break;
			}
			case MOBILE: {
				gen.writeString(SensitiveInfoUtils.mobile(value));
				break;
			}
			case PASSWORD: {
				gen.writeString(SensitiveInfoUtils.password(value));
				break;
			}
			case EMAIL: {
				gen.writeString(SensitiveInfoUtils.email(value));
				break;
			}
			case ID_CARD: {
				gen.writeString(SensitiveInfoUtils.identityCard(value));
				break;
			}
			case BANK_CARD: {
				gen.writeString(SensitiveInfoUtils.bankCard(value));
				break;
			}
			default:
				gen.writeString(value);
				break;
			}
	}

}
