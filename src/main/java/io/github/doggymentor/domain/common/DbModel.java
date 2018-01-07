package io.github.doggymentor.domain.common;

import java.io.Serializable;

public class DbModel implements Serializable {

    private static final long serialVersionUID = 8571261118900116242L;

    private String createdAt;
    private String updatedAt;

    public DbModel() {
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
