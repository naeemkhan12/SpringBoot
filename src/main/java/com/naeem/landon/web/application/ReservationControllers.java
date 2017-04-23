package com.naeem.landon.web.application;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.naeem.landon.business.domain.RoomReservation;
import com.naeem.landon.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 4/23/2017.
 */
@Controller
@RequestMapping(value = "/reservations")
public class ReservationControllers {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private ReservationService reservationService;
    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value = "date" , required = false)String dateString, Model model) {
        Date date = null;
        if(dateString!=null) {
            try {
                date = DATE_FORMAT.parse(dateString);

            } catch (ParseException ex) {
                date = new Date();
            }
        }else{
                date = new Date();
        }
        List<RoomReservation> roomReservations = this.reservationService.getRoomReservationForDate(date);
        model.addAttribute("roomReservations" , roomReservations);
        return "reservations";
    }


}
