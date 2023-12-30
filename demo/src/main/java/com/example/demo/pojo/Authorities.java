package com.example.demo.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long userId;
}
