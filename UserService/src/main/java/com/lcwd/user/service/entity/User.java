package com.lcwd.user.service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="micro-users")
public class User {
    @Id
    @Column(name="ID")
    private String userId;

    @Column(name="NAME",length = 30)
    private String name;
    @Column(name="EMAIL")
    private String email;

    @Column(name="ABOUT")
    private String about;

    // we use transient for not saving field in database
    @Transient
    private List<Rating> ratings=new ArrayList<>();


}
