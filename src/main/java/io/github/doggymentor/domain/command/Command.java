package io.github.doggymentor.domain.command;

import io.github.doggymentor.domain.common.DbModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "commands")
public class Command extends DbModel implements Serializable {

    private static final long serialVersionUID = 1549390791769769508L;

    @Id
    private String id;

    private String name;

    private String short_description;

    private String full_description;

    private String learn;

    private String advice;

    private Integer type;

    private String imageBase64;

    private boolean isPremium;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return short_description;
    }

    public String getFullDescription() {
        return full_description;
    }

    public String getLearn() {
        return learn;
    }

    public String getAdvice() {
        return advice;
    }

    public Integer getType() {
        return type;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public boolean isPremium() {
        return isPremium;
    }
}
