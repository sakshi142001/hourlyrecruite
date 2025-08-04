package com.hourlyrecruite.hourlyrecruite.model;

import jakarta.persistence.*;
import com.hourlyrecruite.hourlyrecruite.security.Role;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String username;
   
     @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // --- Constructors ---
    public User() {
    }

    public User(Long id, String username, String email, String password, Role role) {
        this.id = id;
        this.username = username;
        this.email=email;
        this.password = password;
        this.role = role;
    }

    // --- Getters ---
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    // --- Setters ---
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // --- toString (optional) ---
    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", role=" + role +
               '}';
    }


    
}
