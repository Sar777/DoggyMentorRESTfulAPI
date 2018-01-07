package io.github.doggymentor.converter.dto;

import io.github.doggymentor.domain.dog.Dog;
import io.github.doggymentor.dto.DogDTO;
import org.springframework.core.convert.converter.Converter;

public class DogDTOConverter implements Converter<DogDTO, Dog> {

    @Override
    public Dog convert(final DogDTO dto) {
        final Dog dog = new Dog();
        dog.setName(dto.getName());
        dog.setImageBase64(dto.getImageBase64());
        return dog;
    }
}
