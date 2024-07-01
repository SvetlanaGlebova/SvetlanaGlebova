package com.example.docdelivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    protected int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "tenants")
    private Set<Rentor> rentors;
}

