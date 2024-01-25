package com.sda.hotel.business;

import com.sda.hotel.data.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationForDate(Date date) {
        List<RoomReservation> roomReservations = new ArrayList<>();
        Iterable<Room> rooms = this.roomRepository.findAll();
        for(Room room: rooms){
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());

            Iterable<Reservation> reservations = this.reservationRepository.
                    findReservationByRoomIdAndReservationDate(room.getId(), new java.sql.Date(date.getTime()));

            for (Reservation reservation: reservations){
                roomReservation.setDate(date);
                Guest guest = this.guestRepository.findById(reservation.getGuestId()).orElse(null);

                if(guest != null){
                    roomReservation.setGuestId(guest.getGuestId());
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                }

                roomReservations.add(roomReservation);
            }
            roomReservations.sort(Comparator.comparing(RoomReservation::getRoomNumber)
                    .thenComparing(RoomReservation::getRoomName));
        }
        return roomReservations;
    }


    // pobiera wszystkich gości z bazy i sortuje ich po nazwisku potem po imieniu
    public List<Guest> getHotelGuests() {
        List<Guest> guestList = this.guestRepository.findAll();
        guestList.sort(
                new Comparator<Guest>() {
                    @Override
                    public int compare(Guest o1, Guest o2) {
                        if (o1.getLastName().equals(o2.getLastName())) {
                            return o1.getFirstName().compareTo(o2.getFirstName());
                        }
                        return o1.getLastName().compareTo(o2.getLastName());
                    }
                }
        );
        return guestList;
    }

    public List<Room> getRooms() {
        Iterable<Room> rooms = this.roomRepository.findAll();
        List<Room> roomList = new ArrayList<>();
        rooms.forEach(roomList::add);

        roomList.sort(new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.getRoomNumber().compareTo(o2.getRoomNumber());
            }
        });
        return roomList;
    }

    public void addGuest(Guest guest) {
        if(guest==null) throw new RuntimeException("Guest cannot be null");
        this.guestRepository.save(guest);
    }
}
