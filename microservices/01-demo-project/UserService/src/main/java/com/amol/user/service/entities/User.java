package com.amol.user.service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME", length = 20)
    private String userName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT")
    private String about;

    @Column(name = "DOB")
    private String dateOfBirth;

    @Transient // @Transient - annotation used to NOT STORE FIELD IN DATABASE
    private List<Rating> ratings = new ArrayList<>();
}
