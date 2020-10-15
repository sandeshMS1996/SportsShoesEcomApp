package com.sportshoes.ecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
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

    @CreationTimestamp
    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    @ColumnDefault("true")
    private boolean isActiveUser = true;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    @Column(name = "password", nullable = false)
    private String password;

    public Customers(long ID) {
        this.ID = ID;
    }

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }


}
