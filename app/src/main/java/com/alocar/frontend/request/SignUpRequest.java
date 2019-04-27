package com.alocar.frontend.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SignUpRequest implements Serializable {
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("emailAddress")
    private String emailAddress;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("password")
    private String password;
    @JsonProperty("body")
    private String body;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("model")
    private String model;
    @JsonProperty("color")
    private String color;
    @JsonProperty("manufacturingYear")
    private String manufacturingYear;
    @JsonProperty("licensePlate")
    private String licensePlate;

    public SignUpRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(String manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public static class SignUpRequestBuilder {
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String phoneNumber;
        private String password;
        private String body;
        private String brand;
        private String model;
        private String color;
        private String manufacturingYear;
        private String licensePlate;

        public SignUpRequestBuilder() {

        }

        public SignUpRequestBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public SignUpRequestBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public SignUpRequestBuilder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public SignUpRequestBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public SignUpRequestBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public SignUpRequestBuilder withBody(String body) {
            this.body = body;
            return this;
        }

        public SignUpRequestBuilder withBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public SignUpRequestBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public SignUpRequestBuilder withColor(String color) {
            this.color = color;
            return this;
        }

        public SignUpRequestBuilder withMManufacturingYear(String manufacturingYear) {
            this.manufacturingYear = manufacturingYear;
            return this;
        }

        public SignUpRequestBuilder withLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public SignUpRequest build() {
            SignUpRequest signUpRequest = new SignUpRequest();
            signUpRequest.setFirstName(this.firstName);
            signUpRequest.setLastName(this.lastName);
            signUpRequest.setEmailAddress(this.emailAddress);
            signUpRequest.setPhoneNumber(this.phoneNumber);
            signUpRequest.setPassword(this.password);
            signUpRequest.setBody(this.body);
            signUpRequest.setBrand(this.brand);
            signUpRequest.setModel(this.model);
            signUpRequest.setColor(this.color);
            signUpRequest.setManufacturingYear(this.manufacturingYear);
            signUpRequest.setLicensePlate(this.licensePlate);
            return signUpRequest;
        }
    }
}
