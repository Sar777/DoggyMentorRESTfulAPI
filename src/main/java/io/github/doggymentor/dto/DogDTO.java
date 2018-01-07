package io.github.doggymentor.dto;

import java.io.Serializable;

public class DogDTO implements Serializable {

    private static final long serialVersionUID = -7312585266247302433L;

    private String name;

    private String imageBase64;

    public DogDTO() {
    }

    public String getName() {
        return name;
    }

    public String getImageBase64() {
        return imageBase64;
    }
}
