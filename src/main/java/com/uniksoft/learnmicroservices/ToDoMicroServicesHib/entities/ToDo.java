package com.uniksoft.learnmicroservices.ToDoMicroServicesHib.entities;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "todos")
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "DESCRIPTION")
    @NotNull @NotEmpty @NotBlank
    private String description;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "PRIORITY")
    @NotNull @NotEmpty @NotBlank
    private String priority;

    @Column(name = "FKUSER")
    private String fkUser;
}
