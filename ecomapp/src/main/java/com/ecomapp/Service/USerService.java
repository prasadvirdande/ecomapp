package com.ecomapp.Service;

import com.ecomapp.DTOs.Request;
import com.ecomapp.DTOs.Response;
import com.ecomapp.Model.User;

import java.util.List;

public interface USerService {
    List<Response> getallUser();

    Response createUser(Request user);

    void deleteUser(Long id);
}
