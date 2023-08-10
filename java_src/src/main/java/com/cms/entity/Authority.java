package com.cms.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "AUTHORITIES")
@NoArgsConstructor
@AllArgsConstructor
public class Authority extends CommonEntity{

    @Column(length = 150, name = "NAME", nullable = false)
    private String name;
}
