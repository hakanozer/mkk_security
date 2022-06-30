package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    private String name;

    @NotNull(message = "EMail Notnull")
    @NotEmpty(message = "EMail Empty")
    @Email(message = "EMail format fail")
    private String email;

    @NotEmpty(message = "Password NotEmpty")
    @Length(message = "Pasword Min 3 Max 13", min = 3, max = 13)
    @NotNull(message = "Password  NotNull")
    private String password;

    private String remember;

}
