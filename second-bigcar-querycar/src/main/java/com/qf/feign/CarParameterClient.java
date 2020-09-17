package com.qf.feign;

import com.qf.pojo.CarParameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(serviceId = "second-bigcar-carparameter")
public interface CarParameterClient {

    @GetMapping("/CarParameter/getParameter/{cb_id}")
    public CarParameter getParameter(@PathVariable("cb_id") Integer cb_id);
}
