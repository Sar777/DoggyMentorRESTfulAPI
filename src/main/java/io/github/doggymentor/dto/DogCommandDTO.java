package io.github.doggymentor.dto;

import io.github.doggymentor.domain.command.CommandType;
import io.github.doggymentor.domain.dog.CommandStatus;

public class DogCommandDTO {

    private String id;

    private CommandStatus status;

    private String name;

    private String short_description;

    private String full_description;

    private String advice;

    private CommandType type;

    private String imageBase64;

    private boolean isPremium;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CommandStatus getStatus() {
        return status;
    }

    public void setStatus(CommandStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return short_description;
    }

    public void setShortDescription(String description) {
        this.short_description = description;
    }

    public String getFullDescription() {
        return full_description;
    }

    public void setFullDescription(String description) {
        this.full_description = description;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }
}
