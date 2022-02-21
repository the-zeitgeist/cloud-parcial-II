package com.cloud.showtime.services;

import com.cloud.showtime.entities.Showtime;
import com.cloud.showtime.repositories.ShowtimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService{

    private final ShowtimeRepository showtimeRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Showtime showtime) {
        showtimeRepository.save(showtime);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Showtime> findAll() {
        return showtimeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Showtime findById(Long id) {
        return showtimeRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Showtime update(Long id,Showtime showtime) {
        Showtime showtimeDb = this.findById(id);

        if (Objects.isNull(showtimeDb)){
            return null;
        }else {
            showtimeDb =  showtimeDb.toBuilder()
                    .date(showtime.getDate())
                    .movies(showtime.getMovies())
                    .build();

            this.save(showtimeDb);
            return showtimeDb;
        }
    }
}
