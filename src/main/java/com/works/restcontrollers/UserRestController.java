package com.works.restcontrollers;

import com.works.entities.Admin;
import com.works.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("/user")
public class UserRestController {

    final UserService uService;
    public UserRestController(UserService uService) {
        this.uService = uService;
    }


    @PostMapping("/login")
    public ResponseEntity login( @Valid @RequestBody Admin user) {
        Map<String, Object> hm = new LinkedHashMap<>();
        boolean status = uService.userLogin(user.getEmail(), user.getPassword() );
        if ( status ) {
            hm.put("status", true);
            hm.put("user", user);
        }else {
            hm.put("status", false);
            hm.put("user", user);
        }
        return new ResponseEntity( hm , HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity register( @Valid @RequestBody Admin user) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("user", uService.register( user ));
        return new ResponseEntity( hm , HttpStatus.OK);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle( MethodArgumentNotValidException ex ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", false);
        List<ObjectError> errorList = ex.getAllErrors();
        List<Map<String, String>> ls = new ArrayList<>();
        for( ObjectError item : errorList ) {
            String filed = ((FieldError) item).getField();
            String message = item.getDefaultMessage();
            Map<String, String> hmx = new HashMap<>();
            hmx.put("filed", filed);
            hmx.put("message", message);
            ls.add(hmx);
        }
        hm.put("errors", ls );
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

}
