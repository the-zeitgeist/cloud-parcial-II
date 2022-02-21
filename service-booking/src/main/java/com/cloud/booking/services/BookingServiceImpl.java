package com.cloud.booking.services;

import com.cloud.booking.client.MovieClient;
import com.cloud.booking.client.UserClient;
import com.cloud.booking.entities.Booking;
import com.cloud.booking.modules.Movie;
import com.cloud.booking.modules.User;
import com.cloud.booking.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final MovieClient movieClient;
    private final UserClient userClient;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> findAll() {
        return bookingRepository.findAll().stream()
                .map(booking ->
                        booking.toBuilder()
                                .movies(buildMovies(booking))
                                .user(modelMapper.map(userClient.findById(booking.getUserId()).getData(), User.class))
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Booking findById(Long id) {

        Booking booking = bookingRepository.findById(id).orElse(null);
        if (Objects.nonNull(booking)) {
            return booking.toBuilder()
                    .movies(buildMovies(booking))
                    .user(modelMapper.map(userClient.findById(booking.getUserId()).getData(), User.class))
                    .build();
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Booking findByUserId(Long id) {
        Booking booking = bookingRepository.findByUserId(id).orElse(null);

        if (Objects.nonNull(booking)) {
            return booking.toBuilder()
                    .movies(buildMovies(booking))
                    .user(modelMapper.map(userClient.findById(booking.getUserId()).getData(), User.class))
                    .build();
        }
        return null;
    }

    private List<Movie> buildMovies(Booking booking) {
        List<Movie> movies = new ArrayList<>();

        if (booking.getMoviesIds() != null){
            for (Long movieId : booking.getMoviesIds()) {
                Movie movie = modelMapper.map(movieClient.findByID(movieId).getData(), Movie.class);
                movies.add(movie);
            }

        }
        return movies;
    }
}
