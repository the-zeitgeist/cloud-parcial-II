package com.cloud.booking.client;

import com.example.multimodule.service.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-movie",fallback = MovieFallBackHystrix.class)
public interface MovieClient {

    @GetMapping("/movies/{id}")
    Response findByID(@PathVariable Long id);
}
