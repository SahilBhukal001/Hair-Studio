package com.salon.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String fullName ;

    @NotBlank(message = "Username is mandatory")
    private String userName ;

    @Email
    @NotBlank(message = "Email is mandatory or its not a valid email")
    private String email ;

    @NotBlank(message = "Password is mandatory")
    private String password ;

    private String phone ;

    @NotBlank(message = "Role is mandatory")
    private String role ;

    private LocalDateTime createdAt ;

    private LocalDateTime updatedAt ;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
