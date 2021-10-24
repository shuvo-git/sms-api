package com.padma.sms_api.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.io.Serializable;

@EnableJpaAuditing
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class SmsLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String phone;

    @Column
    private String message;

    @Column
    private String created_by;

    @Column
    private String status;
}
