package com.example.demo.po;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Primary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumns;

@Entity
@Data
@Accessors
public class Authorities {
    @Id
    private String username;

    private String authority;
}
