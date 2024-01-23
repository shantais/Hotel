package com.sda.hotel.data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
    @Id
    @Column(name ="RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;
    @Column(name="ROOM_ID")
    private long roomId;
    @Column(name="GUEST_ID")
    private long guestId;
    @Column(name="RES_DATE")
    private Date reservationDate;

    public Long getReservationId() {
        return reservationId;
    }

    public long getRoomId() {
        return roomId;
    }

    public long getGuestId() {
        return guestId;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", roomId=" + roomId +
                ", guestId=" + guestId +
                ", reservationDate=" + reservationDate +
                '}';
    }
}
