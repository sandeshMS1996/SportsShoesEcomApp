package com.sportshoes.ecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_Active=true")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @Size(min = 5, max = 20, message = "size should be 5-20 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_Active", nullable = false)
    private boolean activeCategory;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    Customers admin;

    public Category(long ID) {
        this.ID = ID;
    }

    public Category(Long adminID, String name) {
        this.admin = new Customers(adminID);
        this.name =  name;
    }

    @PrePersist
    private void activateCategory() {
        this.activeCategory = true;
    }
}
