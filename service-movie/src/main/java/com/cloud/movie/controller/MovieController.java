package com.cloud.movie.controller;


import com.cloud.movie.dto.MovieDto;
import com.cloud.movie.entities.Movie;
import com.cloud.movie.services.MovieService;
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
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private final ResponseBuilder builder;
    private final MovieService movieService;
    private final MyService myService;
    private final ModelMapper modelMapper;

    @PostMapping
    public Response save(@Valid @RequestBody MovieDto movieDto, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(myService.formatMessage((result)));
        }

        Movie movie = modelMapper.map(movieDto,Movie.class);

        movieService.save(movie);
        MovieDto movieDtoRespuesta = modelMapper.map(movie,MovieDto.class);
        return builder.success(movieDtoRespuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovieDto> delete(@PathVariable("id") Long id){
        Movie movie = movieService.findById(id);
        if(movie==null){
            return ResponseEntity.notFound().build();
        }
        movieService.delete(movie);

        MovieDto movieDto = modelMapper.map(movie,MovieDto.class);
        return ResponseEntity.ok(movieDto);
    }

    @GetMapping
    public Response findAll(){
        List<Movie> movies = movieService.findAll();
        if(movies.isEmpty()){
            return builder.failed(null);
        }

        List <MovieDto> moviesDto = movies.stream()
                .map(movie -> modelMapper.map(movie,MovieDto.class))
                .collect(Collectors.toList());

        return builder.success(moviesDto);
    }

    @GetMapping("/{id}")
    public Response findByID(@PathVariable Long id){
        Movie movie = movieService.findById(id);
        if(movie==null){
            return builder.failed(null);
        }

        MovieDto movieDto = modelMapper.map(movie,MovieDto.class);

        return builder.success(movieDto);
    }
}
