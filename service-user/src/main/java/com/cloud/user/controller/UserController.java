package com.cloud.user.controller;

import com.cloud.user.dto.UserDto;
import com.cloud.user.entities.User;
import com.cloud.user.services.UserService;
import com.example.multimodule.service.MyService;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ResponseBuilder builder;
    private final MyService myService;
    private final ModelMapper modelMapper;

    @PostMapping
    public Response save(@Valid @RequestBody UserDto userDto, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(myService.formatMessage((result)));
        }

        User user = modelMapper.map(userDto,User.class);
        userService.save(user);
        UserDto userDtoRespuesta = modelMapper.map(user,UserDto.class);
        return builder.success(userDtoRespuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable("id") Long id){
        User user = userService.findById(id);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        userService.delete(user);

        UserDto userDto = modelMapper.map(user,UserDto.class);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<UserDto> userDtos = users.stream()
                .map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        User user = userService.findById(id);
        if(user==null){
            return builder.failed(null);
        }

        UserDto userDto = modelMapper.map(user,UserDto.class);

        return builder.success(userDto);
    }
}
