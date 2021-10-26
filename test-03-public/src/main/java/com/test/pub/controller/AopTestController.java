package com.test.pub.controller;

import com.test.pub.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aopTest")
public class AopTestController {

    @RequestMapping(value = "/testAspect", method = RequestMethod.GET)
    public User test(@RequestParam("name") String name) {
        User user = new User();
        user.setName("a");
        user.setAge(12);
        return user;
    }

}
