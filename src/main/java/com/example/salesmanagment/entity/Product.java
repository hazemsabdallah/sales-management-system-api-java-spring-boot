package com.example.salesmanagment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "available_quantity")
    private int availableQuantity;

    @NotNull
    @Column(name = "price")
    private double price;

    @NotNull
    @Column(name = "creation_date")
    private Date creationDate;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Sale> sales;
}
