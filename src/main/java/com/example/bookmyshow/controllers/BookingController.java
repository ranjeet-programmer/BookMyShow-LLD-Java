package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dtos.BlockSeatsRequestDto;
import com.example.bookmyshow.dtos.BookSeatRequestDto;
import com.example.bookmyshow.models.Ticket;
import com.example.bookmyshow.services.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bookings")



public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/block")
    public boolean blockSeats(@RequestBody BlockSeatsRequestDto blockSeatsRequestDto) {
        return bookingService.blockSeats(blockSeatsRequestDto.getShowId(), blockSeatsRequestDto.getSeatId(), blockSeatsRequestDto.getUserId());
    }

    @DeleteMapping
    public void clearAllSeatLocks () {
        bookingService.clearAllSeatLocks();
    }

    @PostMapping("/confirm")
    public Optional<Ticket> confirmBooking(@RequestBody BookSeatRequestDto bookSeatRequestDto) {
        return bookingService.bookTicket(bookSeatRequestDto.getShowId(),  bookSeatRequestDto.getSeatId(), bookSeatRequestDto.getUserId());
    }

}
