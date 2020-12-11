package com.restapi.book.dto;

import javax.validation.constraints.NotBlank;

public class AuthorDto {

    @NotBlank(message = "Author name is mandatory")
    private String name;
    @NotBlank(message = "Author's date of birth is mandatory")
    private String dob;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
