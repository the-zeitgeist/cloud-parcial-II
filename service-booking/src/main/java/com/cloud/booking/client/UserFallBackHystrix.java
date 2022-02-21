package com.cloud.booking.client;

import com.cloud.booking.modules.User;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFallBackHystrix implements UserClient{

    private final ResponseBuilder builder;

    @Override
    public Response findById(Long id) {
        return builder.success(new User());
    }
}
