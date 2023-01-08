package com.teleport.searchengine.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "orders", indexes = @Index(name = "id_index", columnList = "id"))
@Table(name = "orders")
public class UrbanArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String salary;
    private String score;
    private String picture;
}
