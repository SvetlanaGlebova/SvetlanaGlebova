package com.example.docdelivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rentor")
public class Rentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    protected int id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_ready")
    private boolean isReady;
}
