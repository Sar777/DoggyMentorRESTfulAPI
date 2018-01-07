package io.github.doggymentor.converter;

import io.github.doggymentor.converter.factory.ConverterFactory;
import io.github.doggymentor.domain.dog.Dog;
import io.github.doggymentor.dto.DogDTO;
import io.github.doggymentor.dto.UserDTO;
import io.github.doggymentor.domain.user.User;
import io.github.doggymentor.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterFacade {

    private final ConverterFactory converterFactory;

    @Autowired
    public ConverterFacade(final ConverterFactory converterFactory) {
        this.converterFactory = converterFactory;
    }

    public User convert(final UserDTO dto) {
        return (User) converterFactory.getConverter(dto.getClass()).convert(dto);
    }

    public Dog convert(final DogDTO dto) {
        return (Dog) converterFactory.getConverter(dto.getClass()).convert(dto);
    }
}
