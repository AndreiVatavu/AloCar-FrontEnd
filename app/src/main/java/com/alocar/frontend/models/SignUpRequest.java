package com.alocar.frontend.models;

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

    public static class SignUpRequestBuilder {
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String phoneNumber;
        private String password;

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

        public SignUpRequest build() {
            SignUpRequest signUpRequest = new SignUpRequest();
            signUpRequest.setFirstName(this.firstName);
            signUpRequest.setLastName(this.lastName);
            signUpRequest.setEmailAddress(this.emailAddress);
            signUpRequest.setPhoneNumber(this.phoneNumber);
            signUpRequest.setPassword(this.password);
            return signUpRequest;
        }
    }
}
