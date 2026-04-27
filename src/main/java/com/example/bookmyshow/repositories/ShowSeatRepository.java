package com.example.bookmyshow.repositories;

import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.models.Ticket;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findAllByShowId(long showId);

    List<ShowSeat> findAllByShowIdAndSeatIdIn(long showId, List<Long> seatIds);

    @Modifying
    @Query("UPDATE ShowSeat s SET s.ticket = :ticket, s.status = 0  WHERE s.id IN : ids")
    int bookShowSeatsBulk(@Param("ids") List<Long> ids, @Param("ticket") Ticket ticket);

}
