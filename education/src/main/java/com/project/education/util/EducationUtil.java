package com.project.education.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EducationUtil {
	private static final ModelMapper modelMapper = new ModelMapper();

	private EducationUtil() {
	}

	public static <S, D> D map(final S source, Class<D> destinationType) {
		return modelMapper.map(source, destinationType);
	}

	public static <S, D> D map(final S source, D destination) {
		modelMapper.map(source, destination);
		return destination;
	}

}
