package com.qf.controller;

import com.qf.pojo.BaseResult;
import com.qf.pojo.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/loginUser")
    public String loginUser(@RequestBody User user){

        return userService.loginUser(user);
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody Map map){
        Object email = map.get("email");
        return userService.findByUserMail(email.toString());
    }

    @GetMapping("/findUser")
    public BaseResult findUser(HttpServletRequest request){
        return userService.findUser(request);

    }

}
