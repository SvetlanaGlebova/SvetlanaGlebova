package com.example.docdelivery.entity;

import jakarta.persistence.*;
import lombok.Builder;
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
    @Column(name = "id")
    protected int id;

    @Column
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "is_ready")
    private boolean isReady;

    @Column(name = "address")
    private String address;

    @ManyToOne
    Tenant tenant;

    @OneToOne
    ApplicationUser user;

    public Rentor() {               /// todo ap remove
        isReady = false;
    }
}
