package com.cms.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@ToString
@Setter
@Entity
@Table(name = "ROLES")
@NoArgsConstructor
@AllArgsConstructor
public class Role extends CommonEntity {

    @Column(length = 150, name = "NAME", nullable = false)
    private String name;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="ROLE_AUTHORITIES",
            joinColumns = @JoinColumn(name="ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name="AUTHORITY_ID")
    )
    private List<Authority> authorities;

    public Role(Long id) {
        this.id = id;
    }
}

