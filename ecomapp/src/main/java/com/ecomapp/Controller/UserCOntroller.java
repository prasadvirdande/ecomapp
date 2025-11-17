package com.ecomapp.Controller;


import com.ecomapp.DTOs.Request;
import com.ecomapp.DTOs.Response;
import com.ecomapp.Model.User;
import com.ecomapp.Service.USerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserCOntroller {


    private final USerService uSerService;

    @GetMapping("/all")
    public ResponseEntity<List<Response>> getallUser() {
        return ResponseEntity.ok(uSerService.getallUser());
    }

    @PostMapping("/users")
    public ResponseEntity<Response> createUser(@RequestBody Request user) {
        return ResponseEntity.ok(uSerService.createUser(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        uSerService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
