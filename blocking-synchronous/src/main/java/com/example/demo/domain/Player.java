package com.example.demo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "player")
public class Player {
    @Id
    private int id;

    @Column
    private String name;

    @Column
    private int age;
}
