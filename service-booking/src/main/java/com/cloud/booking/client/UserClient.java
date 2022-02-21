package com.cloud.booking.client;

import com.example.multimodule.service.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-user",fallback = UserFallBackHystrix.class)
public interface UserClient {

    @GetMapping("/users/{id}")
    Response findById(@PathVariable("id") Long id);
}
