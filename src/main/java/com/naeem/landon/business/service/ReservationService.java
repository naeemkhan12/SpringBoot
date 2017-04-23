package com.naeem.landon.business.service;

import com.naeem.landon.business.domain.RoomReservation;
import com.naeem.landon.data.entity.Guest;
import com.naeem.landon.data.entity.Reservation;
import com.naeem.landon.data.entity.Room;
import com.naeem.landon.data.repository.GuestRepository;
import com.naeem.landon.data.repository.ReservationRepository;
import com.naeem.landon.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Naeem on 4/23/2017.
 */
@Service
public class ReservationService {
    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }
    public List <RoomReservation> getRoomReservationForDate(Date date){
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long,RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room ->{
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(room.getId(),roomReservation);

        });
        Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
        if(reservations!=null){
            reservations.forEach(reservation ->{
                Guest guest = this.guestRepository.findOne(reservation.getGuestId());
                if(guest !=null){
                    RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
                    roomReservation.setData(date);
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                    roomReservation.setGuestId(guest.getId());

                }
            });
        }
        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long roomId:roomReservationMap.keySet()){
            roomReservations.add(roomReservationMap.get(roomId));

        }
        return roomReservations;

    }
}
