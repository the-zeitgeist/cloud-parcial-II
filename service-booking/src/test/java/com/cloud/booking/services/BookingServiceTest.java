package com.cloud.booking.services;

import com.cloud.booking.client.MovieClient;
import com.cloud.booking.client.UserClient;
import com.cloud.booking.entities.Booking;
import com.cloud.booking.modules.Movie;
import com.cloud.booking.modules.User;
import com.cloud.booking.repositories.BookingRepository;
import com.example.multimodule.service.utils.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private MovieClient movieClient;
    @Mock
    private UserClient userClient;
    @Mock
    private ModelMapper modelMapper;
    private BookingService bookingService;

    @BeforeEach
    public void begin(){
        MockitoAnnotations.initMocks(this);
        bookingService = new BookingServiceImpl(bookingRepository,movieClient,userClient,modelMapper);

        Long[] moviesIDS = new Long[2];
        moviesIDS[0] = 1L;
        moviesIDS[1] = 1L;

        Booking booking = Booking.builder()
                .id(1L)
                .userId(1L)
                .showtimeId(1L)
                .moviesIds(moviesIDS)
                .build();

        Mockito.when(bookingRepository.findById(1L))
                .thenReturn(Optional.of(booking));

        Mockito.when(movieClient.findByID(1L))
                .thenReturn(Response.builder().data(new Movie()).build());

        Mockito.when(userClient.findById(1L))
                .thenReturn(Response.builder().data(new User()).build());
    }

    @Test
    void when_findById_return_product(){
        Booking booking  = bookingService.findById(1L);
        Assertions.assertThat(booking.getId()).isEqualTo(1L);
    }

}