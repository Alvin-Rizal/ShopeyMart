package com.enigma.shopeymart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "m_product_price")
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "stock",columnDefinition = "int check(stock >0)")
    private Integer stock;
    @Column(name = "is_actice")
    private Boolean isActive;
    @Column(name = "price",columnDefinition = "bigint check(price > 0)")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "store_id") //penamaan dan beritahu database apakah dia foreign key atau tidak
    private Store store;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;
}
