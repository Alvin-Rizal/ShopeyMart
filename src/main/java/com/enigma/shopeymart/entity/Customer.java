package com.enigma.shopeymart.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "m_customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name",nullable = false,length = 50)
    private String name;

    @Column(name = "email",unique = true,nullable = false,length = 60)
    private String email;

    @Column(name = "address",nullable = false,length = 100)
    private String address;

    @Column(name = "mobile_phone",unique = true,nullable = false,length = 30)
    private String mobilePhone;

    //id
    //name
    //address
    //mobilePhone -> unique
    //email -> unique
}
