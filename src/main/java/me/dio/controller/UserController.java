package me.dio.controller;


import me.dio.controller.exception.CNPJInvalidoException;
import me.dio.controller.exception.CPFInvalidoException;
import me.dio.domain.model.User;
import me.dio.domain.model.UserType;
import me.dio.domain.model.ValidaCNPJ;
import me.dio.domain.model.ValidaCPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final me.dio.service.UserService userService;

    public UserController(me.dio.service.UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<me.dio.domain.model.User> findById(@PathVariable Long id){
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate) throws Exception {
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        if(userToCreate.getUserType() == UserType.NATURAL){
            if(!ValidaCPF.isCPF(userToCreate.getClient().getDocument())){
                throw new CPFInvalidoException();
            }
            return ResponseEntity.created(location).body(userCreated);
        }

        if(userToCreate.getUserType() == UserType.LEGAL){
            if(!ValidaCNPJ.isCNPJ(userToCreate.getClient().getDocument())){
                throw new CNPJInvalidoException();
            }
            return ResponseEntity.created(location).body(userCreated);
        }
        return null;
    }
}
