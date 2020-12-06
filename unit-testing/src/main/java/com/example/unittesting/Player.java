package com.example.unittesting;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private BigDecimal budget;

    @Enumerated(EnumType.STRING)
    @Column
    private City city;
}
