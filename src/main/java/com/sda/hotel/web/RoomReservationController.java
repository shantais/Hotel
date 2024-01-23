package com.sda.hotel.web;

import com.sda.hotel.business.ReservationService;
import com.sda.hotel.business.RoomReservation;
import com.sda.hotel.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservations") //nadana sciezka przewodnia dla wszystkich endpointów związanych z rezerwacjami
public class RoomReservationController {
    private final ReservationService reservationService;
    private final DateUtils dateUtils;

    public RoomReservationController(ReservationService reservationService, DateUtils dateUtils) {
        this.reservationService = reservationService;
        this.dateUtils = dateUtils;
    }

    //    @GetMapping // robi to samo co poniższa metoda
    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value = "date", required = false) String dateString, Model model){
        Date date = this.dateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations = this.reservationService.getRoomReservationForDate(date);
        model.addAttribute("roomReservations", roomReservations);
        return "roomres";
    }
}
