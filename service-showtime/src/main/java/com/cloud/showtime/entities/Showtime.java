package com.cloud.showtime.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Builder(toBuilder = true)
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Showtime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false,length = 600)
    private Long[] movies;
}
