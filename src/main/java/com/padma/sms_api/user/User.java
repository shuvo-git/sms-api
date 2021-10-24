package com.padma.sms_api.user;

import lombok.*;

import javax.annotation.Generated;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column
    private String password;
}
