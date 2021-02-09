package com.marcopololearning.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserView {

    private String id;
    private String username;
    private String name;
    private String lastname;
    private UserRole role;

    public enum UserRole {
        USER, ADMIN
    }
}
