package com.example.demo.po;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Primary;

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
