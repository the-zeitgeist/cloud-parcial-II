package com.cloud.booking.repositories;

import com.cloud.booking.entities.Booking;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void when_findByCategory_return_ListProduct(){
        Long[] moviesIDS = new Long[2];
        moviesIDS[0] = 1L;
        moviesIDS[1] = 1L;

        Booking booking = Booking.builder()
                .id(1L)
                .userId(1L)
                .showtimeId(1L)
                .moviesIds(moviesIDS)
                .build();

        bookingRepository.save(booking);
        Booking bookingDB = bookingRepository.findByUserId(booking.getUserId()).orElse(null);
        Assertions.assertThat(bookingDB).isNotNull();
    }
}