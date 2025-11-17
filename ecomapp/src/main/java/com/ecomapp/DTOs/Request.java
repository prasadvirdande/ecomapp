package com.ecomapp.DTOs;

import lombok.Data;

@Data
public class Request {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phone;
    private AddressDTO address;

}
