package com.salon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String fullName ;

    private String userName ;

    private String password ;

    private String email ;

    private String phone ;

    private String role ;
}
