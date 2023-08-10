package com.cms.entity;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User extends CommonEntity {

    @Embedded
    private Name name;

    @Column(name="EMAIL", length=100, nullable = false, unique = true)
    private String email;

    @Column(name="CONTACT_NO", length=100, nullable = false, unique = true)
    private String contactNo;

    public User(Long id) {
        this.id = id;
    }
}
