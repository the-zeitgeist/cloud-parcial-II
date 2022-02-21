package com.cloud.booking.controller;

import com.cloud.booking.dto.BookingDto;
import com.cloud.booking.entities.Booking;
import com.cloud.booking.services.BookingService;
import com.example.multimodule.service.MyService;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final ResponseBuilder builder;
    private final MyService myService;
    private final ModelMapper modelMapper;

    @PostMapping
    public Response save(@Valid @RequestBody BookingDto bookingDto, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(myService.formatMessage((result)));
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Booking booking = modelMapper.map(bookingDto,Booking.class);
        bookingService.save(booking);
        BookingDto bookingDtoRespuesta = modelMapper.map(booking,BookingDto.class);

        return builder.success(bookingDtoRespuesta);
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> findAll(){
        List<Booking> bookings = bookingService.findAll();
        if(bookings.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<BookingDto> bookingDtos = bookings.stream()
                .map(booking -> modelMapper.map(booking,BookingDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(bookingDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> findByID(@PathVariable Long id){
        Booking booking = bookingService.findById(id);
        if(booking==null){
            return ResponseEntity.notFound().build();
        }

        BookingDto bookingDtoRespuesta = modelMapper.map(booking,BookingDto.class);

        return ResponseEntity.ok(bookingDtoRespuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookingDto> delete(@PathVariable("id") Long id){
        Booking booking = bookingService.findById(id);
        if(booking==null){
            return ResponseEntity.notFound().build();
        }

        bookingService.delete(booking);

        BookingDto bookingDtoRespuesta = modelMapper.map(booking,BookingDto.class);

        return ResponseEntity.ok(bookingDtoRespuesta);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<BookingDto> findByUserID(@PathVariable Long userId){
        Booking booking = bookingService.findByUserId(userId);
        if(booking==null){
            return ResponseEntity.notFound().build();
        }

        BookingDto bookingDtoRespuesta = modelMapper.map(booking,BookingDto.class);

        return ResponseEntity.ok(bookingDtoRespuesta);
    }
}
