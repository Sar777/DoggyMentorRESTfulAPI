package io.github.doggymentor.domain.dog;

import io.github.doggymentor.domain.common.DbModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "dogs")
public class Dog extends DbModel implements Serializable {

    private static final long serialVersionUID = -3072542242869467942L;

    @Id
    private String id;

    private String ownerId;

    private String name;

    private String imageBase64;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64a) {
        this.imageBase64 = imageBase64a;
    }
}
