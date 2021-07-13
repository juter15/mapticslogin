package kr.co.maptics.mapticslogin.entity.support;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class BooleanToYnConverter implements AttributeConverter<Boolean, Character> {

	@Override
	public Character convertToDatabaseColumn(Boolean attribute) {
		return (attribute != null && attribute) ? 'Y' : 'N';
	}

	@Override
	public Boolean convertToEntityAttribute(Character dbData) {
		return 'Y' == dbData;
	}
}
