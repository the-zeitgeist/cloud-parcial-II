package com.cloud.showtime.services;

import com.cloud.showtime.entities.Showtime;

import java.util.List;

public interface ShowtimeService {

    void save(Showtime showtime);
    List<Showtime> findAll();
    Showtime findById(Long id);
    Showtime update(Long id,Showtime showtime);
}
