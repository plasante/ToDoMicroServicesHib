package com.uniksoft.learnmicroservices.ToDoMicroServicesHib.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "EMAIL", nullable = false)
    @NotNull @NotBlank @NotEmpty
    private String email;

    @Column(name = "NAME")
    @NotNull @NotBlank @NotEmpty
    private String name;

    @Column(name = "PASSWORD")
    @NotNull @NotBlank @NotEmpty
    private String password;
}
