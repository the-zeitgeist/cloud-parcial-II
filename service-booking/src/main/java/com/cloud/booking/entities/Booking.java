package com.cloud.booking.entities;

import com.cloud.booking.modules.Movie;
import com.cloud.booking.modules.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder(toBuilder = true)
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Transient
    private User user;

    @Column(nullable = false)
    private Long showtimeId;

    private Long[] moviesIds;

    @Transient
    private List<Movie> movies;
}
