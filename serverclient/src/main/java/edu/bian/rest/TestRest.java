package edu.bian.rest;

import edu.bian.feign.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestRest {

    @Autowired
    TestFeign testFeign;

    @GetMapping
    public Object test() {
        String server = testFeign.getServer();
        return server;
    }
}
