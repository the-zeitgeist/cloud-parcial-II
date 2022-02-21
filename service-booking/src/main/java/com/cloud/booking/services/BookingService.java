package com.cloud.booking.services;

import com.cloud.booking.entities.Booking;

import java.util.List;

public interface BookingService {
    void save(Booking booking);
    void delete(Booking booking);
    List<Booking> findAll();
    Booking findById(Long id);
    Booking findByUserId(Long id);
}
