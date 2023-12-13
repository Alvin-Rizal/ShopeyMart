package com.enigma.shopeymart.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_user_credential")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class UserCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

   // @Column(name = "username",nullable = false,length = 50)
    private String username;

    //@Column(name = "password",nullable = false,length = 60)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
