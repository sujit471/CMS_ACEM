package com.cms.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "SUBJECTS")
public class Subject extends CommonEntity {

    @Column(name="NAME", length=100, nullable = false, unique = true)
    private String name;
    @Column(name="CODE", length=100, nullable = false, unique = true)
    private String code;

}
