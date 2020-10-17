package com.sportshoes.ecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

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
@Where(clause = "is_active_user=true")
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @Column(name = "is_active_user")
    private boolean isActiveUser;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    public Customers(long ID) {
        this.ID = ID;
    }

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    @PrePersist
    private void activateUser() {
        this.isActiveUser = true;
    }


}
