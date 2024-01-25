//package com.sda.hotel.business;
//
//import com.sda.hotel.data.*;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//// pierwsze rozwiązanie pisane wspólnie później nie wykorzystywana w ostatecznej wersji progaramu
//
//@Service
//public class ReservationServiceV1 {
//    private final RoomRepository roomRepository;
//    private final GuestRepository guestRepository;
//    private final ReservationRepository reservationRepository;
//
//    public ReservationServiceV1(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
//        this.roomRepository = roomRepository;
//        this.guestRepository = guestRepository;
//        this.reservationRepository = reservationRepository;
//    }
//
//    // cała logika jest zmieniona od tego momentu
//
//    public List<RoomReservation> getRoomReservationForDate(Date date){
//        // pobranie wszystkich pokoi
//        Iterable<Room> rooms = this.roomRepository.findAll();
//        // mapowanie pokoi na rezerwacje
//        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
//        rooms.forEach(room -> {
//            RoomReservation roomReservation = new RoomReservation();
//            roomReservation.setRoomId(room.getId());
//            roomReservation.setRoomName(room.getName());
//            roomReservation.setRoomNumber(room.getRoomNumber());
//            roomReservationMap.put(room.getId(), roomReservation);
//        });
//        // przeszukiwanie rezerwacji po dacie
//        Iterable<Reservation> reservations = this.reservationRepository
//                .findReservationByReservationDate(new java.sql.Date(date.getTime()));
//        reservations.forEach(reservation -> {
//            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
//            roomReservation.setDate(date);
//            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
//            roomReservation.setGuestId(guest.getGuestId());
//            roomReservation.setFirstName(guest.getFirstName());
//            roomReservation.setLastName(guest.getLastName());
//        });
//
//        List<RoomReservation> roomReservations = new ArrayList<>();
//        for (Long id : roomReservationMap.keySet()){
//            roomReservations.add(roomReservationMap.get(id));
//        }
//
//        roomReservations.sort(new Comparator<RoomReservation>() {
//            @Override
//            public int compare(RoomReservation o1, RoomReservation o2) {
//                if(o1.getRoomName().equals(o2.getRoomName()))
//                    return o1.getRoomNumber().compareTo(o2.getRoomNumber());
//                return o1.getRoomName().compareTo(o2.getRoomName());
//            }
//        });
//
//        return roomReservations;
//    }
//}
