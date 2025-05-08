package com.security.security.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "master_users")
@Data
public class MasterUsers {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "user_name", nullable = false, length = 45)
        private String userName;

        @Column(name = "user_email", nullable = false, length = 45, unique = true)
        private String userEmail;

        @Column(name = "phone_number", nullable = false, length = 45, unique = true)
        private String phoneNumber;

        @Column(name = "is_active")
        private Boolean isActive;

        @Column(name = "created_at", length = 45)
        private String createdAt;

        @Column(name = "failed_attempts")
        private Integer failedAttempts;

        @Column(name = "password", length = 255)
        private String password;

        @Column(name = "updated_at", length = 45)
        private String updatedAt;
}
