package com.sportshoes.ecom.entity.JSONMappers;

import com.sportshoes.ecom.entity.Customers;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class customerMapper {

    @Size(min = 6, max = 30, message = "Invalid email")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email address")
    private String emailId;

    @Size(min = 5, max = 20, message = "size(5-20")
    private String fname;

    @Size(min = 2, message = "minimun length 2")
    private String lname;

    @Size(min = 5, max = 30)
    private String address;

    @Digits(fraction = 0, integer = 3)
    private int age;

    @NotNull
    private boolean isActiveUser;

    @NotNull
    @Size(min = 5, max = 20, message = "min length 5, max length 20")
    private String password;

    private Customers.Role role;

    public Customers mapToCustomer() {
        return Customers.builder()
                .emailId(this.emailId)
                .fname(this.fname)
                .lname(this.lname)
                .age(this.age)
                .isActiveUser(this.isActiveUser)
                .password(this.password)
                .role(this.role)
                .address(this.address)
                .build();
    }


}
