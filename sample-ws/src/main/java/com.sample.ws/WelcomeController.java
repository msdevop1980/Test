package com.sample.ws;

import org.sample.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class WelcomeController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> listAllUsers() {

        return new ResponseEntity<String>("Greetings from Welcome Controller", HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    User getUser(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user=new User();
        user.setName("mallappa");
        user.setId(1);
       return user;
    }
}


