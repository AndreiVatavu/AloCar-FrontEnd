package com.alocar.frontend.recycleview;

import java.io.Serializable;

/**
 * Created by ravi on 16/11/17.
 */

public class Contact implements Serializable {
    private String userId;
    private String name;
    private String licencePlate;
    private String image;

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
