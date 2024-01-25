package com.sda.hotel.rest;

import com.sda.hotel.business.ReservationService;
import com.sda.hotel.business.RoomReservation;
import com.sda.hotel.data.Guest;
import com.sda.hotel.data.Room;
import com.sda.hotel.utils.DateUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

// @RestController zwraca json'a co potem da się przechwycić
@RestController
@RequestMapping("/api")
public class HotelRestController {
    private final ReservationService reservationService;
    private final DateUtils dateUtils;

    public HotelRestController(ReservationService reservationService, DateUtils dateUtils) {
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String stringDate){
        Date date = this.dateUtils.createDateFromDateString(stringDate);
        return this.reservationService.getRoomReservationForDate(date);
    }

    @GetMapping(path = "/guests")
    public List<Guest> getGuest(){
        return this.reservationService.getHotelGuests();
    }

    @PostMapping(path = "/guests")
    public void addGuest(@RequestBody Guest guest){ //@RequestBody przekazuje dane w ciele żądania
        this.reservationService.addGuest(guest);
    }

    @GetMapping(path = "/rooms")
    public List<Room> getRooms(){
        return this.reservationService.getRooms();
    }
}
