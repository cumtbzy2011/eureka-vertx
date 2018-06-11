package edu.bian.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("server")
public interface TestFeign {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    String getServer();
}
