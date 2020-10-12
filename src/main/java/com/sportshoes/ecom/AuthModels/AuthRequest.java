package com.sportshoes.ecom.AuthModels;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthRequest {
    private  String emailId;
    private  String password;
}
