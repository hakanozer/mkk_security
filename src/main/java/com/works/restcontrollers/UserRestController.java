package com.works.restcontrollers;

import com.works.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserRestController {


    @PostMapping("/login")
    public ResponseEntity login( @Valid User user) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("user", user);
        return new ResponseEntity( hm , HttpStatus.OK);
    }


}
