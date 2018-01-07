package io.github.doggymentor.converter.factory;

import io.github.doggymentor.converter.dto.DogDTOConverter;
import io.github.doggymentor.converter.dto.UserDTOConverter;
import io.github.doggymentor.dto.DogDTO;
import io.github.doggymentor.dto.UserDTO;
import io.github.doggymentor.converter.dto.UserDTOConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConverterFactory {

    private Map<Object, Converter> converters;

    public ConverterFactory() {

    }

    @PostConstruct
    public void init() {
        converters = new HashMap<>();
        converters.put(UserDTO.class, new UserDTOConverter());
        converters.put(DogDTO.class, new DogDTOConverter());
    }

    public Converter getConverter(final Object type) {
        return converters.get(type);
    }
}
