package org.dellmdq.blogdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String name;//username
    private String password;
    @Transient
    private String matchingPassword;
    private String firstName;
    private String lastName;
    private String email;
    private String verificationCode;
    private boolean enabled;

}
