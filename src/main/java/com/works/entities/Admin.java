package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
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

    @Column(length = 300)
    @NotEmpty(message = "Password NotEmpty")
    @NotNull(message = "Password  NotNull")
    private String password;

    private String remember;

}
