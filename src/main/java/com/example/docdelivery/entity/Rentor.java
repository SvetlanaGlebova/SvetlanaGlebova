package com.example.docdelivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rentors")
public class Rentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    protected int id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_ready")
    private boolean isReady;

    @Column(name = "address")
    private String address;

    @ManyToOne
    Tenant tenant;

    public Rentor() {               /// todo ap remove
        isReady = true;
    }
}
