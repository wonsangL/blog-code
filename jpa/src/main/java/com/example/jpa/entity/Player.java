package com.example.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Player {
    @Id
    private int id;

    private String name;
}
