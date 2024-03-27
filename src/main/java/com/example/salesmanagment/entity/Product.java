package com.example.salesmanagment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "available_quantity")
    private int availableQuantity;

    @Column(name = "price")
    private double price;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Sale> sales;
}
