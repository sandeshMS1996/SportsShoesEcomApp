package com.sportshoes.ecom.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long ID;

    @Column(name = "emailid", unique = true, length = 50, nullable = false)
    private String emailId;

    @Column(name = "fname", nullable = false, length = 20)
    private String fname;


    @Column(name = "lname", nullable = false, length = 30)
    private String lname;


    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "age", nullable = false, length = 3)
    private int age;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_added")
    private Date dateAdded= new Date();

    @Enumerated(EnumType.STRING)
    @ColumnDefault("USER")
    @Column(nullable = false)
    private Role role = Role.USER;

    @Column
    @ColumnDefault("true")
    private boolean isActiveUser = true;

    @Column(name = "passowrd", nullable = false)
    private String password;

    public enum Role {
        USER, ADMIN
    }
}
