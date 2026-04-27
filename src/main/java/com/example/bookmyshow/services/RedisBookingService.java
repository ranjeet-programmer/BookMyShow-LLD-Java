package com.example.bookmyshow.services;

import com.example.bookmyshow.models.*;
import com.example.bookmyshow.repositories.ShowRepository;
import com.example.bookmyshow.repositories.ShowSeatRepository;
import com.example.bookmyshow.repositories.TicketRepository;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RedisBookingService implements BookingService{

    private final CacheService cacheService;
    private final ShowSeatRepository showSeatRepository;
    private final TicketRepository ticketRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;

    public RedisBookingService(RedisService cacheService, ShowSeatRepository showSeatRepository, TicketRepository ticketRepository, ShowRepository showRepository, UserRepository userRepository) {
        this.cacheService = cacheService;
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean blockSeats(long showId, List<Long> seatIds, long userId) {

        System.out.println("Printing Cache Before Logic");
        cacheService.getAllKeysAndValues();
        //steps involved to block (lock) a seat

        //1. we will check if the seats are available or not
        // a. Check if the seats are not booked already
            List<ShowSeat> showSeats = showSeatRepository.findAllByShowIdAndSeatIdIn(showId, seatIds);
            for(ShowSeat seat : showSeats) {
                if(seat.getStatus().equals(ShowSeatStatus.BOOKED)) {
                    return  false;
                }
            }


        // b. Check if the seats are not locked already
        for(ShowSeat seat: showSeats) {
            String status = (String) cacheService.get("seatId-"+seat.getId()+"-userId-"+userId);

            if(status != null) {
                return false;
            }
        }


        //2 If all the seats are available, then we will block(lock) the seat in redis [seat_id : user_id]
        for(ShowSeat seat: showSeats) {
            cacheService.set("seatId-"+seat.getId()+"-userId-"+userId, "LOCKED");
        }

        System.out.println("Printing Cache After Logic");
        cacheService.getAllKeysAndValues();

        return true;
    }

    @Override
    @Transactional
    public Optional<Ticket> bookTicket(long showId, List<Long> showSeatIds, long userId) {

        //1. In redis check if the user has lock for all the seats that they are trying to book

        for(Long seatId: showSeatIds) {
            String status = (String) cacheService.get("showSeatId-"+seatId+"-userId-"+userId);

            if(status == null) {
                return Optional.empty();
            }
        }

        // 2. If the user has lock for all the seats then we will book the seats

        User user = userRepository.findById(userId).get();
        Show show = showRepository.findById(showId).get();


        // create a new ticket
            Ticket ticket = createTicketAndBookSeats(show, user, showSeatIds);

        // Go to all the rows of the show_seat table and update the status to BOOKED in and update TIcketid


        return Optional.empty();

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    protected Ticket createTicketAndBookSeats(Show show, User user, List<Long> showSeatIds) {
        Ticket ticket = new Ticket();
        ticket.setAmount(100);
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setStatus(TicketStatus.BOOKED);
        ticket = ticketRepository.save(ticket);
        showSeatRepository.bookShowSeatsBulk(showSeatIds, ticket);

        return ticket;
    }

    @Override
    public void clearAllSeatLocks() {
        cacheService.deleteAll();
    }
}
