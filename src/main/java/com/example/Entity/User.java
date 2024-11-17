package com.example.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "userIntlejidea")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "age", nullable = false)
    private Integer age;
    @Column(name = "email", nullable = false  , unique = true )
    private String email;
    @Column(name = "mobile", nullable = false , unique = true)
    private String mobile;



}
