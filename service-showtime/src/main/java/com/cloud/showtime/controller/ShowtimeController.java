package com.cloud.showtime.controller;

import com.cloud.showtime.dto.ShowtimeDto;
import com.cloud.showtime.entities.Showtime;
import com.cloud.showtime.services.ShowtimeService;
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
@RequestMapping("/showtime")
public class ShowtimeController {

    private final ShowtimeService showtimeService;
    private final ResponseBuilder builder;
    private final MyService myService;
    private final ModelMapper modelMapper;

    @PostMapping
    public Response save(@Valid @RequestBody ShowtimeDto showtimeDto, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(myService.formatMessage((result)));
        }

        Showtime showtime = modelMapper.map(showtimeDto,Showtime.class);
        showtimeService.save(showtime);
        ShowtimeDto showtimeDtoRespuesta = modelMapper.map(showtime,ShowtimeDto.class);

        return builder.success(showtimeDtoRespuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowtimeDto> update(@PathVariable("id") Long id,@RequestBody ShowtimeDto showtimeDto){
        Showtime showtime = modelMapper.map(showtimeDto,Showtime.class);
        Showtime showtimeDb = showtimeService.update(id,showtime);

        if(showtimeDb==null){
            return ResponseEntity.notFound().build();
        }else {
            ShowtimeDto showtimeDtoRespuesta = modelMapper.map(showtimeDb,ShowtimeDto.class);
            return ResponseEntity.ok(showtimeDtoRespuesta);
        }
    }

    @GetMapping
    public ResponseEntity<List<ShowtimeDto>> findAll(){
        List<Showtime> showtimes = showtimeService.findAll();
        if(showtimes.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<ShowtimeDto> showtimeDtos = showtimes.stream()
                .map(showtime -> modelMapper.map(showtime,ShowtimeDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(showtimeDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowtimeDto> findByID(@PathVariable Long id){
        Showtime showtime = showtimeService.findById(id);
        if(showtime==null){
            return ResponseEntity.notFound().build();
        }

        ShowtimeDto showtimeDto = modelMapper.map(showtime,ShowtimeDto.class);

        return ResponseEntity.ok(showtimeDto);
    }
}
